import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // System.out.print(func1("D46gg"));

        while (true) {
            System.out.print(func3(reader.readLine()));
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
        
        int i=0;
        while(s.charAt(i)=='a'){
            i++;
            if(i==s.length()){
                return false;
            }
        }
        if(s.charAt(i)!='b'){
            return false;
        }
        i++;
        if(i==s.length()){
            return false;
        }
        while(s.charAt(i)=='b'){
            i++;
            if(i==s.length()){
                return false;
            }
        }
        return (s.charAt(i)=='c' && i==s.length()-1);
     
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
    public static boolean func4(String s, String p) {
        //Didn't get what they meant
        return false;
    }
}
