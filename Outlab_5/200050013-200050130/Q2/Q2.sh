BEGIN{
    count=1
}
{
    for (i=1; i<=NF; i=i+1){
        if(arr[$i]==0){
            arr[$i] = count
            count= count+1
        }
        printf("%d ",arr[$i]-1)
    }
    print("")
}
