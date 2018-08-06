class Driver{
  public static void main(String[] args){
      String start = "AACCGGTT";
      String end = "AAACGGTA";
      //String v = "AAAT";
      String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
      MinimumMutations M = new MinimumMutations(start,end,bank);
      System.out.println(M.findMinDistance());
  }
}
