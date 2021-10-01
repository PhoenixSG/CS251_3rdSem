import java.io.*;
import java.util.*;
import java.util.stream.*;

public class q2{
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("input.txt")) ;
			String str, answer = "";
			while ((str = br.readLine()) != null) {
                  HashMap<Character, Integer> freqTable = new HashMap<Character, Integer>();
                  for(int i = 0; i < str.length(); ++i){
                        char ch = str.charAt(i);
                        if(('a' <= ch) && (ch <= 'z')) {
                            if(freqTable.get(ch) != null){
                                int value = freqTable.get(ch);
                                value++;
                                freqTable.put(ch,value);
                            }
                            else freqTable.put(ch,1);
                        }
                        else if(('A' <= ch) && (ch <= 'Z')){
                            ch += ('a' - 'A');
                            if(freqTable.get(ch) != null){
                                int value = freqTable.get(ch);
                                value++;
                                freqTable.put(ch,value);
                            }
                            else freqTable.put(ch,1);

                        } 
                    }         
                    LinkedHashMap<Character, Integer> result = freqTable.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
                    .thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                    int init = result.entrySet().iterator().next().getValue(), iter = 0;
                    for (Character name: result.keySet()) {
                        ++iter;
                        if(result.get(name) == init) {
                            if(iter == 1) answer += (name + "=" + result.get(name));
                            else answer += ("," + name + "=" + result.get(name));
                        }
                        else break;
                    }
                    answer += ("\n");
			}
            //System.out.print(answer);
            Writer wr = new FileWriter(new File("output.txt"));
            wr.write(answer);
            wr.flush();
            wr.close();
    }
}


