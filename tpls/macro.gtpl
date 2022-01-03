\ExplSyntaxOn
\cs_new_eq:NN \Repeat \prg_replicate:nn
\ExplSyntaxOff

 %\makeatletter
 %\renewcommand \dotfill {\leavevmode \cleaders \hb@xt@ 1.5em{\hss .\hss }\hfill \kern \z@}
 %\makeatother

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

\newcommand{\myUnderlinedSectionBoxedTwo}[2]{
\begin{tcolorbox}[
  colback=backgroundHighlight,
  height fill
]
  \#1
  \tcblower
  \#2
\end{tcolorbox}
}

\newcommand{\myUnderlinedSectionFull}[2]{
  \myUnderline{#2}
  \fillwithdottedlines{\stretch{1}}
  \medskip
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

 \newcommand{\arulefill}{%
 	\phantom{a}\xrfill[-1ex]{0.4pt}[black]\phantom{a}
 }
 
 \makeatletter
 \renewcommand \dotfill {\leavevmode \cleaders \hb@xt@ 2em{\hss .\hss }\hfill \kern \z@}
 \makeatother

% from: https://gist.github.com/duetosymmetry/e3ee991065be6e35e02ecf2fcc3d9fad

%%-----------------------------------------------------------------------
%% This section sets up a routine for filling the squares in a
%% grid with null lines.
%%-----------------------------------------------------------------------
  \newcommand{\squaresize}{0.15in}
  \pgfdeclarepatternformonly
    {lightcones}% name
    {\pgfpointorigin}% lower left
    {\pgfpoint{\squaresize}{\squaresize}}%  upper right
    {\pgfpoint{\squaresize}{\squaresize}}%  tile size
    {% shape description
     \pgfsetlinewidth{0.4pt}
     \pgfpathmoveto{\pgfpoint{0in}{0in}}
     \pgfpathlineto{\pgfpoint{\squaresize}{\squaresize}}
     \pgfpathmoveto{\pgfpoint{0in}{\squaresize}}
     \pgfpathlineto{\pgfpoint{\squaresize}{0in}}
     \pgfusepath{stroke}
    }

%%-----------------------------------------------------------------------
%% This section sets up a routine for filling a region with dots
%%-----------------------------------------------------------------------
  % Re-use the quantity \squaresize defined above
  \def\dotsize{.7pt}
  \pgfdeclarepatternformonly
    {dotgrid}% name
    {\pgfpoint{-0.5*\squaresize}{-0.5*\squaresize}}% lower left
    {\pgfpoint{0.5*\squaresize}{0.5*\squaresize}}%  upper right
    {\pgfpoint{\squaresize}{\squaresize}}%  tile size
    {% shape description
     \pgfpathcircle{\pgfqpoint{0pt}{0pt}}{\dotsize}
     \pgfusepath{fill}
    }


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Commands for working in poster env
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\newcommand{\posterboxContentsThreeline}{
		\vspace{\mytextsize}
		\arulefill

		\vspace{\mytextsize}
		\arulefill

		\vspace{\mytextsize}

		\arulefill
    \vspace{\mytextsize}
  }

  \newcommand{\posterboxFullWidthBox}[2]{
  		\posterbox[
			title=#2, 
		]{span=12,row=#1,}{}
  }

   \newcommand{\posterboxHalfWidthBox}[2]{
  		\posterbox[
			title=#2, 
		]{span=6,row=#1,}{}
  }

  \newcommand{\posterboxFullWidthLinedBoxHelper}[3]{
  		\posterbox[
			title=#3, 
			underlay={            
				\begin{tcbclipinterior}
		    		\coordinate (X) at ([xshift=0,yshift=-5mm]frame.north west);
					\draw[help lines, ystep=\baselineskip, xstep=\linewidth] (X) grid (frame.south east);
				\end{tcbclipinterior}
			}
		]{span=#2,row=#1,}{}
  }

  \newcommand{\posterboxFullWidthLinedBox}[2]{
    \posterboxFullWidthLinedBoxHelper{#1}{12}{#2}
  }

  \newcommand{\posterboxHalfWidthLinedBox}[2]{
      \posterboxFullWidthLinedBoxHelper{#1}{6}{#2}
  }


  \newcommand{\posterboxFullWidthThreeEnums}[2]{
  	\posterbox[title=#2,valign=center]{span=12,row=#1}{
      \begin{enumerate}
        %	  \setlength\itemsep{0.3*}
        %	  \setlength\itemsep{(\tcbtextheight/5)}
        \item \arulefill
        \item \arulefill
        \item \arulefill
      \end{enumerate}
    }
  }

  \newcommand{\posterboxThreeColumnThreeLine}[4]{
    \posterbox[
      title=#2,
      valign=center
    ]{row=#1,span=4}{
      \posterboxContentsThreeline
    }

  \posterbox[
      title=#3,
      valign=center
    ]{row=#1,column=5,span=4}{
      \posterboxContentsThreeline
    }

    \posterbox[
      title=#4,
      valign=center
    ]{row=#1,column=9,span=4}{
        \posterboxContentsThreeline
    }
  }

\tcbset{
  emptyBox/.style={
      enhanced,
      add to height=-3mm,
      top=-3mm,
      colframe=white,
      attach boxed title to top left,
      boxed title style={empty},
  },
  fancyBlueBox/.style={
        enhanced,
				right=0pt,
				left=0pt,
				fit,
				colframe=blue!50!black,
				colback=blue!10!white,
				colbacktitle=blue!5!yellow!10!white,
				fonttitle=\small\bfseries,
				coltitle=black,
				attach boxed title to top center={yshift=-0.25mm-\tcboxedtitleheight/2,yshifttext=2mm-\tcboxedtitleheight/2},
				boxed title style={boxrule=0.5mm,
						frame code={ \path[tcb fill frame] ([xshift=-4mm]frame.west)
								-- (frame.north west) -- (frame.north east) -- ([xshift=4mm]frame.east)
								-- (frame.south east) -- (frame.south west) -- cycle; },
						interior code={ \path[tcb fill interior] ([xshift=-2mm]interior.west)
								-- (interior.north west) -- (interior.north east)
								-- ([xshift=2mm]interior.east) -- (interior.south east) -- (interior.south west)
								-- cycle;} }
  }
}

\newcommand{\posterboxBareBox}[3]{
  \posterbox[
      emptyBox,
      title=#2
    ]{
    #1}{
      #3  
    }
}

\newcommand{\posterboxBareBoxWithDots}[2]{
  \posterbox[
      emptyBox,
      title=#2,
      overlay={            
				\begin{tcbclipinterior}
		    		\coordinate (X) at ([xshift=0,yshift=-5mm]frame.north west);
            \fill [pattern=dotgrid] (0,0) rectangle (8.5in,11in);
				% (frame.south east)
				\end{tcbclipinterior}
			}
    ]{
    #1}{
 
    }
}
