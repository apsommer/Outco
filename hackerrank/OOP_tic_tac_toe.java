//import com.sun.org.apache.xpath.internal.operations.String;
import java.util.Scanner;
/**
Scanner scanner = new Scanner(System.in);
String myString = scanner.next();
int myInt = scanner.nextInt();
scanner.close();*/
class Game {
Scanner scanner = new Scanner(System.in);


  Board board = new Board();
  String player = "X";

  void startGame() {

    for (int i = 0; i < 9; i ++) {

      int move;
      do {

        // display board
        //board.display();

        // get user input
       String myString =  scanner.next();
        move = scanner.nextInt();
      //  move = Integer.valueOf(System.in()); // user types int 0 > 8

      } while (!board.checkMoveValid(move));

      // play the move
      board.playMove(move, player);

      // check for a win
      if (board.isOver()) {
        System.out.print("Player " + player + " wins.");
      }

      // swap players
      if (player == "X") player = "O";
      else player = "X";
    }
    scanner.close();

    // must be tie
    System.out.print("Tie game.");

  }

}

public class Main {

  static void main(String[] args) {
    Game game = new Game();
    game.startGame();
  }
}

class Board {

  String[][] grid;

  Board() {

    grid = new String[3][3];
  }

  boolean checkMoveValid(int move) {
    if (move < 1 || move > 9) return false;

    int row = move / 3;
    int col = move % 3;

    if (grid[row][col] != null) return true;
    return false;
  }

  void playMove(int move,String player) {

    int row = move / 3;
    int col = move % 3;

    grid[row][col] = player;
  }


  boolean isOver() {

    //rows
    for (int i = 0; i < 3; i ++) {
      // if (grid[i][j] != current) f
      if( grid[i][0] != null && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])){
        return true;
      }
    }

    //cols
    for (int i = 0; i < 3; i ++) {
      // if (grid[i][j] != current) f
      if( grid[0][i] != null && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])){
        return true;
      }
    }

    //diag
    if( grid[0][0] != null && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])){
      return true;
    }
      if( grid[0][2] != null && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])){
      return true;
    }
    return false;
  }
}
