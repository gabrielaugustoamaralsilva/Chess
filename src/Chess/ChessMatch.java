package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color current_player;
    private boolean check;
    private boolean checkmate;
    private List<Piece> piecesontheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    public int getTurn(){
        return turn;
    }
    public Color getCurrent_player(){
        return current_player;
    }

    public boolean getCheckmate() {
        return checkmate;
    }

    public ChessMatch(){
        board = new Board(8,8);
        current_player = Color.WHITE;
        turn = 1;
        initialsetup();
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat= new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    public boolean getCheck(){
        return check;
    }
  private void nextTurn(){
        turn++;
        current_player = (current_player == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }
   private void placeNewPiece(char colum, int row, ChessPiece piece){
     board.placePiece(piece,new ChessPosition(colum,row).toPosition());
     piecesontheBoard.add(piece);
   }
    private void initialsetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
      placeNewPiece('e', 2, new Rook(board, Color.WHITE));
       placeNewPiece('e', 1, new Rook(board, Color.WHITE));
       placeNewPiece('d', 1, new King(board, Color.WHITE));

      placeNewPiece('c', 7, new Rook(board, Color.BLACK));
       placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
       placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
   public boolean[][] possibleMoves(ChessPosition sourceposition){
        Position position = sourceposition.toPosition();
       validateSourcePosition(position);
       return board.piece(position).possibleMoves();
   }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);
        if (testcheck(current_player)){
            undoMove(source,target,capturedPiece);
            throw new ChessException("voce nao pode se colocar em cheque");
        }
        check = (testcheck(Oponnent(current_player))) ?true:false;
        if (testcheckmate(Oponnent(current_player))){
            checkmate = true;
        }
        else {
            nextTurn();
        }
        return (ChessPiece) capturedPiece;
    }
   private void validateTargetPosition(Position source, Position target){
        if (!board.piece(source).possibleMove(target)){
            throw new ChessException("a peça escolhida nao pode se mover para a posicao de destino");
        }
   }
    private void validateSourcePosition(Position position){
        if (!board.thereisapiece(position)){
            throw new ChessException("nao exsite peça na posição inical");
        }
        if (current_player != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("a peça escolhida nao é sua");
        }
        if (!board.piece(position).isthereAnyPossibleMove()){
            throw new ChessException("Não existe movimentos para a peça escolhida");
        }
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece captured = board.removePiece(target);
        board.placePiece(p,target);
        if (captured != null){
            piecesontheBoard.remove(captured);
            capturedPieces.add( captured);
        }
        return captured;
    }
    private void undoMove(Position source, Position target,Piece capturedPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p,source);
        if (capturedPiece != null){
             board.placePiece(capturedPiece,target);
             capturedPieces.remove(capturedPiece);
             piecesontheBoard.add(capturedPiece);
        }
    }
    private Color Oponnent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    private ChessPiece king(Color color){
        List<Piece> list = piecesontheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            if (p instanceof King){
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("nao tem rei");
    }
    private boolean testcheck(Color color){
        Position KingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPiece = piecesontheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == Oponnent(color)).collect(Collectors.toList());
        for (Piece p : opponentPiece){
            boolean[][] mat = p.possibleMoves();
            if (mat[KingPosition.getRow()][KingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }
    public boolean testcheckmate(Color color){
        if (! testcheck(color)){
            return false;
        }
        List<Piece> list = piecesontheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getColumns() ; j++) {
                    if (mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Piece capturedPiece = makeMove(source,target);
                        boolean testcheck = testcheck(color);
                        undoMove(source,target,capturedPiece);
                        if (!testcheck){
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
}
//   placeNewPiece('c', 1, new Rook(board, Color.WHITE));
//        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
//        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
//        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
//        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
//        placeNewPiece('d', 1, new King(board, Color.WHITE));
//
//        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
//        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
//        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
//        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
//        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
//        placeNewPiece('d', 8, new King(board, Color.BLACK));
