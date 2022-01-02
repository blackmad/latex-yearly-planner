\ExplSyntaxOn
\cs_new_eq:NN \Repeat \prg_replicate:nn
\ExplSyntaxOff

 \makeatletter
 \renewcommand \dotfill {\leavevmode \cleaders \hb@xt@ 1.5em{\hss .\hss }\hfill \kern \z@}
 
 \makeatother

\newcommand{\myMinLineHeight}[1]{\parbox{0pt}{\vskip#1}}
\newcommand{\myDummyQ}{\textcolor{white}{Q}}

{{- $numbers := .Cfg.Layout.Numbers -}}
\newcommand{\myNumArrayStretch}{ {{- $numbers.ArrayStretch -}} }
\newcommand{\myNumQuarterlyLines}{ {{- $numbers.QuarterlyLines -}} }
\newcommand{\myNumDotHeightFull}{ {{- $numbers.DotHeightFull -}} }
\newcommand{\myNumDotWidthFull}{ {{- $numbers.DotWidthFull -}} }
\newcommand{\myNumDotWidthTwoThirds}{ {{- $numbers.DotWidthTwoThirds -}} }
\newcommand{\myNumWeeklyLines}{ {{- $numbers.WeeklyLines -}} }
\newcommand{\myNumDailyTodos}{ {{- $numbers.DailyTodos -}} }
\newcommand{\myNumDailyNotes}{ {{- $numbers.DailyNotes -}} }
\newcommand{\myNumDailyBottomHour}{ {{- $numbers.DailyBottomHour -}} }
\newcommand{\myNumDailyTopHour}{ {{- $numbers.DailyTopHour -}} }
\newcommand{\myNumDailyDiaryGoals}{ {{- $numbers.DailyDiaryGoals -}} }
\newcommand{\myNumDailyDiaryGrateful}{ {{- $numbers.DailyDiaryGrateful -}} }
\newcommand{\myNumDailyDiaryBest}{ {{- $numbers.DailyDiaryBest -}} }
\newcommand{\myNumDailyDiaryLog}{ {{- $numbers.DailyDiaryLog -}} }
\newcommand{\myNumDailyPersonal}{ {{- $numbers.DailyPersonal -}} }
\newcommand{\myNumTodoLinesInTodoPage}{ {{- $numbers.TodoLinesInTodoPage -}} }

\newlength{\myLenTabColSep}
\newlength{\myLenLineThicknessDefault}
\newlength{\myLenLineThicknessThick}
\newlength{\myLenLineHeightButLine}
\newlength{\myLenTwoColSep}
\newlength{\myLenTwoCol}
\newlength{\myLenTriColSep}
\newlength{\myLenTriCol}
\newlength{\myLenFiveColSep}
\newlength{\myLenFiveCol}
\newlength{\myLenMonthlyCellHeight}
\newlength{\myLenNotesIndexCellHeight}
\newlength{\myLenHeaderResizeBox}
\newlength{\myLenHeaderSideQuartersWidth}
\newlength{\myLenHeaderSideMonthsWidth}

{{- $lengths := .Cfg.Layout.Lengths -}}
\setlength{\myLenTabColSep}{ {{- $lengths.TabColSep -}} }
\setlength{\myLenLineThicknessDefault}{ {{- $lengths.LineThicknessDefault -}} }
\setlength{\myLenLineThicknessThick}{ {{- $lengths.LineThicknessThick -}} }
\setlength{\myLenLineHeightButLine}{ {{- $lengths.LineHeightButLine -}} }
\setlength{\myLenTwoColSep}{ {{- $lengths.TwoColSep -}} }
\setlength{\myLenTwoCol}{\dimexpr.5\linewidth-.5\myLenTwoColSep}
\setlength{\myLenFiveColSep}{ {{- $lengths.FiveColSep -}} }
\setlength{\myLenFiveCol}{\dimexpr.2\linewidth-\myLenFiveColSep}
\setlength{\myLenMonthlyCellHeight}{ {{- $lengths.MonthlyCellHeight -}} }
\setlength{\myLenTriColSep}{ {{- $lengths.TriColSep -}} }
\setlength{\myLenTriCol}{\dimexpr.333\linewidth-.667\myLenTriColSep}
\setlength{\myLenNotesIndexCellHeight}{ {{- $lengths.NotesIndexCellHeight -}} }
\setlength{\myLenHeaderResizeBox}{ {{- $lengths.HeaderResizeBox -}} }
\setlength{\myLenHeaderSideQuartersWidth}{ {{- $lengths.HeaderSideQuartersWidth -}} }
\setlength{\myLenHeaderSideMonthsWidth}{ {{- $lengths.HeaderSideMonthsWidth -}} }

\newcommand{\myQuarterlySpring}{ {{- $lengths.QuarterlySpring -}} }
\newcommand{\myMonthlySpring}{ {{- $lengths.MonthlySpring -}} }
\newcommand{\myDailySpring}{ {{- $lengths.DailySpring -}} }
\newcommand{\myColorGray}{ {{- .Cfg.Layout.Colors.Gray -}} }
\newcommand{\myColorLightGray}{ {{- .Cfg.Layout.Colors.LightGray -}} }

\definecolor{backgroundHighlight}{HTML}{ {{- .Cfg.Layout.Colors.BackgroundHighlight -}} }
\definecolor{primaryTextColor}{HTML}{ {{- .Cfg.Layout.Colors.TextColor -}} }

\newcommand{\myLinePlain}{\hrule width \linewidth height \myLenLineThicknessDefault}
\newcommand{\myLineThick}{\hrule width \linewidth height \myLenLineThicknessThick}

\renewcommand{\ULdepth}{1.8pt}
\contourlength{0.8pt}

\newcommand{\myUnderlineInBox}[1]{%
  \uline{\phantom{#1}}%
  \llap{\contour{backgroundHighlight}{#1}}%
}

\newcommand{\myUnderline}[1]{%
  \uline{\phantom{#1}}%
  \llap{\contour{white}{#1}}%
}

\newcommand{\myLineHeightButLine}{\myMinLineHeight{\myLenLineHeightButLine}}
%\newcommand{\myUnderline}[1]{#1\vskip1mm\myLineThick\par}
\newcommand{\myLineColor}[1]{\textcolor{#1}{\myLinePlain}}
\newcommand{\myLineGray}{\myLineColor{\myColorGray}}
\newcommand{\myLineLightGray}{\myLineColor{\myColorLightGray}}
\newcommand{\myLineGrayVskipBottom}{\myLineGray\vskip\myLenLineHeightButLine}
\newcommand{\myLineGrayVskipTop}{\vskip\myLenLineHeightButLine\myLineGray}

\newcommand{\myTodo}{\myLineHeightButLine$\square$\myLinePlain}
\newcommand{\myTodoLineGray}{\myLineHeightButLine$\square$\myLineGray}

\newcommand{\myDotGrid}[2]{\leavevmode\multido{\dC=0mm+5mm}{#1}{\multido{\dR=0mm+5mm}{#2}{\put(\dR,\dC){\circle*{0.1}}}}}


\newcommand{\myMashWithoutSkip}[3][]{
  {{- if $.Cfg.Dotted -}} \myDotGrid{#2}{#3} {{- else -}} \Repeat{#2}{\myLineGrayVskipTop} {{- end -}}
}

\newcommand{\myMash}[3][]{
  {{- if $.Cfg.Dotted -}} \vskip\myLenLineHeightButLine#1\myDotGrid{#2}{#3} {{- else -}} \Repeat{#2}{\myLineGrayVskipTop} {{- end -}}  
}

\newcommand{\remainingHeight}{%
  \ifdim\pagegoal=\maxdimen
  \dimexpr\textheight-9.4pt\relax
  \else
  \dimexpr\pagegoal-\pagetotal-\lineskip-9.4pt\relax
  \fi%
}

\newcommand{\myUnderlinedSection}[3]{
\myUnderlineInBox{#2}
\myMash{#1}{#3}
\medskip
}

\newcommand{\myUnderlinedLinedSectionBoxed}[1]{
\begin{tcolorbox}[
  colback=backgroundHighlight
]
  \myUnderlineInBox{#1}
  \fillwithdottedlines{\stretch{1}}
\end{tcolorbox}

}

\newcommand{\myUnderlinedSectionBoxed}[1]{
\begin{tcolorbox}[
  colback=backgroundHighlight,
  height=3cm,
]
\myUnderlineInBox{#1}
\end{tcolorbox}
}

\newcommand{\myUnderlinedSectionBoxedTwo}[3][\stretch{1}][xxxx]{
\begin{tcolorbox}[
  colback=backgroundHighlight,
  height fill
]
  \#3
  \tcblower
  \#2
\end{tcolorbox}
}

\newcommand{\myUnderlinedSectionFull}[2]{
  \myUnderline{#2}
  \fillwithdottedlines{\stretch{1}}
  \medskip
}


\newcommand{\arulefill}{%
  \phantom{a}\xrfill[-1ex]{0.4pt}[black]\phantom{a}
}

\makeatletter
\def\lowerdotfill{%
  \phantom{a}\leavevmode
  \cleaders \hb@xt@ .44em{\hss\lower0.5ex\hbox{.}\hss}\hfill
  \kern\z@\phantom{a}
  }
\makeatother

\newcommand{\myUnderlinedSectionNumberedList}[1]{
  \begin{tcolorbox}[
    colback=backgroundHighlight,
    title=#1
  ]
    \begin{enumerate}
      \item \arulefill
      \item \arulefill
      \item \arulefill
    \end{enumerate}
  \end{tcolorbox}
}


\newcommand{\myUnderlinedSectionTodoList}[1]{
  \begin{tcolorbox}[
    colback=backgroundHighlight,
    title=#1
  ]
    \begin{todolist}
      \item \arulefill
      \item \arulefill
    \end{todolist}
  \end{tcolorbox}
}

\newcommand{\myColoredTitleLinedSectionBoxed}[2]{
\begin{tcolorbox}[
  boxrule=0pt,
  frame hidden,
  colback=white,
]
  \begin{tcolorbox}[
    colback=backgroundHighlight,
    size=fbox,
    boxrule=0mm,
    hbox
  ]
    #1
  \end{tcolorbox}
  \vspace{3mm}
  \hspace{-5mm}\myMashWithoutSkip{#2}{\myNumDotWidthFull}
  \medskip
\end{tcolorbox}
}

\NewColumnType{Z}[1][]{Q[co=1,h,#1]}

\newcommand{\threeColumnHeadingBox}[1]{
  \begin{tblr}{
    colspec = {ZZZ}, colsep = 3mm, rowsep = 3mm, 
    vlines = {2pt, white},
    rows = {backgroundHighlight,ht=3cm}
  }
    #1
  \end{tblr}
  \medskip
}