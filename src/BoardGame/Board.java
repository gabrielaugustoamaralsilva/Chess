package BoardGame;

public class Board {
  private int rows;
  private int columns;
  private Piece[][] pieces;
    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1){
            throw new BoardException("È necessário que exista ao menos uma linha e uma coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public Piece piece(int row, int colum){
       if (!positionExists(row,colum)){
           throw new BoardException("Posicao nao existe");
       }

        return pieces[row][colum];
    }
    public Piece piece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Posicao nao existe");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    public void placePiece(Piece piece, Position position){
       if (thereisapiece(position)){
           throw new BoardException("ja tem uma peça na  posição " + position);
       }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    private boolean positionExists(Position position){
      return positionExists(position.getRow(),position.getColumn());
    }
    private boolean positionExists(int row, int colum){
        return row >= 0 && row < rows && colum >= 0 && colum < columns;
    }
    public boolean thereisapiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Posicao nao existe");
        }
        return piece(position) != null;
    }
}
