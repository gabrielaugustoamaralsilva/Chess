package fun;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.UI;

import java.util.Scanner;

public class program {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
       while (true) {
           UI.printBoard(chessMatch.getPieces());
           System.out.println();
           System.out.print("Source:");
           ChessPosition source = UI.readChessPosition(sc);
           System.out.println();
           System.out.print("Target:");
           ChessPosition target = UI.readChessPosition(sc);
           ChessPiece captured = chessMatch.performChessMove(source,target);
       }
    }
}
