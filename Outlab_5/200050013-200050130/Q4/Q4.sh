#!/bin/bash

awk -v j="$2" -v i="$3" '
BEGIN{
    split(j, time_string, ":")
    start_time=(time_string[1]*3600+time_string[2]*60+time_string[3])
    split(i, time_string, ":")
    end_time=(time_string[1]*3600+time_string[2]*60+time_string[3])
    count=1
    FS="\t"
    OFS="\t"
}
{
    if(NR >1){
        split($3,date_time_string,", ")
        split(date_time_string[2], time_string, ":")
        time_seconds=(time_string[1]*3600+time_string[2]*60+time_string[3])
        if($2=="Joined"){
            if(time_seconds<start_time){
                time_seconds=50400
            }
            time_seconds=-1*time_seconds    
            toggle[$1]=-1    
        }
        if($2=="Left"){
            toggle[$1]=1    
        }
        arr[$1]=arr[$1]+time_seconds
        # print($1, arr[$1])
    }
    
}
END{
    
    for(key in arr){
        if(toggle[key]==-1){
            arr[key]=arr[key]+end_time
        }
        hours = int(arr[key]/3600)
        minutes = int( (arr[key]%3600)/60 )
        seconds = arr[key]%60
        printf("%s\t%02d:%02d:%02d\n",key,hours, minutes, seconds) | "sort"
    }
    
}
' $1 > output.txt

#toggel arr and  time array
# convert time to numbers and add and subtract
# easy logic notmuch
# sort while printing at the end