package fun;

import Chess.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while (!chessMatch.getCheckmate()) {
            try {
                UI.clearScreen();
                UI.printmatch(chessMatch,captured);
                System.out.println();
                System.out.print("Source:");
                ChessPosition source = UI.readChessPosition(sc);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(),possibleMoves);
                System.out.println();
                System.out.print("Target:");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece captured_piece = chessMatch.performChessMove(source, target);
                if (captured_piece != null){
                    captured.add(captured_piece);
                }
            } catch (ChessException ade) {
                System.out.println(ade.getMessage());
                sc.nextLine();
            }catch (InputMismatchException ade) {
                System.out.println(ade.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printmatch(chessMatch,captured);
    }
}
