package chess.piece;

import chess.model.Side;
import chess.model.Square;

import java.util.ArrayList;
import java.util.List;
public abstract class Piece {
    private final int  score;
    public int[][] RatingBoard;
    protected Square square;
    protected final Side side;
    protected  int check;

    public abstract List<Square> computeLegalMoves();
    protected Piece (Square square, Side side, int score, int check){
        this.square=square;
        this.side=side;
        this.score= score;
        this.square.setPiece(this);
        this.check = check;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public Square getSquare()
    {
        return square;
    }

    public void setSquare(Square square)
    {
        this.square = square;
    }
    public int getScore()
    {
        return score;
    }
    public Side getSide()
    {
        return side;
    }
    protected void checkSquare(List<Square> legalMoves, Square targetSquare)
    {
        if (targetSquare != null && (targetSquare.getPiece() == null || targetSquare.getPiece().side != side))
        {
            legalMoves.add(targetSquare);
        }
    }

    protected List<Square> computeLinearMoves(int horizontal, int vertical)
    {
        List<Square> legalMoves = new ArrayList<Square>();
        Square targetSquare = square.getAdjacentSquare(horizontal, vertical);

        while(targetSquare != null)
        {
            if (targetSquare.getPiece() == null)
            {
                legalMoves.add(targetSquare);
            }
            else if (targetSquare.getPiece().side == side)
            {
                break;
            }
            else
            {
                legalMoves.add(targetSquare);
                break;
            }
            targetSquare = targetSquare.getAdjacentSquare(horizontal, vertical);
        }
        return legalMoves;
    }

    public abstract int RatingBoard(Side side, Square square);



}

