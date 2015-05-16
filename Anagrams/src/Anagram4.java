
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class Anagram4 {
    public static void main(String[] args) {
    

        try {
        	long timeStart = System.currentTimeMillis();
        	int numOfClasses = 1;
            AMap<String, List<String>> m = 
                   new AMap<String, List<String>>();
int wn =1;
            Scanner s = new Scanner(new File(args[0]));
            while (s.hasNext()) {
                String word = s.next();
                System.out.println(wn+" "+word);
                wn++;
                String alpha = sorting(word);
                List<String> l = m.get(alpha);
                if (l == null)
                    m.put(alpha, l=new ArrayList<String>());
                l.add(word);
            }

            StringBuilder builder = new StringBuilder();
            
            for (String key : m.keySet()) {
            	List<String> l = m.get(key);
            	for (String st :l) {
            	    builder.append(st);
            	    builder.append(", ");
            	}
            	builder.append("\r\n");
            	numOfClasses++;
            }
            System.out.println("Number of anagram classes: "+numOfClasses);
            builder.append("Number of anagram classes: "+numOfClasses);
            String output = builder.toString();
           
          
         // write  output file 
    		File report = new File("output");
    		FileWriter writer;
    		try {
    			writer = new FileWriter(report,true);

    			writer.write(output);

    			writer.flush();
    		} catch (IOException e) {
    			System.out.println("FAILED TO WRITE FILE");
    			e.printStackTrace();
    		}
    		long timeFinish = System.currentTimeMillis();
    		System.out.println("Time: "+TimeUnit.MILLISECONDS.toSeconds(timeFinish-timeStart)+" seconds.");
    		System.out.println("Number of anagram classes: "+(numOfClasses-1)+".");
        } 
    catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

    }

    private static String sorting(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}