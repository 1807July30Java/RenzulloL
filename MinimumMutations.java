import java.util.HashMap;
import java.util.ArrayList;
public class MinimumMutations{
  static String[] bank;
  static String start;
  static String end;
  static HashMap<String,Double> distances = new HashMap<String,Double>();
  public static void MinimumMutations(String start,String end,String[] bank){
      start = start;
      end = end;
      bank = bank;
      for(String b : bank){distances.put(b,Double.POSITIVE_INFINITY);}
    }
    public ArrayList<String> getNeighbors(String v){
      ArrayList<String> neighbors = new ArrayList<String>();
      for(String b:bank){
        int mut = 0;
        for(int i = 0; i < Math.min(b.length(),v.length()); i++){
            if(b.charAt(i) != v.charAt(i)){mut++;}
        }
        if(mut==1){neighbors.add(v);}
      }
      return neighbors;
    }





}
