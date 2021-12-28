package compose

import (
	"fmt"

	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

func Monthly(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	fmt.Println("montly", dailyDay.Index)
	if dailyDay.Index != 0 {
		return make(page.Modules, 0), nil
		// TOOD make this work for multiple months again
	}

	year := dailyDay.Year
	month := dailyDay.Month
	quarter := dailyDay.Quarter

	modules := make(page.Modules, 0, 1)

	modules = append(modules, page.Module{
		Cfg:                    cfg,
		Template:               template,
		HeaderTemplateFilename: cfg.DefaultHeader,
		Body: map[string]interface{}{
			"Year":         year,
			"Quarter":      quarter,
			"Month":        month,
			"Breadcrumb":   month.Breadcrumb(),
			"HeadingMOS":   month.HeadingMOS(),
			"SideQuarters": year.SideQuarters(quarter.Number),
			"SideMonths":   year.SideMonths(month.Month),
			"Extra":        PageHeader(name),
			"Extra2":       extra2(cfg.ClearTopRightCorner, false, false, nil, 0),
		},
	})

	return modules, nil
}
