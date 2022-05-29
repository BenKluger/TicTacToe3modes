import java.util.Scanner;

public class game extends GameBoard{



    public static void startGame(){ //This starts the game.
        char[][] board = createBoard();
        printBoard(board);
        giveChoice(board);
    }

    public static void giveChoice(char[][] board){ //Select the game! After the user selects, it creates a new object of the mode they chose, and the object's default constructor runs their version of the game.

        System.out.println("What game would you like to play?");
        System.out.println("0: Player vs Player");
        System.out.println("1: Player vs Dumb AI");
        System.out.println("2: Player vs Smart AI");
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();

        if (input == 0){
            System.out.println("You have selected: Player vs Player.");
            PvP pvpGame = new PvP(board);
        }
        else if (input == 1){
            System.out.println("You have selected: Player vs Dumb AI.");
            PvDumbAI pvEzAI = new PvDumbAI(board);
        }
        else if (input == 2) {
            System.out.println("You have selected: Player vs Smart AI.");
            PvSmartAI pvSAI = new PvSmartAI(board);
        }
        else{
            System.out.println("The Game Mode you have selected does not exist. Restart program. " + input);
        }


    }   

    public static boolean availSpots(char[][] board){ //checks if there is a spot left to play
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }    

    public static boolean isWinner(char[][] board){// Is there a winner? Dumb checker goes top right piece (2 checks), then
        // bottom right piece (2 checks), then the middle (4 checks)
            char markerTopLeft = ' ';
            char markerMid = ' ';
            char markerBotRight = ' ';

            if (board[0][0] != ' ')
                markerTopLeft = board[0][0];
            if (board[1][1] != ' ')
                markerMid = board[1][1];
            if (board[2][2] != ' ')
                markerBotRight = board[2][2];

            if (markerTopLeft != ' ')
                if ((board[0][1] == markerTopLeft && board[0][2] == markerTopLeft)
                        || (board[1][0] == markerTopLeft && board[2][0] == markerTopLeft)) // Top left marker. Looks at first column and first row
                    return true;

            if (markerBotRight != ' ')
                if ((board[2][0] == markerBotRight && board[2][1] == markerBotRight)
                        || (board[0][2] == markerBotRight && board[1][2] == markerBotRight)) // Bottom right marker. Third column and row
                    return true;

            // check + and X --> four checks
            if(markerMid != ' ')
                if ((board[0][1] == markerMid && board[2][1] == markerMid) // middle column
                        || (board[1][0] == markerMid && board[1][2] == markerMid) // middle row
                        || (board[0][0] == markerMid && board[2][2] == markerMid) //top left diagonal
                        || (board[0][2] == markerMid && board[2][0] == markerMid)) //top right diagonal
                    return true;

            return false;
            // System.out.println(marker);
        }

    public static int evaluate(char[][] board){ //AI board checker. This is no longer a boolean winner method. This adds points to each score, to find the best move.
        // Is there a winner? Dumb checker goes top right piece (2 checks), then
        // bottom right piece (2 checks), then the middle (4 checks)
        char markerTopLeft = ' ';
        char markerMid = ' ';
        char markerBotRight = ' ';

        if (board[0][0] != ' ')
            markerTopLeft = board[0][0];
        if (board[1][1] != ' ')
            markerMid = board[1][1];
        if (board[2][2] != ' ')
            markerBotRight = board[2][2];

        if (markerTopLeft != ' ')
            if ((board[0][1] == markerTopLeft && board[0][2] == markerTopLeft)
                    || (board[1][0] == markerTopLeft && board[2][0] == markerTopLeft)) { // Top left marker. Looks at first column and first row
                if (markerTopLeft == 'X')
                    return +10;
                else if (markerTopLeft == 'O')
                    return -10;
            }

        if (markerBotRight != ' ')
            if ((board[2][0] == markerBotRight && board[2][1] == markerBotRight)
                    || (board[0][2] == markerBotRight && board[1][2] == markerBotRight)){ // Bottom right marker. Third column and row
                if (markerBotRight == 'X')
                    return +10;
                else if (markerBotRight == 'O')
                    return -10;
            }

        // check + and X --> four checks
        if(markerMid != ' ')
            if ((board[0][1] == markerMid && board[2][1] == markerMid) // middle column
                    || (board[1][0] == markerMid && board[1][2] == markerMid) // middle row
                    || (board[0][0] == markerMid && board[2][2] == markerMid) //top left diagonal
                    || (board[0][2] == markerMid && board[2][0] == markerMid)){ //top right diagonal
                if (markerMid == 'X')
                    return +10;
                else if (markerMid == 'O')
                    return -10;
                }

        return 0; //if none of them have won

    }


    public static void main(String[] args) {
        startGame();

//        System.out.println("Say something");
//        Scanner in = new Scanner(System.in);
//        String input2 = in.nextLine();
//
//        if (input2.equals("b3"));
//            System.out.println("YAY");
//        else
//            System.out.println("NO");

    }
}

