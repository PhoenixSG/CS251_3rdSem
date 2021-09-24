sed -e 's!www.[^[:space:]]*!!g' -e 's!http\(s\)\{0,1\}://[^[:space:]]*!!g' -e 's/.*/\L&/g' -e 's/^ *\| *$//g'  -e "s/[[:punct:]]\+//g" -re 's/[^[:space:]]*[0-9][^[:space:]]* ?//g' -re 's/\b\w{1,2}\s?\b//g' <sample.txt >MyOutput.txt
mapfile -t array < stopwords.txt 
for i in "${array[@]}"
do
    sed -i "s/\<$i\>\s*//g" MyOutput.txt #Removes stopwords
done 
mapfile -t array2 < suffixes.txt 
for i in "${array2[@]}"
do 
    sed -i "s/${i}\b//g" MyOutput.txt #Removes suffixes
done 
sed -ie '/^[[:space:]]*$/d' MyOutput.txt
rm -f MyOutput.txte
