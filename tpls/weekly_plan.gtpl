{{ if $.Cfg.Dotted -}}
  {{ template "_common_04_weekly_dotted.gtpl" dict "Cfg" .Cfg "Body" .Body }}
{{- else -}}
  {{ template "_common_04_weekly_lined.gtpl" dict "Cfg" .Cfg "Body" .Body }}
{{- end }}
