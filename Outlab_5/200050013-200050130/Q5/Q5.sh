#!/bin/bash

awk '
BEGIN{
    FS=">"
}
/<div class="field field-name-field-select-state field-type-list-text field-label-above"><div class="field-label">State Name/{
    split($6, name, "<")
    split($14, number, "<")
    arr[name[1]] = number[1]    
}
{
    if(FNR!=NR){
        split($1, array, "\r")
        for (key in arr){
            if(key==array[1]){
                printf("%s %s\r\n",key, arr[key]) | "sort"
            }
        }
    }
}
END{
}' covid_status.html sample.txt > output.txt
