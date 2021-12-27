package compose

import (
	"github.com/kudrykv/latex-yearly-planner/app/components/cal"
	"github.com/kudrykv/latex-yearly-planner/app/components/header"
	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

var Daily = DailyStuff("", "")
var DailyPlan = DailyStuff("Plan", "Plan")
var DailyReflect = DailyStuff("Reflect", "Reflect")
var DailyNotes = DailyStuff("More", "Notes")

func PageHeader(leaf string) header.Items {
	items := header.Items{}
	items = append(items, header.NewTextItem(leaf))
	return items
}

type DailyDay struct {
	Quarter *cal.Quarter
	Month   *cal.Month
	Year    *cal.Year
	Week    *cal.Week
	Day     *cal.Day
}

func DailyStuff(prefix, leaf string) func(cfg config.Config, tpls []string, dailyDay DailyDay) (page.Modules, error) {
	return func(cfg config.Config, tpls []string, dailyDay DailyDay) (page.Modules, error) {
		modules := make(page.Modules, 0, 1)

		year := dailyDay.Year
		month := dailyDay.Month
		quarter := dailyDay.Quarter
		week := dailyDay.Week
		day := dailyDay.Day

		modules = append(modules, page.Module{
			Cfg: cfg,
			Tpl: tpls[0],
			Body: map[string]interface{}{
				"Year":         year,
				"Quarter":      quarter,
				"Month":        month,
				"Week":         week,
				"Day":          day,
				"Breadcrumb":   day.Breadcrumb(prefix, "", cfg.ClearTopRightCorner && len(leaf) > 0),
				"HeadingMOS":   day.HeadingMOS(prefix, leaf),
				"SideQuarters": year.SideQuarters(day.Quarter()),
				"SideMonths":   year.SideMonths(day.Month()),
				"Extra":        PageHeader(leaf),
				"Extra2":       extra2(cfg.ClearTopRightCorner, false, false, week, 0),
			},
		})

		return modules, nil
	}
}
