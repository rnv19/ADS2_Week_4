import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 8;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform() {
        // read the input
		String in = BinaryStdIn.readString();
		CircularSuffixArray suffixArray = new CircularSuffixArray(in);

		int temp = suffixArray.length();

		// find original position
        for (int i = 0; i < temp; i++) 
        {
            if (suffixArray.index(i) == 0) 
            {
				BinaryStdOut.write(i);
				break;
			}
		}

		// the write the suffix
        for (int i = 0; i < temp; i++) 
        {
			int pos = (suffixArray.index(i) + temp - 1) % temp;
            if (pos < 0) 
            {
				pos += temp;
			}
			BinaryStdOut.write(in.charAt(pos), R);
		}

		BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
		String s = BinaryStdIn.readString();
		char[] temp = s.toCharArray();

		// map list of positions for each characters 
		Map<Character, Deque<Integer>> posChar = new HashMap<Character, Deque<Integer>>();
        for (int i = 0; i < temp.length; i++ )
        {
			Deque<Integer> deq = posChar.get(temp[i]);
			if (deq == null) {
				deq = new LinkedList<Integer>();
				posChar.put(temp[i], deq);
			}
			deq.add(i);
		}
		
		// sort last word
		Arrays.sort(temp);
		
		// construct the next[] array
		int[] nextArray = new int[temp.length];
        for (int i = 0; i < temp.length; i++) 
        {
			Deque<Integer> deq = posChar.get(temp[i]);
			nextArray[i] = deq.removeFirst();
		}
		
		// reconstruct string
		int current = first;
        for (int i = 0; i < temp.length; i++) 
        {
			char c = temp[current];
			BinaryStdOut.write(c);
			current = nextArray[current];
		}

		BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        
    }

}
