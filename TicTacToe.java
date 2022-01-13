package classX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

public class TicTacToe {

    static HashMap<Integer, String> boardMoves = new HashMap<>();
    static int choice;
    static Random r = new Random();
    static final HashMap<Integer, int[]> MOVE_SET = new HashMap<>();
    static String[][] board = {{" ","|"," ","|"," "},
            {"-","+","-","+","-"},
            {" ","|"," ","|"," "},
            {"-","+","-","+","-"},
            {" ","|"," ","|"," "},};

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        MOVE_SET.put(1, new int[]{0, 0});
        MOVE_SET.put(2, new int[]{0, 2});
        MOVE_SET.put(3, new int[]{0, 4});
        MOVE_SET.put(4, new int[]{2, 0});
        MOVE_SET.put(5, new int[]{2, 2});
        MOVE_SET.put(6, new int[]{2, 4});
        MOVE_SET.put(7, new int[]{4, 0});
        MOVE_SET.put(8, new int[]{4, 2});
        MOVE_SET.put(9, new int[]{4, 4});
        showIntroduction();
        System.out.println("""
                            What mode do you want to play in:
                            1. Human vs Human
                            2. Human vs PC
                            3. PC vs PC
                            """);
        choice = Integer.parseInt(in.readLine());
        VALIDATE: while(true){
            switch (choice){
                case 1:
                    playInMode1();
                    break VALIDATE;
                case 2:
                    playInMode2();
                    break VALIDATE;
                case 3:
                    playInMode3();
                    break VALIDATE;
                default:
                    System.out.println("Invalid choice, try again!");
                    choice = Integer.parseInt(in.readLine());
            }
        }

