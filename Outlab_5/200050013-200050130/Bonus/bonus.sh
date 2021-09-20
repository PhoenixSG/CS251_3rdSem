#! /usr/bin/awk -f 

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
                val = val + int($i)
            }
        }
        else if(count==2){
            val2 = 0
            for(i=1; i<=NF; i=i+1){
                val2 = val2 * base_in
                val2 = val2 + int($i)
            }
            val = val+val2
            position = 0
            while(val>0){
                position=position+1
                arr[position] = val%base_out
                val = int(val/base_out)
            }
            for(i=position; i>0; i--){
                printf("%d ", arr[i])
            }
            print("")
        }
    count = (count+1)%3
    }
    
}
END{

}
