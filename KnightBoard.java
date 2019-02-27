import java.util.Arrays;

public class KnightBoard {
  private int[][] board;
  public int[][] moves;
  private int rows;
  private int cols;
  private int solutions;

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(5, 5);
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
          solveOpt(startingRow, startingCol, 1);
    }
    else {
      solveH(startingRow, startingCol, 1, false);
    }
    return solutions > 0;
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

  private void solveOpt(int row, int col, int level) {
    if (solutions > 0) ;
    else if (row > rows - 1 || row < 0 || col > cols - 1 || col < 0);
    else if (board[row][col] != 0);
    else if (addKnight(row, col)) {
      board[row][col] = 1;
      if (checkSolution()) solutions ++;
      else {
        int[][] possibles = moveKnightOpt(row, col);
        for (int x = 0; x < possibles.length; x ++) {
          if (possibles[x][0] > 0 && possibles[x][1] > 0) {
            solveOpt(possibles[x][0], possibles[x][1], level + 1);
          }
        }
      }
      board[row][col] = 0;
    }
  }

  private void setMoves() {
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

  public int[][] moveKnightOpt(int r, int c) {
    int[][] possible = new int[8][2];
    for (int x = 0; x < 8; x ++) {
      possible[x] = moveKnight(x, r, c);
    }

    int[] ans = new int[8];
    for (int y = 0; y < 8; y ++) {
      try{
        ans[y] = moves[possible[y][0]][possible[y][1]];
      }
      catch(ArrayIndexOutOfBoundsException e) {
        ans[y] = -1;
      }
    }
    doubleSort(ans, possible);

    for (int z = 0; z < 8; z ++) {
      if (ans[z] == -1 || ans[z] == 0) {
        possible[z] = new int[]{-1, -1};
      }
    }
    return possible;
  }

  private static void doubleSort(int ary[], int[][] follower) {
    for (int x = 1; x < ary.length; x ++) {
      int target = ary[x];
      int[] target2 = follower[x];
      for (int y = x - 1; y >= 0; y --) {
        if (target < ary[y]) {
          ary[y + 1] = ary[y];
          follower[y + 1] = follower[y];
          if (y == 0) {
            ary[0] = target;
            follower[0] = target2;
          }
          else {
          ary[y + 1] = target;
          follower[y + 1] = target2;
          y = -1;
          }
        }
      }
    }
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
