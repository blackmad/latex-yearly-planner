package tex

import "fmt"

const nl = "\n"

func CellColor(color, text string) string {
	return fmt.Sprintf(`\cellcolor{%s}{%s}`, color, text)
}

func TextColor(color, text string) string {
	return fmt.Sprintf(`\textcolor{%s}{%s}`, color, text)
}

func Hyperlink(ref, text string) string {
	return text
}

func Hypertarget(ref, text string) string {
	return ""
}

func Tabular(format, text string) string {
	return `\begin{tabular}{` + format + `}` + nl + text + nl + `\end{tabular}`
}

func ResizeBoxW(width, text string) string {
	return fmt.Sprintf(`\resizebox{!}{%s}{%s}`, width, text)
}

func Multirow(rows int, text string) string {
	return fmt.Sprintf(`\multirow{%d}{*}{%s}`, rows, text)
}

func Bold(text string) string {
	return fmt.Sprintf(`\textbf{%s}`, text)
}
