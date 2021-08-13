#! /bin/bash

pdflatex -interaction=nonstopmode main
bibtex main
pdflatex -interaction=nonstopmode main
pdflatex -interaction=nonstopmode main

