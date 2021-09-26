sed -e 's!www.[^[:space:]]*!!g' -e 's!http\(s\)\{0,1\}://[^[:space:]]*!!g' -e 's/.*/\L&/g' -e 's/^ *\| *$//g'  -e "s/[[:punct:]]\+//g" -re 's/[^[:space:]]*[0-9][^[:space:]]* ?//g' -re 's/\b\w{1,2}\s?\b//g' <sample.txt >output.txt
mapfile -t array < stopwords.txt 
for i in "${array[@]}"
do
    sed -i "s/\<$i\>\s*//g" output.txt #Removes stopwords
done 
mapfile -t array2 < suffixes.txt 
for i in "${array2[@]}"
do 
    sed -i "s/${i}\b/A/g" output.txt #Replaces suffixes with dummy character 'A', guaranteed not to occur anywhere else since all letters have been lowercased
done 
sed -i -e "s/A\+//g" output.txt #Removes the 'A's introduced above (The 'A's basically ensure that each word is stemmed atmost once)
sed -i -re 's/\b\w{1,2}\s?\b//g' output.txt
sed -i -e 's/[[:blank:]]*$//' output.txt #Trims spaces at end of lines
sed -i -e '/^[[:space:]]*$/d' output.txt #Trims spaces at beginnings of lines
sed -i -e "s/[[:space:]]\{2,\}/ /g" output.txt #Makes spacing uniform

