#!/bin/bash

awk '
BEGIN{
    FS=">"
}
/<div class="field field-name-field-select-state field-type-list-text field-label-above"><div class="field-label">State Name/{
    # print($6,"  ", $14)
    split($6, name, "<")
    split($14, number, "<")
    # print(name[1], number[1])
    # for(i=0; i<NF; i=i+1){
    #     print($i)
    # }
    # if(name[1])
    arr[name[1]] = number[1]    
}
{
    # print(arr[$0])
    if(FNR!=NR){
        split($1, array, "\r")
        for (key in arr){
        # if()
            if(key==array[1]){
                printf("%s %s\r\n",key, arr[key]) | "sort"
                # print($0) |"sort"
                # print($0, arr[$0]) | "sort"
            }
        }
    }
}
END{
    
    # print(list)

    # for (key in arr){
    #     # if()
    #     print(key, arr[key])
    # }
    # while((getline state < "sample.txt")>0){
    #     word = state
    #     print(word)
    #     # if(state in arr){
    #         print(word, arr[word]) | "sort"
    #     # }
    # }
}' covid_status.html sample.txt > output.txt
