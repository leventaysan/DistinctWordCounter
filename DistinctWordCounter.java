package q2;
 
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
 
public class DistinctWordCounter {
    
    public Map<String, Integer> getWordCount(String fileName){
         
        FileInputStream file = null;
        DataInputStream data = null;
        BufferedReader br = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            file = new FileInputStream(fileName);
            data = new DataInputStream(file);
            br = new BufferedReader(new InputStreamReader(data));
            String line = null;
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " ");
                while(st.hasMoreTokens()){
                    String tmp = st.nextToken().toLowerCase();
                    if(wordMap.containsKey(tmp)){
                        wordMap.put(tmp, wordMap.get(tmp)+1);
                    } else {
                        wordMap.put(tmp, 1);
                    }
                }
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }
     
    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){             
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
     
    public static void main(String a[]){
        DistinctWordCounter mdc = new DistinctWordCounter();
        Map<String, Integer> wordMap = mdc.getWordCount("C:/Sample.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        for(Map.Entry<String, Integer> entry:list){
            System.out.println("The number of "+entry.getKey()+" = "+entry.getValue());
        }
    }
}
