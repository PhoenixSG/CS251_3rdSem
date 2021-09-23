#!/bin/bash
v=$1
rm -f ham.txt
touch ham.txt
touch tmp.txt
awk -F "|" '/0:/ {print substr($0,3)}' sample.txt | awk '
BEGIN{
FS = "|";
}
{
    for(i = 1; i <= NF; i++){
        if($i in arr) arr[$i]++;
        else arr[$i] = 1;
    }
}
END{
for (i in arr) for(j = 0; j < arr[i]; ++j) print i
}
' > ham.txt 
awk -F '-' 'FNR==NR{a[$2]=$1;next} a[$1]{print a[$1] >"ham.txt"}' word_token_mapping.txt ham.txt 
echo $(sort ham.txt | uniq -c | sort -k1,1nr -k2| sed 's/^\s*[0-9]* \(.*\)$/\1/') > ham.txt #Sorting deliberately done through bash and not awk due to portability #issues
awk -v v="$v" '
BEGIN{
FS = " ";
}
{
    if(NF > v) for(i = 1; i <= v; i++) print $i;
    else for(i = 1; i <= NF; i++) print $i;
}
' ham.txt > tmp.txt
mv tmp.txt ham.txt
rm -f tmp.txt

rm -f spam.txt
touch spam.txt
touch tmp.txt
awk -F "|" '/1:/ {print substr($0,3)}' sample.txt | awk '
BEGIN{
FS = "|";
}
{
    for(i = 1; i <= NF; i++){
        if($i in arr) arr[$i]++;
        else arr[$i] = 1;
    }
}
END{
for (i in arr) for(j = 0; j < arr[i]; ++j) print i
}
' > spam.txt 
awk -F '-' 'FNR==NR{a[$2]=$1;next} a[$1]{print a[$1] >"spam.txt"}' word_token_mapping.txt spam.txt 
echo $(sort spam.txt | uniq -c | sort -k1,1nr -k2| sed 's/^\s*[0-9]* \(.*\)$/\1/') > spam.txt #Sorting deliberately done through bash and not awk due to portability #issues
awk -v v="$v" '
BEGIN{
FS = " ";
}
{
    if(NF > v) for(i = 1; i <= v; i++) print $i;
    else for(i = 1; i <= NF; i++) print $i;
}
' spam.txt > tmp.txt
mv tmp.txt spam.txt
rm -f tmp.txt
