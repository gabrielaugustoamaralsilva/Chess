package fun;

import Chess.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source:");
                ChessPosition source = UI.readChessPosition(sc);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(),possibleMoves);
                System.out.println();
                System.out.print("Target:");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece captured = chessMatch.performChessMove(source, target);
            } catch (ChessException ade) {
                System.out.println(ade.getMessage());
                sc.nextLine();
            }catch (InputMismatchException ade) {
                System.out.println(ade.getMessage());
                sc.nextLine();
            }
        }
    }
}
