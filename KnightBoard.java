public class KnightBoard {
  private int[][] board;
  private int rows;
  private int cols;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(5, 5);
    System.out.println(board.toString());
    System.out.println(board.solve(0, 0));
    System.out.println(board.toString());
  }

  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
    for (int[] x : board) {
      for (int y : x) y = 0;
    }
    rows = board.length;
    cols = board[0].length;
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
/*
  public int countSolutions(int startingRow, int startingCol)  {

  }
*/
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[startingRow].length) throw new IllegalArgumentException();
    return solveH(startingRow, startingCol, 1, board);
  }

  private boolean solveH(int row ,int col, int level, int ary[][]) {
    System.out.println("Level: " + level);
    System.out.println("Board:\n " + arrayPrint(ary));
    if (row >= board.length || row < 0 || col >= board[row].length || col < 0 || board[row][col] != 0) return false;
    ary[row][col] = level;
    if (level >= rows * cols) {
      board = ary;
      return true;
    }
    return solveH(row - 2, col + 1, level + 1, ary) || solveH(row - 1, col + 2, level + 1, ary) || solveH(row + 1, col + 2, level + 1, ary) || solveH(row + 2, col + 1, level + 1, ary) || solveH(row + 2, col - 1, level + 1, ary)
    || solveH(row + 1, col + 2, level + 1, ary) || solveH(row - 1, col - 2, level + 1, ary) || solveH(row - 2, col - 1, level + 1, ary);
  }

  private String arrayPrint(int[][] ary) {
    String output = "";
    for (int[] x: ary) {
      for (int y: x) {
        if (y == 0) output += " _ ";
        if (y / 10 == 0 && y != 10) output += " " + y + " ";
        else output += y + " ";
      }
      output = output.substring(0, output.length() - 1);
      output += "\n";
    }
    return output;
  }
}
