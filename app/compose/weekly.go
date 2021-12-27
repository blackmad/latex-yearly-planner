package compose

import (
	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

func Weekly(cfg config.Config, tpls []string, dailyDay DailyDay) (page.Modules, error) {
	if dailyDay.Day.Time.Weekday() != cfg.WeekStart {
		return make(page.Modules, 0, 0), nil
	}

	modules := make(page.Modules, 0, 1)

	year := dailyDay.Year
	week := dailyDay.Week

	modules = append(modules, page.Module{
		Cfg: cfg,
		Tpl: tpls[0],
		Body: map[string]interface{}{
			"Year":         year,
			"Week":         week,
			"Breadcrumb":   week.Breadcrumb(),
			"HeadingMOS":   week.HeadingMOS(),
			"SideQuarters": year.SideQuarters(week.Quarters.Numbers()...),
			"SideMonths":   year.SideMonths(week.Months.Months()...),
			"Extra":        week.PrevNext().WithTopRightCorner(cfg.ClearTopRightCorner),
			"Extra2":       extra2(cfg.ClearTopRightCorner, false, false, nil, 0),
		},
	})

	return modules, nil
}
