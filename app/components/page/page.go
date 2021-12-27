package page

import (
	"github.com/kudrykv/latex-yearly-planner/app/config"
)

type Page struct {
	Cfg     config.Config
	Modules Modules
}

type Modules []Module
type Module struct {
	Cfg                    config.Config
	Template               string
	HeaderTemplateFilename string
	Body                   interface{}
}
