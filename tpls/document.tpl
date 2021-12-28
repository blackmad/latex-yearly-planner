\documentclass[9pt]{extarticle}

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

\usepackage{enumitem,amssymb}
\newlist{todolist}{itemize}{2}
\setlist[todolist]{label=$\square$}

\newdimen\longline
\longline=\textwidth\advance\longline-4cm

\def\LayoutTextField#1#2{#2} % override default in hyperref

\def\lbl#1{\hbox to 4cm{#1\dotfill\strut}}%
\def\labelline#1#2{\lbl{#1}\vbox{\hbox{\TextField[name=#1,width=#2,borderwidth=0]{\null}}\kern0pt\hrule}}
\def\q#1{\hbox to \hsize{\labelline{#1}{\longline}}\vskip1.4ex}


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

\begin{document}

{{template "macro.tpl" .}}

\include{content}
\end{document}

