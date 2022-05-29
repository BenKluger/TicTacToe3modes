import java.util.Scanner;

public class PvDumbAI {

    public PvDumbAI(char[][] board){
        startTurns(board);
    } //default constructor starts the rest

    public static void startTurns(char[][] board){ //starts the moves

        int marker = 0;

        makeTurn(marker, board);
    }

    public static void makeTurn(int marker, char[][] board){ // This method accepts user input for a move, and parses it into actual moves

        System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        

        while (input.length() > 2) {
            System.out.println("Your input is too long. Try again.");

            System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");
            input = in.nextLine();
        }
            playSpot(input, board, marker);



        System.out.println("Done making move.");
        game.printBoard(board);

        

        if(game.isWinner(board)){ //check if there is a winner after a move has been made
            System.out.println("Player " + (marker+1) + " has won!");
            game.printBoard(board);
            System.exit(1);
        }        

        // if (moveCounter == 9){
        if (!game.availSpots(board)){ //check if it is a tie
            System.out.println("The game is a draw.");
            game.printBoard(board);
            System.exit(1);
        }
        
        
        System.out.println("The AI is now making a move.");
        makeAImove(board);

        //now repeat the checks
        if(game.isWinner(board)){
            System.out.println("The AI has won!");
            game.printBoard(board);
            System.exit(1);
        }        


        if (!game.availSpots(board)){
            System.out.println("The game is a draw.");
            game.printBoard(board);
            System.exit(1);
        }

        // marker = (marker+1) % 2; //turn switcher is not needed since this is against an AI

        game.printBoard(board);

        makeTurn(marker, board); //recall the method

    }

    public static void makeAImove(char[][] board){ // the AI checks for a blank spot, then places a "player" piece there.
        // If it sees that that concludes in a win, it will place its own marker there.
        // Otherwise, it will take away that player piece, and move on to the next section of code.



        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] != ' ') //if there is already a mark there, move on
                    continue;
                else if (board[i][j] == ' '){
                    board[i][j] = 'X';
                    if (game.isWinner(board)){
                        board[i][j] = 'O';
                        System.out.println("AI blocked a move on [" + i + "][" + j + "]");
                        return;
                    }
                    else
                        board[i][j] = ' '; //reset it back to a space and move on
                }
            }
        }

        //else, if there is no spot that blocks a win for Player 1, place in a random spot
        boolean isValidSpot = false;

        while(!isValidSpot) { //while we have not placed a random piece down
            int min = 0, max = 2;
            int randRow = (int) Math.floor(Math.random()*(max-min+1)+min);
            int randCol = (int) Math.floor(Math.random()*(max-min+1)+min);
            if (board[randRow][randCol] == ' '){
                board[randRow][randCol] = 'O';
                isValidSpot = true;
            }
        }
    }

    public static void playSpot(String str, char[][] board, int marker){ //this parses the user input, and performs checks along the way
        try {

            char mark = 'X';


            int row = str.charAt(0) - 'A';
            int column = str.charAt(1) - '1';

//            System.out.println("Row: " + row + "\nColumn: " + column);
            if (board[row][column] == ' ') { //if it is an empty spot, it will place the mark there
                board[row][column] = mark;
            }
            else{ //else, if there is a marker there, then ask again
                System.out.println("There is already a marker there. Try again.");

                System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                // in.close();

                playSpot(input, board, marker);
            }
        } catch (IndexOutOfBoundsException e){ //checks for out of bounds
            System.out.println("You cannot place a marker out of bounds. Try again.");

            System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            playSpot(input, board, marker);
        }
    }
}