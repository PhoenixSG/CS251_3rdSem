#!/bin/bash


rm -f marksheet.csv
rm -f distribution.txt
touch marksheet.csv
touch distribution.txt


cd organised/

cat ../mock_grading/roll_list | while read line || [[ -n $line ]]; do

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
            #./$line/executable  < $iter > $line/student_outputs/$filename.out 2>/dev/null | cat & sleep 1
			commd=./$line/executable  < $iter > $line/student_outputs/$filename.out 
			(
            ((t = 5))

             while ((t > 0)); do
                sleep 1
                kill -0 commd || exit 0
                ((t -= 1))
            done
            kill -s SIGTERM commd && kill -0 commd || exit 0
            kill -s SIGKILL commd
            ) 2> /dev/null

            cmp -s $line/student_outputs/$filename.out ../mock_grading/outputs/$filename.out
			ans=$?
			#echo $ans
			if [[ $ans -eq 0 ]]
			then
				let marks++
				#echo $marks
			fi
			#echo "here"
		done
		printf "$line,$marks\n" >> ../marksheet.csv
    fi   
done    
done

cd ..
while IFS=, read -r col1 col2
do
    echo $col2 >> distribution.txt
done < marksheet.csv

sort -nr -o distribution.txt distribution.txt

killall -q executable




