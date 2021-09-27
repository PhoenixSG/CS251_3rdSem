import java.io.*;
import java.util.*;
import java.util.stream.*;

public class q2{
    
    public static void main(String[] args) throws IOException{
        HashMap<Character, Integer> freqTable = new HashMap<Character, Integer>();
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        int ch;
        while((ch = r.read()) != -1){             
            if(('a' <= ch) && (ch <= 'z')) {
                char elem = (char) ch;
                if(freqTable.get(elem) != null){
                    int value = freqTable.get(elem);
                    value++;
                    freqTable.put(elem,value);
                }
                else freqTable.put(elem,1);
            }
            else if(('A' <= ch) && (ch <= 'Z')){
                ch += ('a' - 'A');
                char elem = (char) ch;
                if(freqTable.get(elem) != null){
                    int value = freqTable.get(elem);
                    value++;
                    freqTable.put(elem,value);
                }
                else freqTable.put(elem,1);

            }          
        }
        LinkedHashMap<Character, Integer> result = 
        freqTable.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
                             .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        int init = -1, it = 0;
        String answer = "";
        for (Character name: result.keySet()) {
            ++it;
            String key = name.toString();
            Integer val = result.get(name);
            String value = val.toString();
            if(val == init) answer += ("," + key + "=" + value);
            else if(it == 1) answer += (key + "=" + value);
            else answer += ("\n" + key + "=" + value);
            init = val;
        }
        Writer br = new FileWriter(new File("output.txt"));
        br.write(answer);
        br.flush(); 
        br.close();
    }   
}