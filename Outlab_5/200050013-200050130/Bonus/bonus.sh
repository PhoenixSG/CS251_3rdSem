#!/bin/bash

awk '
BEGIN{
    count=0
}
{
    if(NR==1){
        size = int($1)
    }
    else{
        if(count==0){
            base_in = int($1)
            base_out = int($2)
        }
        else if(count==1){
            val = 0
            for(i=1; i<=NF; i=i+1){
                val = val * base_in
                split($i, array, "\r")
                if(array[1]=="A"){
                    val = val + 10
                }
                else if(array[1]=="B"){
                    val = val + 11
                }
                else if(array[1]=="C"){
                    val = val + 12
                }
                else if(array[1]=="D"){
                    val = val + 13
                }
                else if(array[1]=="E"){
                    val = val + 14
                }
                else if(array[1]=="F"){
                    val = val + 15
                }
                else
                {
                    val = val + int($i)
                }
            }
        }
        else if(count==2){
            val2 = 0
            for(i=1; i<=NF; i=i+1){
                val2 = val2 * base_in
                split($i, array, "\r")
                if(array[1]=="A"){
                    val2 = val2 + 10
                }
                else if(array[1]=="B"){
                    val2 = val2 + 11
                }
                else if(array[1]=="C"){
                    val2 = val2 + 12
                }
                else if(array[1]=="D"){
                    val2 = val2 + 13
                }
                else if(array[1]=="E"){
                    val2 = val2 + 14
                }
                else if(array[1]=="F"){
                    val2 = val2 + 15
                }
                else
                {
                    val2 = val2 + int($i)
                }
            }
            val = val+val2
            position = 0
            while(val>0){
                position=position+1
                arr[position] = val%base_out
                val = int(val/base_out)
            }
            if(arr[position]==10){
                printf("A")
            }
            else if(arr[position]==11){
                printf("B")
            }
            else if(arr[position]==12){
                printf("C")
            }
            else if(arr[position]==13){
                printf("D")
            }
            else if(arr[position]==14){
                printf("E")
            }
            else if(arr[position]==15){
                printf("F")

            }
            else{
                printf("%d", arr[position])
            }
            for(i=position-1; i>0; i--){
                if(arr[i]==10){
                    printf(" A")
                }
                else if(arr[i]==11){
                    printf(" B")
                }
                else if(arr[i]==12){
                    printf(" C")
                }
                else if(arr[i]==13){
                    printf(" D")
                }
                else if(arr[i]==14){
                    printf(" E")
                }
                else if(arr[i]==15){
                    printf(" F")
                }
                else{
                    printf(" %d", arr[i])
                }
            }
            print("")
        }
    count = (count+1)%3
    }
    
}
END{

}
' bonus_sample_input.txt > bonus_output.txt
