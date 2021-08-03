#!/bin/bash

mkdir -p organised

cat mock_grading/roll_list | while read line || [[ -n $line ]]; do
   mkdir -p organised/$line
done

cat mock_grading/roll_list | while read line || [[ -n $line ]]; do
   for j in mock_grading/submissions/*; do
    #echo $j 
    if [[ $j == *$line* ]]; then
        str1=$j
        str2=$j
        str2="$../../$str2" 
        str2=${str2:1}    
        #echo $str2
        ln -sf $str2 ./organised/$line/${str1##*/}  
    fi   
done    
done
