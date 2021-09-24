#!/bin/bash

awk '
BEGIN{
    count=1
}
{
    if(arr[$1]==0){
        arr[$1] = count
        count= count+1
    }
    printf("%d",arr[$1]-1)
    for (i=2; i<=NF; i=i+1){
        if(arr[$i]==0){
            arr[$i] = count
            count= count+1
        }
        printf(" %d",arr[$i]-1)
    }
    print("")
}' sample.txt > output.txt