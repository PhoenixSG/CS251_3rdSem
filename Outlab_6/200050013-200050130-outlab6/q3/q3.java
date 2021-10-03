import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class q3 {
    
    public static boolean func1(String s) {

        if (s.length() > 5) {
            return false;
        }

        Pattern input = Pattern.compile("[a-zA-Z0-9]*");
        Matcher list = input.matcher(s);
        if(list.find()){
            return (list.start()==0 && list.end()==s.length());
        }
        else{
            return false;
        }

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
        if(s.length()%2!=0){
            return false;
        }
        Pattern input = Pattern.compile("a+b+");
        Pattern input2 = Pattern.compile("a+");
        Matcher list = input.matcher(s);
        Matcher list2 = input2.matcher(s);
        if(list.find() && list2.find()){
            return (list.start()==0 && list.end()==s.length() && list2.start()==0 && list2.end()==s.length()/2);
        }
        else{
            return false;
        }
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
