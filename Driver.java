class Driver{
  public static void main(String[] args){
      String start = "AAAA";
      String end = "AAAB";
      String[] bank = {start,end};
      MinimumMutations M = new MinimumMutations(start,end,bank);
      System.out.println(M.getNeighbors(start));
  }
}
