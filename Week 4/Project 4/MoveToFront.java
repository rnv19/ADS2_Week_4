import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int total = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] CharArrayEncode = new char[total];
        for (int i = 0; i < total; i++) 
        {
            CharArrayEncode[i] = (char) i;
        }
        
        String str = BinaryStdIn.readString();
        char[] temp = str.toCharArray();
        
        for (int i = 0; i < temp.length; i++) 
        {
            char t1 = temp[i];
            for (int t2 = 0; t2 < CharArrayEncode.length; t2++) 
            {
                if (CharArrayEncode[t2] == t1) 
                {
                    break;
                }
            }
            
            BinaryStdOut.write((char) t2);
            System.arraycopy(CharArrayEncode, 0, CharArrayEncode, 1, t2);
            CharArrayEncode[0] = t1;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] charArrayDecode = new char[total];
        for (int i = 0; i < total; i++) 
        {
            charArrayDecode[i] = (char) i;
        }
        
        String str = BinaryStdIn.readString();
        String[] temp = str.split(" ");
        
        for (int i = 0; i < total; i++) 
        {
            int n = Integer.parseInt(temp[i]);
            char current = charArrayDecode[n];
            BinaryStdOut.write(current);
            System.arraycopy(charArrayDecode, 0, charArrayDecode, 1, n);
            charArrayDecode[0] = current;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) 
        {
            MoveToFront.encode();
        } else if (args[0].equals("+")) 
        {
            MoveToFront.decode();
        } else 
        {
            throw new IllegalArgumentException();
        }
    }
}
