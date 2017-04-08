package demo.cognitive.utils;

/**
 * Created by lisong on 2017/4/8.
 */
public class Base64Convertor {
    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z', 'A' , 'B' ,
            'C' , 'D' , 'E' , 'F' , 'G' , 'H' ,
            'I' , 'J' , 'K' , 'L' , 'M' , 'N' ,
            'O' , 'P' , 'Q' , 'R' , 'S' , 'T' ,
            'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'
    };

    public static void main(String[] args) {
        String url = "dfgfdgfdgfds";
        int x = url.hashCode();
        x = Integer.MAX_VALUE;
        System.out.println(x);
        System.out.println(Integer.toString(x, 36));
        System.out.println(toString(x, 62));
        long maxValue = Long.MAX_VALUE;
        System.out.println(toString(maxValue, 32).length());
        System.out.println( toString(maxValue, 62).length());
    }
    public static String toString36Base(int i) {
        return toString(i, 36);
    }

    public static String toString(int i, int radix) {
        if (radix < 2 || radix > 62)
            radix = 10;

        /* Use the faster version */
        if (radix == 10) {
            return Integer.toString(i);
        }

        char buf[] = new char[33];
        boolean negative = (i < 0);
        int charPos = 32;

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos--] = digits[-(i % radix)];
            i = i / radix;
        }
        buf[charPos] = digits[-i];

        if (negative) {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (33 - charPos));
    }

    public  static String toString(Long i, int radix) {
        if (radix < 2 || radix > 62)
            radix = 10;
        if (radix == 10)
            return Long.toString(i);
        char[] buf = new char[65];
        int charPos = 64;
        boolean negative = (i < 0);

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos--] = digits[(int)(-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = digits[(int)(-i)];

        if (negative) {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (65 - charPos));
    }
}
