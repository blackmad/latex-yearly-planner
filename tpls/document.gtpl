\documentclass[9pt]{exam}

\title{\vspace{-20cm}}


\usepackage{geometry}

\geometry{textwidth=\paperwidth, textheight=\paperheight, noheadfoot, nomarginpar}
\setlength{\topskip}{0mm}
\setlength{\parindent}{0mm}


\newlength{\mytextsize}

   %This part fails....  (no it doesn't:-)
   \makeatletter
      \setlength{\mytextsize}{\f@size pt}
   \makeatother

\usepackage{geometry}
\usepackage[table]{xcolor}
{{if $.Cfg.Debug.ShowFrame}}\usepackage{showframe}{{end}}
\usepackage{calc}
\usepackage{dashrule}
\usepackage{setspace}
\usepackage{array}
\usepackage{tikz}
\usepackage{varwidth}
\usepackage{blindtext}
\usepackage{tabularx}
\usepackage{wrapfig}
\usepackage{makecell}
\usepackage{graphicx}
\usepackage{multirow}
\usepackage{amssymb}
\usepackage{expl3}
\usepackage{leading}
\usepackage{pgffor}
\usepackage{marginnote}
\usepackage{adjustbox}
\usepackage{multido}
\usepackage[most]{tcolorbox}
\usepackage{hyperref}
\usepackage{xhfill}
\usepackage{contour}
\usepackage{ulem}
\usepackage{tabularray}

\usetikzlibrary{patterns}

\usepackage{enumitem,amssymb}
\newlist{todolist}{itemize}{2}
\setlist[todolist]{label=$\square$}

\hypersetup{
    {{- if not .Cfg.Debug.ShowLinks}}hidelinks=true{{end -}}
}

\geometry{paperwidth={{.Cfg.Layout.Paper.Width}}, paperheight={{.Cfg.Layout.Paper.Height}}}
\geometry{
  top={{.Cfg.Layout.Paper.Margin.Top}},
  bottom={{.Cfg.Layout.Paper.Margin.Bottom}},
  left={{.Cfg.Layout.Paper.Margin.Left}},
  right={{.Cfg.Layout.Paper.Margin.Right}},
  marginparwidth={{.Cfg.Layout.Paper.MarginParWidth}},
  marginparsep={{.Cfg.Layout.Paper.MarginParSep}}
}

\pagestyle{empty}
{{if $.Cfg.Layout.Paper.ReverseMargins}}\reversemarginpar{{end}}
\newcolumntype{Y}{>{\centering\arraybackslash}X}
\parindent=0pt
\fboxsep0pt

\extraheadheight{-.5in}

\begin{document}
{{template "macro.gtpl" .}}
\include{content}
\end{document}

