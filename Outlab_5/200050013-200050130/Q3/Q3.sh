#!/bin/bash
cnt=$1
rm -f ham.txt
touch ham.txt
awk '/0:/' sample.txt | while read line || [[ -n $line ]]; do
    tmp=${line#*:} 
    arr=($(echo "$tmp" | tr "|" "\n"))
    for i in "${arr[@]}"; do
        printf "$i\n" >> ham.txt
    done
done
awk -F '-' 'FNR==NR{a[$2]=$1;next} a[$1]{print a[$1] >"ham.txt"}' word_token_mapping.txt ham.txt 
echo $(sort ham.txt | uniq -c | sort -k1,1nr -k2| sed 's/^\s*[0-9]* \(.*\)$/\1/') > ham.txt
touch tmp.txt
cat ham.txt | while read line || [[ -n $line ]]; do
    for i in $line; do
        printf "$i\n" >> tmp.txt
    done
done
head -n$cnt tmp.txt > ham.txt
rm -f tmp.txt

rm -f spam.txt
touch spam.txt
awk '/1:/' sample.txt | while read line || [[ -n $line ]]; do
    tmp=${line#*:} 
    arr=($(echo "$tmp" | tr "|" "\n"))
    for i in "${arr[@]}"; do
        printf "$i\n" >> spam.txt
    done
done
awk -F '-' 'FNR==NR{a[$2]=$1;next} a[$1]{print a[$1] >"spam.txt"}' word_token_mapping.txt spam.txt 
echo $(sort spam.txt | uniq -c | sort -k1,1nr -k2|sed 's/^\s*[0-9]* \(.*\)$/\1/') > spam.txt
touch tmp.txt
cat spam.txt | while read line || [[ -n $line ]]; do
    for i in $line; do
        printf "$i\n" >> tmp.txt
    done
done
head -n$cnt tmp.txt > spam.txt
rm -f tmp.txt

