package compose

import (
	"fmt"

	"github.com/kudrykv/latex-yearly-planner/app/components/cal"
	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

func MonthlyEnd(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	fmt.Println(dailyDay.Day.Time, cfg.EndDate())
	if dailyDay.Day.Time.Day() == dailyDay.Month.LastDay() ||
		dailyDay.Day.Time.Format("2020-01-01") == cfg.EndDate().Format("2020-01-01") {
		return MonthlyHelper(cfg, name, template, dailyDay)
	}

	return make(page.Modules, 0), nil

}

func Monthly(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	if dailyDay.Index == 0 || dailyDay.Day.Time.Day() == 1 {
		return MonthlyHelper(cfg, name, template, dailyDay)
	}
	return make(page.Modules, 0), nil

}

func MonthlyHelper(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	day := dailyDay.Day
	year := dailyDay.Year
	month := dailyDay.Month
	quarter := dailyDay.Quarter

	modules := make(page.Modules, 0, 1)

	// calculate all the weeks!
	weeks := make(cal.Weeks, 0)
	currentDate := day.Time

	allMonths := []string{}

	for i := 0; i < 5; i++ {
		day := cal.Day{Time: currentDate}

		week := cal.FillWeekly(cfg.WeekStart, year, day)
		weeks = append(weeks, week)

		for _, month := range week.Months {
			if !contains(allMonths, month.Month.String()) {
				allMonths = append(allMonths, month.Month.String())
			}
		}

		currentDate = currentDate.AddDate(0, 0, 7)
	}

	cfg.ParsedStartDate()

	modules = append(modules, page.Module{
		Cfg:                    cfg,
		Template:               template,
		HeaderTemplateFilename: cfg.DefaultHeader,
		Body: map[string]interface{}{
			"Year":         year,
			"Quarter":      quarter,
			"Month":        month,
			"Breadcrumb":   month.Breadcrumb(allMonths),
			"HeadingMOS":   month.HeadingMOS(),
			"SideQuarters": year.SideQuarters(quarter.Number),
			"SideMonths":   year.SideMonths(month.Month),
			"Extra":        PageHeader(name),
			"Extra2":       extra2(cfg.ClearTopRightCorner, false, false, nil, 0),
			"Weeks":        weeks,
			"Test":         "wtf",
		},
	})

	return modules, nil
}
