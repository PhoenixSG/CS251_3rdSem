#!/bin/bash

touch marksheet.csv
touch distribution.txt


cd organised/

find . -maxdepth 1 -mindepth 1 -type d -printf '%f\n' | while read line || [[ -n $line ]]; do

   for j in $line/*; do
    #echo $j 
    mkdir -p $line/student_outputs
    marks=0
    if [[ $j == $line*.cpp ]]; then
    	#echo $j "check"
		g++ -o $line/executable $j  2>/dev/null		
		#echo "first"
		for iter in ../mock_grading/inputs/*;do
			filename=$(basename $iter .in)
			#echo $iter
			#echo $filename
			./$line/executable  < $iter > $line/student_outputs/$filename.out 2>/dev/null & sleep 1
			cmp -s $line/student_outputs/$filename.out ../mock_grading/outputs/$filename.out
			ans=$?
			#echo $ans
			if [[ $ans==0 ]]
			then
				marks=$(($marks+1))
				#echo $marks
			fi
			#echo "here"
		done
		printf "$line,$marks\n" >> ../marksheet.csv
    fi   
done    
done



