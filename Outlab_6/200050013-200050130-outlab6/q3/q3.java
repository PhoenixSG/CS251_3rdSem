import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // System.out.print(func1("D46gg"));

        while (true) {
            String s[] = func4(reader.readLine(), reader.readLine());
            for(int i=0; i<s.length; i++){
                System.out.println(s[i]);
            }
            System.out.println("");
        }
    }

    public static boolean func1(String s) {

        if (s.length() > 5) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            // System.out.println(s.charAt(i));
            if (s.charAt(i) <= 'z' && s.charAt(i) >= 'a') {
                continue;
            } else if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') {
                continue;
            } else if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                continue;
            } else {
                return false;
            }
        }
        // System.out.println(s.length());

        return true;
    }


    public static boolean func2(String s) {
        
        Pattern input = Pattern.compile("a*b+c");
        Matcher list = input.matcher(s);
        if(list.find()){
            return (list.start()==0 && list.end()==s.length());
        }
        else{
            return false;
        }
    }

    public static boolean func3(String s) {
        if(s.length()<2){
            return false;
        }
        int i=0, j=0;
        while(s.charAt(i)=='a'){
            i++;
            if(i==s.length()){
                return false;
            }
        }
        while(s.charAt(i+j)=='b'){
            j++;
            if((i+j)==s.length()){
                return i==j;
            }
        }
        return false;
    }
    public static String[] func4(String s, String p) {
        
        Pattern input = Pattern.compile(p);

        Matcher list = input.matcher(s);
        ArrayList<String> array = new ArrayList<>();
        while(list.find()){
            array.add(list.group());
        }
        String normalarray[] = new String[array.size()];
        return array.toArray(normalarray);
    }
}
