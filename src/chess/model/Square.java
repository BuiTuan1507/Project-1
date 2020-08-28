package chess.model;

import chess.piece.Piece;

public class Square
{
    private final Board board;
    private final int row;
    private final int col;
    private boolean legal;
    private Piece piece;

    public Square(Board board, int row, int col)
    {
        this.board = board;
        this.row = row;
        this.col = col;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }

    public Piece getPiece()
    {
        return piece;
    }

    public int getCol() {
        return col;
    }
    public int getRow()
    {
        return row;
    }


    public Square getAdjacentSquare(int horizontal, int vertical)
    {
        return board.getSquare(vertical + row, horizontal + col);
    }



    public void setLegal(boolean legal)
    {
        this.legal = legal;
    }

    public boolean isLegal()
    {
        return legal;
    }

    @Override
    public String toString()
    {
        return String.format("%s%s", (char) ('A' + col), (1 + row));
    }
}