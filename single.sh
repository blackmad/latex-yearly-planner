#!/usr/bin/env bash

set -eo pipefail

set -x

if [ -z $CFG ]; then CFG=$1; fi

go run cmd/plannergen/plannergen.go --config "${CFG}"

nakedname=$(echo "${CFG}" | rev | cut -d, -f1 | cut -d'/' -f 1 | cut -d'.' -f 2-99 | rev)

_passes=(1)

if [[ -n "${PASSES}" ]]; then
  # shellcheck disable=SC2207
  _passes=($(seq 1 "${PASSES}"))
fi

for _ in "${_passes[@]}"; do
  pdflatex \
    -file-line-error \
    -interaction=nonstopmode \
    -synctex=1 \
    -output-format=pdf \
    -output-directory=./out \
    "out/${nakedname}.tex"
done

if [ -n "${NAME}" ]; then
  cp "out/${nakedname}.pdf" "${NAME}.pdf"
  echo "created ${NAME}.pdf"
else
  cp "out/${nakedname}.pdf" "${nakedname}.pdf"
  echo "created ${nakedname}.pdf"
fi
