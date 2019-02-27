public class KnightBoard {
  private int[][] board;
  public int[][] moves;
  private int rows;
  private int cols;
  private int solutions;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(8, 8);
    System.out.println(board.solve(0,0));
    System.out.println(board);
  }

  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
    for (int[] x : board) {
      for (int y : x) y = 0;
    }
    moves = new int[startingRows][startingCols];
    setMoves();
    solutions = 0;
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

  public int countSolutions(int startingRow, int startingCol)  {
    if (startingRow < 0 || startingCol < 0 || startingRow > rows - 1 || startingCol > cols - 1) throw new IllegalArgumentException();
    solveH(startingRow, startingCol, 1, true);
    return solutions;
  }

  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow > rows - 1 || startingCol > cols - 1) throw new IllegalArgumentException();
    if (board.length > 3 && board[0].length > 3) {
          return solveOpt(startingRow, startingCol, 1);
    }
    else {
      solveH(startingRow, startingCol, 1, false);
      return solutions > 0;
    }
  }

  private void solveH(int row ,int col, int level, boolean count) {
    if (! count && solutions > 0) ;
    else if (row > rows - 1 || row < 0 || col > cols - 1 || col < 0);
    else if (board[row][col] != 0);
    else if (addKnight(row, col)) {
      board[row][col] = 1;
      if (checkSolution()) solutions ++;
      else {
        for (int x = 0; x < 8; x ++) {
          int[] coords = moveKnight(x, row, col);
          solveH(coords[0], coords[1], level + 1, count);
        }
      }
      board[row][col] = 0;
    }
  }

  private boolean solveOpt(int row, int col, int level) {
    if (row < 0 || row >= rows || col < 0 || col >= cols) return false;
    if (board[row][col] != 0) return false;
    board[row][col] = level;

    if (level >= rows * cols) return true;
    return true; //Dummy value
  }

  public void setMoves() {
    for (int x = 0; x < rows; x ++) {
      for (int y = 0; y < cols; y ++) {
        moves[x][y] = 8;
        if ((x == 1 || x == rows - 2) || (y == 1 || y == cols - 2)) moves[x][y] = 6;
        if ((x == 1 || x == rows - 2) && (y == 1 || y == cols - 2)) moves[x][y] = 4;
        if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
          if (moves[x][y] == 6) {
            moves[x][y] = 3;
          } else {
            moves[x][y] = 4;
          }
        }
        if ((x == 0 || x == rows - 1) && (y == 0 || y == cols - 1)) moves[x][y] = 2;
      }
    }
  }

  private int[] moveKnight(int direction, int r, int c) {
    int[] ans;
    if (direction == 0) ans = new int[] {r - 2, c + 1};
    else if (direction == 1) ans = new int[] {r - 2, c - 1};
    else if (direction == 2) ans = new int[] {r - 1, c + 2};
    else if (direction == 3) ans = new int[] {r - 1, c - 2};
    else if (direction == 4) ans = new int[] {r + 2, c + 1};
    else if (direction == 5) ans = new int[] {r + 2, c - 1};
    else if (direction == 6) ans = new int[] {r + 1, c + 2};
    else if (direction == 7) ans = new int[] {r + 1, c - 2};
    else ans = new int[] {r, c};
    return ans;
  }

  private boolean checkStuck(int r, int c) {
    if (r + 1 < rows && c + 2 < cols && board[r + 1][c + 2] == 0) return false;
    if (r + 1 < rows && c - 2 >= 0 && board[r + 1][c - 2] == 0) return false;
    if (r + 2 < rows && c + 1 < cols && board[r + 2][c + 1] == 0) return false;
    if (r + 2 < rows && c - 1 >= 0 && board[r + 2][c - 1] == 0) return false;
    if (r - 1 >= 0 && c + 2 < cols && board[r - 1][c + 2] == 0) return false;
    if (r - 1 >= 0 && c - 2 >= 0 && board[r - 1][c - 2] == 0) return false;
    if (r - 2 >= 0 && c + 1 < cols && board[r - 2][c + 1] == 0) return false;
    if (r - 2 >= 0 && c - 1 >= 0 && board[r - 2][c - 1] == 0) return false;
    return true;
  }

  private boolean checkSolution(){
    for (int r = 0; r < rows; r ++){
      for (int c = 0; c < cols; c ++){
        if (board[r][c] == 0){
          return false;
        }
      }
    }
    return true;
  }

  private boolean addKnight(int r, int c){
    if (board[r][c] != 0) {
      return false;
    }
    return true;
  }

}
