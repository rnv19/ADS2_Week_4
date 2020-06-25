import edu.princeton.cs.algs4.SuffixArrayX;
import java.util.Arrays;

public class CircularSuffixArray {
	
    //	private SuffixArrayX arr;
    private int len;
    private int[] pos;
    private String str;
    
    private class Node implements Comparable<Node> {
        private int index;
        
        public Node(int index) 
        {
            this.index = index;
        }		

        public int getIndex() 
        { 
            return index; 
        }		
        
        @Override
        public int compareTo(Node node) 
        {
            int j = node.getIndex();
            int temp = length();
            for (int i = 0; i < temp; i++) 
            {
                int p1 = (i + index + temp) % temp;
                int p2 = (i + j + temp) % temp;

                if (str.charAt(p1) != str.charAt(p2))
                {
                    return str.charAt(p1) - str.charAt(p2);
                }
            }
            return 0;
        }
        
    }
    
    public CircularSuffixArray(String string) {
        if (string == null) 
        {
            throw new IllegalArgumentException();
        }  	

        this.pos = new int[string.length()];
        this.len = string.length();
        this.str = string;
        Node[] suffix = new Node[string.length()];
        
        for (int i = 0; i < suffix.length; i++) 
        {
            suffix[i] = new Node(i);
        }
        Arrays.sort(suffix);
        for (int i = 0; i < pos.length; i++) 
        {    		
            pos[i] = suffix[i].getIndex();
        }
    } // end constructor
    
    public int length() {
        // length of s
        return this.len;
    }
    
    public int index(int i) {
        // returns index of ith sorted suffix
        if (i < 0 || i >= len) 
        {
            throw new IllegalArgumentException();
        }
        return pos[i];
    }
    
//    just for a test
    public static void main(String[] args) {
        CircularSuffixArray arr = new CircularSuffixArray("ABRACADABRA!");
        for (int i=0; i<arr.length(); i++) 
        {
            System.out.println(arr.index(i));
        }
    }
    
}
