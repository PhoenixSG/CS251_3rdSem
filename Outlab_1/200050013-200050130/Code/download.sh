#!/bin/bash

if [[ $# -ne 2 ]]; then
    echo "Usage: bash download.sh <link to directory> <cut-dirs argument>"
    exit 1
fi

wget -rqx -nH -np --reject "index.html{}, index.html, index.html.tmp" --cut-dirs=$2 $1

