package Chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
            boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);
        //cima
        p.setValue(position.getRow() - 1, position.getColumn());
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //esquerda
        p.setValue(position.getRow(), position.getColumn() - 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //direita
        p.setValue(position.getRow(), position.getColumn() + 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //baixo
        p.setValue(position.getRow() + 1, position.getColumn());
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //diagonal esquerda cima
        p.setValue(position.getRow() - 1, position.getColumn() - 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //diagonal direita cima
        p.setValue(position.getRow() - 1, position.getColumn() + 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //diagonal esquerda baixo
        p.setValue(position.getRow() + 1, position.getColumn() - 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //diagonal direita baixo
        p.setValue(position.getRow() + 1, position.getColumn() + 1);
        if ((getBoard().positionExists(p) && !getBoard().thereisapiece(p))){
            mat[p.getRow()][p.getColumn()] = true;
        } if (getBoard().positionExists(p) && isThereOponnentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
