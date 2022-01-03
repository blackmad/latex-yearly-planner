
\begin{tcbposter}[
		coverage={
			spread outwards,fill downwards,spread inwards,
			phantom=\thispagestyle{empty},
			top=0pt,toptitle=0pt,
			bottom=6mm
		},
		poster={columns=12,rows=5,showframe=false,spacing=6mm},
		boxes={enhanced,
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
								-- cycle;} }}
	]

	\posterboxFullWidthThreeEnums{1}{Goals}

	\posterboxThreeColumnThreeLine{2}{Work Priorities}{Personal Priorities}{Habits}

	\posterboxFullWidthLinedBox{3}{I am looking forward to...}

	\posterboxHalfWidthLinedBox{4}{What am I grateful for today?}

	\posterboxHalfWidthBox{5}{Today's positive self-talk}

	\posterboxBareBox{rowspan=2,span=6,row=4,column=7}{Notes}{\fillwithdottedlines{10in}}

\end{tcbposter}