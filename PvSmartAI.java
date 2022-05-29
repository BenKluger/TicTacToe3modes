import java.util.Scanner;

// GeeksForGeeks Minimax Algorithm page was referenced for this code
// https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

public class PvSmartAI {

    static class Move { //creates a value for row and column object
        int row, col;
    }

    static char player = 'X', ai = 'O';

    public PvSmartAI(char[][] board){
        startTurns(board);
    }

    public static void startTurns(char[][] board){

        int marker = 0;

        makeTurn(marker, board);
    }

    public static void makeTurn(int marker, char[][] board){

        System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();


        while (input.length() > 2) { //edge case
            System.out.println("Your input is too long. Try again.");

            System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");
            input = in.nextLine();
        }
        playSpot(input, board, marker); //place user move

        System.out.println("Done making move.");
        game.printBoard(board);



        if(game.isWinner(board)){ // check if the user has won
            System.out.println("Player " + (marker+1) + " has won!");
            game.printBoard(board);
            System.exit(1);
        }


        if (!game.availSpots(board)){
            System.out.println("The game is a draw.");
            game.printBoard(board);
            System.exit(1);
        }


        System.out.println("The AI is now making a move.");
        //Finding the best move for the AI
        Move bestMoveAI = findBestMove(board);
        playSpotAI(board, bestMoveAI); //play the AI's move


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

        game.printBoard(board);

        makeTurn(marker, board);

    }

    static int minimax(char board[][], int depth, Boolean isMaximizerMove){ //this is the minimax algorithm itself. It finds the optimal piece placing position

        int score = game.evaluate(board); //check the score, see if it is a win

        //if Maximizer has won the game return the score
        if (score == 10)
            return score;
        if (score == -10) //Minimizer won
            return score;

        if (game.availSpots(board) == false)
            return 0; //tie

        int best;
        if (isMaximizerMove){ // this is for the imaginary moves the player will make
            best = -1000;

            //go through every cell
            for(int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (board[i][j] == ' '){
                        board[i][j] = player;
                        //call minimax recursively and choose the maximum value
                        best = Math.max(best, minimax(board, depth+1, !isMaximizerMove)) - depth;
                        //now undo the move
                        board[i][j] = ' ';
                    }
                }
            }
        }

        else { //if it is the minimizer's move, find the optimal moving spot for the minimizer
            best = 1000;

            //go through every cell
            for(int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (board[i][j] == ' '){
                        board[i][j] = ai;
                        //call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(board, depth+1, !isMaximizerMove)) + depth;
                        //now undo the move
                        board[i][j] = ' ';
                    }
                }
            }
        }
        return best;
    }


    //This method will return the best overall move choice for the AI
    static Move findBestMove(char[][] board){

        int bestVal = 1000; //start off at a high value. Remember, the AI is the minimizer!
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        //now we go through every cell and evaluate the minimax function for all empty cells and return the cell with the optimal value
        for(int i = 0; i < 3; i++){
            for (int j = 0; j<3; j++){
                if (board[i][j] == ' '){
                    //make the move
                    board[i][j] = ai;

                    //compute the minimax function for this move
                    int moveVal = minimax(board, 0, true);

                    //undo the move
                    board[i][j] = ' ';

                    //if the value of the current move is less than the best value, then update the best
                    if (moveVal < bestVal){
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("The best move for the AI is: \nRow: " + bestMove.row + "\nCol: " + bestMove.col);
        return bestMove;
    }

    public static void playSpot(String str, char[][] board, int marker) { //this parses the user input, and performs checks along the way
        try {

            char mark = 'X';


            int row = str.charAt(0) - 'A';
            int column = str.charAt(1) - '1';

//            System.out.println("Row: " + row + "\nColumn: " + column);
            if (board[row][column] == ' ') { //if it is an empty spot, it will place the mark there
                board[row][column] = mark;
            } else { //else, if there is a marker there, then ask again
                System.out.println("There is already a marker there. Try again.");

                System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                // in.close();

                playSpot(input, board, marker);
            }
        } catch (IndexOutOfBoundsException e) { //checks for out of bounds
            System.out.println("You cannot place a marker out of bounds. Try again.");

            System.out.println("It is your turn. Select a spot! (User input is A->C for rows, 1->3 for columns) Ex: \"A2\"");

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            playSpot(input, board, marker);
        }
    }

    public static void playSpotAI(char[][] board, Move bestMove){ //this plays the AI move. Very little code is needed
        board[bestMove.row][bestMove.col] = 'O';
    }
}
