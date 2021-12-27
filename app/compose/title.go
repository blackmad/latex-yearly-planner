package compose

import (
	"fmt"

	"github.com/kudrykv/latex-yearly-planner/app/components/page"
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

func Title(cfg config.Config, template string) (page.Modules, error) {
	if template == "" {
		return nil, fmt.Errorf("exppected one tpl, got %s", template)
	}

	return page.Modules{{Cfg: cfg, Template: template}}, nil
}
