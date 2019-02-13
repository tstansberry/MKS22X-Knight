public class KnightBoard {
  private int[][] board;

  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
    for (int[] x : board) {
      for (int y : x) y = 0;
    }
  }

  public String toString() {
    String output = "";
    for (int[] x: board) {
      for (int y: x) {
        if (y / 10 == 0 && y != 10) output += " " + y + " ";
        else output += y + " ";
      }
      output = output.substring(0, output.length() - 1);
      output += "\n";
    }
    return output;
  }

  public int countSolutions(int startingRow, int startingCol)  {

  }

  private boolean solveH(int row ,int col, int level) {

  }
}
