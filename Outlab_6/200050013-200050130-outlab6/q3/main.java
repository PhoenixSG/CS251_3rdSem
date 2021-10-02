
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // System.out.print(func1("D46gg"));
        q3 object = new q3();
        while (true) {
            String s[] = object.func4(reader.readLine(), reader.readLine());
            for(int i=0; i<s.length; i++){
                System.out.println(s[i]);
            }
            System.out.println("");
        }
    }
}
