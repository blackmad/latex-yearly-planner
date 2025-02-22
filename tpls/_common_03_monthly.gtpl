{{- template "monthTabularV2.gtpl" dict "Month" .Body.Month "Large" true -}}
\medskip

{{ if $.Cfg.Dotted -}}
\myUnderline{Notes}
\vbox to 0pt{\myMash[\myMonthlySpring]{20}{\myNumDotWidthFull}}
{{- else -}}
\parbox{\myLenTwoCol}{
  \myUnderline{Notes}
  \vbox to \dimexpr\textheight-\pagetotal-\myLenLineHeightButLine\relax {%
    \leaders\hbox to \linewidth{\textcolor{\myColorGray}{\rule{0pt}{\myLenLineHeightButLine}\hrulefill}}\vfil
  }%
}%
\hspace{\myLenTwoColSep}%
\parbox{\myLenTwoCol}{
  \myUnderline{Notes}
  \vbox to \dimexpr\textheight-\pagetotal-\myLenLineHeightButLine\relax {%
    \leaders\hbox to \linewidth{\textcolor{\myColorGray}{\rule{0pt}{\myLenLineHeightButLine}\hrulefill}}\vfil
  }%
}
{{- end}}
