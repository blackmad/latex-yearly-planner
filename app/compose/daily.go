package compose

import (
	"github.com/kudrykv/latex-yearly-planner/app/components/cal"
	"github.com/kudrykv/latex-yearly-planner/app/components/header"
	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

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

func Daily(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	prefix := ""
	modules := make(page.Modules, 0, 1)

	year := dailyDay.Year
	month := dailyDay.Month
	quarter := dailyDay.Quarter
	week := dailyDay.Week
	day := dailyDay.Day

	modules = append(modules, page.Module{
		Cfg:                    cfg,
		Template:               template,
		HeaderTemplateFilename: cfg.DefaultHeader,
		Body: map[string]interface{}{
			"Year":         year,
			"Quarter":      quarter,
			"Month":        month,
			"Week":         week,
			"Day":          day,
			"Breadcrumb":   day.Breadcrumb(prefix, "", cfg.ClearTopRightCorner && len(name) > 0),
			"HeadingMOS":   day.HeadingMOS(prefix, name),
			"SideQuarters": year.SideQuarters(day.Quarter()),
			"SideMonths":   year.SideMonths(day.Month()),
			"Extra":        PageHeader(name),
			"Extra2":       extra2(cfg.ClearTopRightCorner, false, false, week, 0),
		},
	})

	return modules, nil
}
