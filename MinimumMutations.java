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
        ArrayDeque<String> Qcurrent = new ArrayDeque<String>();
        ArrayDeque<String> Qnext = new ArrayDeque<String>();
        HashMap<String,Boolean> H = new HashMap<String,Boolean>();
        int depth = 0;
        int nvisited = 0;
        Qcurrent.push(this.start);
        if(this.start.equals(this.end)){return 0;}
        while(!Qcurrent.isEmpty()){
            Qnext.clear();
            depth++;
            while(!Qcurrent.isEmpty()){//adds layer
              ArrayList<String> N = getNeighbors(Qcurrent.pop());
              for(String n:N){//adds unseen neighbors to next layer
                  if(n.equals(this.end)){return depth;}
                  if(!H.containsKey(n)){
                     H.put(n,true);
                     Qnext.push(n);
                    }
                }
            }
            Qcurrent = Qnext.clone();
        }
      return -1;
    }
}
