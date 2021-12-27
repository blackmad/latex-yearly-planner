package app

import (
	"bytes"
	"errors"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strings"

	"github.com/kudrykv/latex-yearly-planner/app/components/cal"
	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/compose"
	"github.com/kudrykv/latex-yearly-planner/app/config"
	"github.com/kudrykv/latex-yearly-planner/app/tpls"
	"github.com/urfave/cli/v2"
)

const (
	fConfig = "config"
)

func New() *cli.App {
	return &cli.App{
		Name: "plannergen",

		Writer:    os.Stdout,
		ErrWriter: os.Stderr,

		Flags: []cli.Flag{
			&cli.PathFlag{Name: fConfig, Required: true},
		},

		Action: action,
	}
}

func action(c *cli.Context) error {
	var (
		fn  Composer
		ok  bool
		cfg config.Config
		err error
	)

	pathConfigs := strings.Split(c.Path(fConfig), ",")
	if cfg, err = config.New(pathConfigs...); err != nil {
		return fmt.Errorf("config new: %w", err)
	}

	wr := &bytes.Buffer{}

	t := tpls.New()

	if err = t.Document(wr, cfg); err != nil {
		return fmt.Errorf("tex document: %w", err)
	}

	if err = ioutil.WriteFile("out/"+RootFilename(pathConfigs[len(pathConfigs)-1]), wr.Bytes(), 0600); err != nil {
		return fmt.Errorf("ioutil write file: %w", err)
	}

	year := cal.NewYear(cfg.WeekStart, cfg.Year)

	wr.Reset()

	index := 0

	currentDate := cfg.ParsedStartDate()

	for !currentDate.After(cfg.EndDate()) {
		log.Println(currentDate)

		for _, block := range cfg.Pages {

			var mom []page.Modules
			if fn, ok = ComposerMap[block.FuncName]; !ok {
				fmt.Println((block))
				return fmt.Errorf("unknown func " + block.FuncName)
			}

			day := cal.Day{Time: currentDate}
			week := cal.FillWeekly(cfg.WeekStart, year, day)
			quarter := cal.NewQuarter(cfg.WeekStart, year, int(day.Month())/3)
			month := cal.NewMonth(cfg.WeekStart, year, quarter, day.Month())

			log.Println("block name:", block.Name)
			modules, err := fn(cfg, block.Name, block.Template, compose.DailyDay{
				Day:     &day,
				Month:   month,
				Year:    year,
				Quarter: quarter,
				Week:    week,
				Index:   index,
			})

			if err != nil {
				return fmt.Errorf("%s: %w", block.FuncName, err)
			}

			mom = append(mom, modules)

			if len(mom) == 0 {
				return fmt.Errorf("modules of modules must have some modules")
			}

			allLen := len(mom[0])
			for _, mods := range mom {
				if len(mods) != allLen {
					return errors.New("some modules are not aligned")
				}
			}

			for i := 0; i < allLen; i++ {
				for j, mod := range mom {
					log.Println("one page", j, i)
					HeaderTemplateLine := ""
					if mod[i].HeaderTemplateFilename != "" {
						HeaderTemplateLine = `{{ template "` + mod[i].HeaderTemplateFilename + `" dict "Cfg" .Cfg "Body" .Body }}`
					}
					BodyTemplateLine := `{{ template "` + mod[i].Template + `" dict "Cfg" .Cfg "Body" .Body }}`

					fullTemplate := HeaderTemplateLine + "\n" + BodyTemplateLine + "\n\\newpage" + "\n"

					if err = t.ExecuteContents(wr, fullTemplate, mod[i]); err != nil {
						return fmt.Errorf("execute %s on %s: %w", block.FuncName, fullTemplate, err)
					}
				}
			}
		}
		index += 1
		currentDate = currentDate.AddDate(0, 0, 1)
	}

	if err = ioutil.WriteFile("out/content.tex", wr.Bytes(), 0600); err != nil {
		return fmt.Errorf("ioutil write file: %w", err)
	}

	return nil
}

func RootFilename(pathconfig string) string {
	if idx := strings.LastIndex(pathconfig, "/"); idx >= 0 {
		pathconfig = pathconfig[idx+1:]
	}

	if strings.HasSuffix(pathconfig, ".yml") {
		pathconfig = pathconfig[:len(pathconfig)-len(".yml")]
	}

	if strings.HasSuffix(pathconfig, ".yaml") {
		pathconfig = pathconfig[:len(pathconfig)-len(".yaml")]
	}

	return pathconfig + ".tex"
}

type Composer func(cfg config.Config, name string, template string, dailyDay compose.DailyDay) (page.Modules, error)

var ComposerMap = map[string]Composer{
	"title": compose.Title,
	// "annual":        compose.Annual,
	// "quarterly":     compose.Quarterly,
	"monthly": compose.Monthly,
	"weekly":  compose.Weekly,
	"daily":   compose.Daily,
	// "notes_indexed": compose.NotesIndexed,
}
