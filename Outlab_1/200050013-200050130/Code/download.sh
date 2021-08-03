#!/bin/bash

if [[ $# -ne 2 ]]; then
    echo "Usage: bash download.sh <link to directory> <cut-dirs argument>"
    exit 1
fi

filename=$(basename $1)

wget  -rqx -nH -np --reject "index.html{}, index.html, index.html.tmp" --cut-dirs=$2 $1

if [ $filename != "mock_grading" ]
then
	mv $filename mock_grading
fi
