import java.util.*;

public class TicTacToe{
  static ArrayList<Integer> placements = new ArrayList<Integer>();
  static ArrayList<Integer> cpuplacements = new ArrayList<Integer>();

  public static void main (String[] args){


    char[][] board = {
                      {' ', '|', ' ', '|', ' '},
                      {'-', '|', '-', '|', '-'},
                      {' ', '|', ' ', '|', ' '},
                      {'-', '|', '-', '|', '-'},
                      {' ', '|', ' ', '|', ' '}
                     };
    while (true){
      Scanner scan = new Scanner(System.in);
      System.out.println("Choose a Tile (1-9): ");
      int placement = scan.nextInt();
      while(placements.contains(placement) ||
      cpuplacements.contains(placement)){
        System.out.println("Position taken. Enter another position: ");
        placement = scan.nextInt();
      }

      System.out.println("Filling in position " + placement);
      place(board, placement, "player");

      Random random = new Random();
      int cpuplacement = random.nextInt(9) + 1;
      while(placements.contains(cpuplacement) ||
      cpuplacements.contains(cpuplacement)){
        cpuplacement = random.nextInt(9) + 1;
      }
      place(board, cpuplacement, "cpu");

      for(char[] row:board){
        for(char col:row){
          System.out.print(col);
        }
        System.out.println();
      }
      if(gamecheck().length() > 0){
        System.out.println(gamecheck());
        break;
      }

    }


  }

  public static void place (char[][] board, int placement, String user){

    char symbol = ' ';

    if(user.equals("player")){
      symbol = 'X';
      placements.add(placement);
    }
    else if(user.equals("cpu")){
      symbol = 'O';
      cpuplacements.add(placement);
    }
    switch(placement){
      case 1: board[0][0] = symbol;
              break;
      case 2: board[0][2] = symbol;
              break;
      case 3: board[0][4] = symbol;
              break;
      case 4: board[2][0] = symbol;
              break;
      case 5: board[2][2] = symbol;
              break;
      case 6: board[2][4] = symbol;
              break;
      case 7: board[4][0] = symbol;
              break;
      case 8: board[4][2] = symbol;
              break;
      case 9: board[4][4] = symbol;
              break;
    }
  }

  public static String gamecheck(){
    List toprow = Arrays.asList(1, 2, 3);
    List midrow = Arrays.asList(4, 5, 6);
    List botrow = Arrays.asList(7, 8, 9);

    List leftcol = Arrays.asList(1, 4, 7);
    List midcol = Arrays.asList(2, 5, 8);
    List rightcol = Arrays.asList(3, 6, 9);

    List leftdiag = Arrays.asList(1, 5, 9);
    List rightdiag = Arrays.asList(7, 5, 3);

    List<List> winConditions = new ArrayList<List>();
    winConditions.add(toprow);
    winConditions.add(midrow);
    winConditions.add(botrow);
    winConditions.add(leftcol);
    winConditions.add(midcol);
    winConditions.add(rightcol);
    winConditions.add(leftdiag);
    winConditions.add(rightdiag);

    for(List condition:winConditions){
      if(placements.containsAll(condition)){
        return "You won!";
      }
      else if(cpuplacements.containsAll(condition)){
        return "You lost.";
      }
      else if(placements.size() + cpuplacements.size() == 9){
        return "No winners.";
      }
    }
    return "";
  }
}