        System.out.println("It was a tie!");

    }

    static void showIntroduction(){
        System.out.println("Welcome to Srish's Tic-Tac-Toe Game!");
        System.out.println("""
                In order to play let's first get into the rules!
                You will be taking turns to fill in each square of the board!
                The board looks like this
                 | |
                -+-+-
                 | |
                The 1st player will fill a box with "O", and the second player with "X"
                The boxes are numbered as follows:
                1|2|3
                -+-+-
                4|5|6
                -+-+-
                7|8|9
                The first person to have three of their figures connected wins!
                """);
    }

    static void playInMode1() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        while (boardMoves.size() <= 9){
            int move;
            System.out.println("Enter your move:");
            move = Integer.parseInt(in.readLine());
            while (true) {
                if(1 <= move && move <= 9 && !boardMoves.containsKey(move))
                    break;
                else if(boardMoves.containsKey(move)) {
                    System.out.println("This square is occupied! Try Again!");
                    move = Integer.parseInt(in.readLine());
                }
                else{
                    System.out.println("Out of bounds! Try again!");
                    move = Integer.parseInt(in.readLine());
                }
            }
            boardMoves.put(move, "O");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "O";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

            if(boardMoves.size() == 9)
                break;

            System.out.println("Enter your move:");
            move = Integer.parseInt(in.readLine());
            while (true) {
                if(1 <= move && move <= 9 && !boardMoves.containsKey(move))
                    break;
                else if(boardMoves.containsKey(move)) {
                    System.out.println("This square is occupied! Try Again!");
                    move = Integer.parseInt(in.readLine());
                }
                else{
                    System.out.println("Out of bounds! Try again!");
                    move = Integer.parseInt(in.readLine());
                }
            }
            boardMoves.put(move, "X");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "X";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

        }

    }

    static void playInMode2() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("""
                            Do you want to be go first or second?:
                            1. Become Player 1
                            2. Become Player 2
                            """);
        choice = Integer.parseInt(in.readLine());
        VALIDATE: while(true) {
            switch (choice) {
                case 1:
                    bePlayer1();
                    break VALIDATE;
                case 2:
                    bePlayer2();
                    break VALIDATE;
                default:
                    System.out.println("Invalid choice, try again!");
                    choice = Integer.parseInt(in.readLine());
            }
        }

    }

    static void playInMode3(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        while (boardMoves.size() <= 9){
            int move;
            do {
                move = r.nextInt(9) + 1;
            } while (boardMoves.containsKey(move));
            boardMoves.put(move,"O");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "O";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

            if(boardMoves.size() == 9)
                break;

            do {
                move = r.nextInt(9) + 1;
            } while (boardMoves.containsKey(move));
            boardMoves.put(move,"X");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "X";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

        }
    }

    static void bePlayer1() throws IOException {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        while (boardMoves.size() <= 9){
            int move;
            System.out.println("Enter your move:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            move = Integer.parseInt(in.readLine());
            while (true) {
                if(1 <= move && move <= 9 && !boardMoves.containsKey(move))
                    break;
                else if(boardMoves.containsKey(move)) {
                    System.out.println("This square is occupied! Try Again!");
                    move = Integer.parseInt(in.readLine());
                }
                else{
                    System.out.println("Out of bounds! Try again!");
                    move = Integer.parseInt(in.readLine());
                }
            }
            boardMoves.put(move, "O");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "O";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

            if(boardMoves.size() == 9)
                break;

            do {
                move = r.nextInt(9) + 1;
            } while (boardMoves.containsKey(move));
            boardMoves.put(move,"X");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "X";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

        }
    }

    static void bePlayer2() throws IOException {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        while (boardMoves.size() <= 9){
            int move;
            do {
                move = r.nextInt(9) + 1;
            } while (boardMoves.containsKey(move));
            boardMoves.put(move,"O");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "O";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();

            if(boardMoves.size() == 9)
                break;

            System.out.println("Enter your move:");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            move = Integer.parseInt(in.readLine());
            while (true) {
                if(1 <= move && move <= 9 && !boardMoves.containsKey(move))
                    break;
                else if(boardMoves.containsKey(move)) {
                    System.out.println("This square is occupied! Try Again!");
                    move = Integer.parseInt(in.readLine());
                }
                else{
                    System.out.println("Out of bounds! Try again!");
                    move = Integer.parseInt(in.readLine());
                }
            }
            boardMoves.put(move, "X");
            board[MOVE_SET.get(move)[0]][MOVE_SET.get(move)[1]] = "X";

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            checkVictory();
        }
    }

    public static void checkVictory(){

        ArrayList<Integer> positions = new ArrayList<>();

        //Player 1
        for(Entry<Integer, String> entry: boardMoves.entrySet()){
            if(entry.getValue().equals("O")){
                positions.add(entry.getKey());
            }
        }

        if(positions.contains(1) && positions.contains(2) && positions.contains(3)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(4) && positions.contains(5) && positions.contains(6)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(7) && positions.contains(8) && positions.contains(9)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(1) && positions.contains(4) && positions.contains(7)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(2) && positions.contains(5) && positions.contains(8)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(3) && positions.contains(6) && positions.contains(9)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(1) && positions.contains(5) && positions.contains(9)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }
        if(positions.contains(3) && positions.contains(5) && positions.contains(7)){
            System.out.println("Player 1 Wins!");
            System.exit(0);
        }

        positions.clear();

        //Player 2
        for(Entry<Integer, String> entry: boardMoves.entrySet()){
            if(entry.getValue().equals("X")){
                positions.add(entry.getKey());
            }
        }

        if(positions.contains(1) && positions.contains(2) && positions.contains(3)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(4) && positions.contains(5) && positions.contains(6)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(7) && positions.contains(8) && positions.contains(9)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(1) && positions.contains(4) && positions.contains(7)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(2) && positions.contains(5) && positions.contains(8)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(3) && positions.contains(6) && positions.contains(9)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(1) && positions.contains(5) && positions.contains(9)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }
        if(positions.contains(3) && positions.contains(5) && positions.contains(7)){
            System.out.println("Player 2 Wins!");
            System.exit(0);
        }

    }

}
