import java.util.HashMap;
import java.util.ArrayList;
import java.util.ArrayDeque;
public class MinimumMutations{
   String[] bank;
   String start;
   String end;
  public MinimumMutations(String start,String end,String[] bank){
      this.start = start;
      this.end = end;
      this.bank = bank;
    }
    public ArrayList<String> getNeighbors(String v){
      ArrayList<String> neighbors = new ArrayList<String>();
      for(String b:this.bank){
        int mut = 0;
        for(int i = 0; i < Math.min(b.length(),v.length()); i++){
            if(b.charAt(i) != v.charAt(i)){mut++;}
        }
        if(mut==1){neighbors.add(b);}
      }
      return neighbors;
    }

    public int findMinDistance(){
        ArrayDeque<String> Q = new ArrayDeque<String>();
        String current =this.start;
        int depth = 0;
        while(this.end != current){
            ArrayList<String> N = getNeighbors(current);
            for(String n:N){Q.push(n);}
            current = Q.pop();
            depth++;
        }
      return depth;
    }
}
