
\tcbset{
	posterbox/.style={%
		enhanced jigsaw, size=fbox,
		colback=#1!10, colframe=#1!10!black,
		colbacktitle=#1!70!black
	},
}


\begin{tcbposter}[
	coverage={spread outwards,fill downwards,spread inwards,phantom=\thispagestyle{empty}},
	poster={columns=3,rows=5,showframe=false,spacing=6mm},
	fontsize=9pt,
boxes={enhanced,	
right=0pt,
left=0pt,
fit,
		colframe=blue!50!black,colback=blue!10!white,colbacktitle=blue!5!yellow!10!white,
	fonttitle=\small\bfseries,coltitle=black,attach boxed title to top center=
	{yshift=-0.25mm-\tcboxedtitleheight/2,yshifttext=2mm-\tcboxedtitleheight/2},
	boxed title style={boxrule=0.5mm,
		frame code={ \path[tcb fill frame] ([xshift=-4mm]frame.west)
			-- (frame.north west) -- (frame.north east) -- ([xshift=4mm]frame.east)
			-- (frame.south east) -- (frame.south west) -- cycle; },
		interior code={ \path[tcb fill interior] ([xshift=-2mm]interior.west)
			-- (interior.north west) -- (interior.north east)
			-- ([xshift=2mm]interior.east) -- (interior.south east) -- (interior.south west)
			-- cycle;} }}
		]
	
		\posterbox[title=Top Priorities]{span=3,row=1}{    
\begin{enumerate}
%	  \setlength\itemsep{0.3*}
%	  \setlength\itemsep{(\tcbtextheight/5)}
	\item \arulefill
	\item \arulefill
	\item \arulefill
\end{enumerate}
	}

		\posterbox[title=Work Priorities]{row=2}{ 
        \vspace{\mytextsize}  
\arulefill

\vspace{\mytextsize}
		 \arulefill

\vspace{\mytextsize}

	 \arulefill
}

	\posterbox[title=Personal Priorities]{row=2,column=2}{    
	\begin{enumerate}
		%	  \setlength\itemsep{0.3*}
		\setlength\itemsep{(\tcbtextheight/5)}
		\item \arulefill
		\item \arulefill
		\item \arulefill
	\end{enumerate}
}
	\posterbox[title=Habits]{row=2,column=3}{    
	\begin{enumerate}
		%	  \setlength\itemsep{0.3*}
		\setlength\itemsep{(\tcbtextheight/5)}
		\item \arulefill
		\item \arulefill
		\item \arulefill
	\end{enumerate}
}

\posterbox[title=I am looking forward to..., top=3mm, left=0mm, right=0mm]{span=3,row=3,}
{\fillwithdottedlines{\tcbtextheight}}

\posterbox[title=What am I grateful for today?, top=3mm, left=0mm, right=0mm]{span=3,row=4,}
{\fillwithdottedlines{\tcbtextheight}}

	
	
	\posterbox[title=I am looking forward to..., top=3mm, left=0mm, right=0mm]{span=3,row=5,}
		{\fillwithdottedlines{\tcbtextheight}}
		
% 		\posterbox[title=Today I am open to the possibility of..., top=3mm, left=0mm, right=0mm]{span=3,row=6,}	{\fillwithdottedlines{\tcbtextheight}}
	
%		\posterbox[title=Todos and priorities2,		colback=white,			colframe=white,		colbacktitle=white, 		coltitle=black 		]{span=3,row=4}		{\fillwithdottedlines{\tcbtextheight}}


	
\end{tcbposter}