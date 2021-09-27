public class q1
{
    static int sum = 0, product = 1, cnt = 0; 
    public static void main(String[] args)
    {
        for (String val : args) {
            int x = Integer.parseInt(val);
            sum += x;
            product *= x;
            cnt += 1;
        }
        System.out.print(cnt);
        System.out.print(",");
        System.out.print(sum);
        System.out.print(",");
        System.out.print(product);
    }
}