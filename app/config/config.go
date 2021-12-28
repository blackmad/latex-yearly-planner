package config

import (
	"fmt"
	"io/ioutil"
	"log"
	"strings"
	"time"

	"regexp"
	"strconv"

	"github.com/caarlos0/env/v6"
	"gopkg.in/yaml.v3"
)

type Config struct {
	Debug Debug

	Year                int `env:"PLANNER_YEAR"`
	WeekStart           time.Weekday
	Dotted              bool
	CalAfterSchedule    bool
	ClearTopRightCorner bool
	AMPMTime            bool
	AddLastHalfHour     bool

	StartDate string
	Duration  string

	DefaultHeader string

	Pages Pages

	Layout Layout
}

type Debug struct {
	ShowFrame bool
	ShowLinks bool
}

type Pages []Page
type Page struct {
	Name     string
	FuncName string
	Template string
}

type RenderBlocks []RenderBlock

func (r Pages) WeeklyEnabled() bool {
	for _, s := range r {
		if s.FuncName == "weekly" {
			return true
		}
	}

	return false
}

type RenderBlock struct {
	FuncName string
	Template string
}

type Colors struct {
	Gray                string
	LightGray           string
	BackgroundHighlight string
	TextColor           string
}

type Layout struct {
	Paper Paper

	Numbers Numbers
	Lengths Lengths
	Colors  Colors
}

type Numbers struct {
	ArrayStretch        float64
	QuarterlyLines      int
	WeeklyLines         int
	DailyTodos          int
	DailyNotes          int
	DailyPersonal       int
	DailyBottomHour     int
	DailyTopHour        int
	DailyDiaryGoals     int
	DailyDiaryGrateful  int
	DailyDiaryBest      int
	DailyDiaryLog       int
	TodoLinesInTodoPage int
	IndexMeetingNotes   int
	NotesIndexPages     int
	NotesOnPage         int
	DotHeightFull       int
	DotWidthFull        int
	DotWidthTwoThirds   int
}

type Paper struct {
	Width  string `env:"PLANNER_LAYOUT_PAPER_WIDTH"`
	Height string `env:"PLANNER_LAYOUT_PAPER_HEIGHT"`

	Margin Margin

	ReverseMargins bool
	MarginParWidth string
	MarginParSep   string
}

type Margin struct {
	Top    string `env:"PLANNER_LAYOUT_PAPER_MARGIN_TOP"`
	Bottom string `env:"PLANNER_LAYOUT_PAPER_MARGIN_BOTTOM"`
	Left   string `env:"PLANNER_LAYOUT_PAPER_MARGIN_LEFT"`
	Right  string `env:"PLANNER_LAYOUT_PAPER_MARGIN_RIGHT"`
}

func New(pathConfigs ...string) (Config, error) {
	var (
		bts []byte
		err error
		cfg Config
	)

	for _, filepath := range pathConfigs {
		if bts, err = ioutil.ReadFile(strings.ToLower(filepath)); err != nil {
			return cfg, fmt.Errorf("ioutil read file: %w", err)
		}

		if err = yaml.Unmarshal(bts, &cfg); err != nil {
			return cfg, fmt.Errorf("yaml unmarshal: %w", err)
		}
	}

	if err = env.Parse(&cfg); err != nil {
		return cfg, fmt.Errorf("env parse: %w", err)
	}

	if cfg.Year == 0 {
		cfg.Year = cfg.ParsedStartDate().Year()
	}

	fmt.Println(cfg)

	return cfg, nil
}

func weekStartDate(date time.Time, startDay time.Weekday) time.Time {
	offset := (int(startDay) - int(date.Weekday()) - 7) % 7
	result := date.Add(time.Duration(offset*24) * time.Hour)
	return result
}

func (cfg Config) ParsedStartDate() time.Time {
	if cfg.StartDate == "today" {
		tm, _ := time.Parse("2006-01-02", time.Now().Format("2006-01-02"))
		return tm
	}

	if cfg.StartDate == "weekstart" {
		return weekStartDate(time.Now(), cfg.WeekStart)
	}

	if cfg.StartDate == "monthstart" {
		tm, _ := time.Parse("2006-01-02", time.Now().Format("2006-01-02")[0:7]+"-01")
		return tm
	}

	return time.Date(cfg.Year, 1, 1, 0, 0, 0, 0, time.Local)
}

func (cfg Config) DurationDays() int {
	return -1 * int(cfg.ParsedStartDate().Sub(cfg.EndDate()).Hours()) / 24
}

func (cfg Config) EndDate() time.Time {
	regex := *regexp.MustCompile(`(\d+)(\w+)`)
	res := regex.FindAllStringSubmatch(cfg.Duration, -1)

	durationNum, _ := strconv.Atoi(res[0][1])
	durationType := res[0][2]

	startdate := cfg.ParsedStartDate()

	if durationType == "year" || durationType == "years" {
		lastday := startdate.AddDate(durationNum, 0, 0).Add(time.Nanosecond * -1)
		return lastday
	}

	if durationType == "month" || durationType == "months" {
		lastday := startdate.AddDate(0, durationNum, 0).Add(time.Nanosecond * -1)
		return lastday
	}

	if durationType == "week" || durationType == "weeks" {
		lastday := startdate.AddDate(0, 0, durationNum*7).Add(time.Nanosecond * -1)
		return lastday
	}

	if durationType == "day" || durationType == "days" {
		lastday := startdate.AddDate(0, 0, durationNum).Add(time.Nanosecond * -1)
		return lastday
	}

	if cfg.Duration != "" {
		log.Fatalf("Could not parse duration: %s", cfg.Duration)
	}

	return startdate.AddDate(0, 0, 1).Add(time.Nanosecond * -1)
}
