package compose

import (
	"fmt"

	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

func Title(cfg config.Config, name string, template string, dailyDay DailyDay) (page.Modules, error) {
	if template == "" {
		return nil, fmt.Errorf("exppected one tpl, got %s", template)
	}

	if dailyDay.Index != 0 {
		modules := make(page.Modules, 0, 1)
		return modules, nil
	}

	return page.Modules{{Cfg: cfg, Template: template}}, nil
}
