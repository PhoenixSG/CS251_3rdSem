import java.math.BigInteger;
public class q1
{
    public static void main(String[] args)
    {
        BigInteger sum = BigInteger.ZERO;
        BigInteger product = BigInteger.ONE;
        BigInteger cnt = BigInteger.ZERO;


        for (String val : args) {
            BigInteger x = new BigInteger(val);
            sum = sum.add(x);
            product = product.multiply(x);
            cnt = cnt.add(BigInteger.ONE);
        }
        System.out.print(cnt);
        System.out.print(",");
        System.out.print(sum);
        System.out.print(",");
        System.out.print(product);
    }
}
