public class GameBoard {
    public static char[][] createBoard(){ //create a board and initialize it to ' ' spots by each unused spot.
        char[][] board = new char[3][3];
        for (int i = 0; i < 3;i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
        //printBoard(board);
        return board;
    }

//    public static void printBoard(char[][] board){ //Print please, SIRE
//        System.out.println(board[0][0] + "|"+board[0][1] + "|"+board[0][2]);
//        System.out.println(board[1][0] + "|"+board[1][1] + "|"+board[1][2]);
//        System.out.println(board[2][0] + "|"+board[2][1] + "|"+board[2][2]);
//    }

    public static void printBoard(char[][] board){
        System.out.println("     A   B   C");
        System.out.println("    ___ ___ ___");
        System.out.println("1  |_" + board[0][0] + "_|_" + board[0][1] + "_|_" + board[0][2] + "_|");
        System.out.println("2  |_" + board[1][0] + "_|_" + board[1][1] + "_|_" + board[1][2] + "_|");
        System.out.println("3  |_" + board[2][0] + "_|_" + board[2][1] + "_|_" + board[2][2] + "_|");
    }


}
