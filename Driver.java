import java.io.FileInputStream;
class Driver{
  public static void main(String[] args){
      String start = "AACCGGTT";
      String end = "AAACGGTA";
      String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
      //FileInputStream F = new FileInputStream("bank.txt");

      MinimumMutations M = new MinimumMutations(start,end,bank);
      System.out.println(M.findMinDistance());
  }
}
