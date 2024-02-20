package fun;

import Chess.ChessMatch;
import Chess.UI;

public class program {
    public static void main(String[] args) {


        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }
}
