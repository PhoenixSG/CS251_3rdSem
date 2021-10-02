
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // System.out.print(func1("D46gg"));
        //Main for testing!
        Matrix x = new Matrix(2);
        x.setelem(1, 0, 23);
        x.setelem(0, 1, 20);

        Matrix y = new Matrix(2, 2,34.54F);
        x.add(y).printmatrix();;
        x.matmul(y).printmatrix();;

        x.printmatrix();
    }
}
