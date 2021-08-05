#!/bin/bash

rm -f marksheet.csv
rm -f distribution.txt
touch marksheet.csv
touch distribution.txt


cd organised/

cat ../mock_grading/roll_list | while read line || [[ -n $line ]]; do

   for j in $line/*; do
   	rm -rf $line/student_outputs
    mkdir -p $line/student_outputs
    marks=0
    if [[ $j == $line*.cpp ]]; then
		g++ -o $line/executable $j  2>/dev/null	
		for iter in ../mock_grading/inputs/*;do
			filename=$(basename $iter .in)
            function commd()
            {
                #[ ! -f /$line/executable ] && touch $line/student_outputs/$filename.out
                EXC=./$line/executable
                if test -f "$EXC"; then
                	timeout 5 ./$line/executable  < $iter > $line/student_outputs/$filename.out 2>/dev/null
                	#echo "1"
                	#echo $EXC
                else
                	touch $line/student_outputs/$filename.out
                	#echo "2"
                	#echo $EXC
                fi
            }           
            commd
            cmp -s $line/student_outputs/$filename.out ../mock_grading/outputs/$filename.out
			ans=$?
			if [[ $ans -eq 0 ]]
			then
				let marks++
			fi
		done
        printf "$line,$marks\n" >> ../marksheet.csv
    fi   
done    
done
rm -f ../organised/nul
cd ..
while IFS=, read -r col1 col2
do
    echo $col2 >> distribution.txt
done < marksheet.csv

sort -nr -o distribution.txt distribution.txt
