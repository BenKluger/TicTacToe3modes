import java.util.Scanner;

public class PvP {

    public PvP(char[][] board){
        startTurns(board);
    }//default constructor starts the rest

    public static void startTurns(char[][] board){//starts the moves

        int marker = 0;

        makeTurn(marker, board);
    }


    public static void makeTurn(int marker, char[][] board){// This method accepts user input for a move, and parses it into actual moves

        System.out.println("It is " + (marker+1) + "'s turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        

        while (input.length() > 2) { //edge case check
            System.out.println("Your input is too long. Try again.");

            System.out.println("It is Player " + (marker+1) + "'s turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");
            input = in.nextLine();
        }
            playSpot(input, board, marker);



        System.out.println("Done making move.");

        if(game.isWinner(board)){
            System.out.println("Player " + (marker+1) + " has won!");
            game.printBoard(board);
            System.exit(1);
        }

        
        if (!game.availSpots(board)){
            System.out.println("The game is a draw.");
            game.printBoard(board);
            System.exit(1);
        }

        marker = (marker+1) % 2; //turn switcher, using binary swap

        game.printBoard(board);

        makeTurn(marker, board);

    }

    public static void playSpot(String str, char[][] board, int marker){
        try {

            char mark = ' '; //finds out what piece is being placed
            if (marker == 0)
                mark = 'X';
            else if (marker == 1)
                mark = 'O';


            int row = str.charAt(0) - 'A';
            int column = str.charAt(1) - '1';

//            System.out.println("Row: " + row + "\nColumn: " + column);
            if (board[row][column] == ' ') { //if it is an empty spot, it will place the mark there
                board[row][column] = mark;
            }
            else{ //else, if there is a marker there, then ask again
                System.out.println("There is already a marker there. Try again.");

                System.out.println("It is Player " + (marker+1) + "'s turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

                Scanner in = new Scanner(System.in);
                String input = in.nextLine();

                playSpot(input, board, marker);
            }
        } catch (IndexOutOfBoundsException e){ //checks for out of bounds
            System.out.println("You cannot place a marker out of bounds. Try again.");

            System.out.println("It is Player " + (marker+1) + "'s turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            playSpot(input, board, marker);
        }
    }
}
