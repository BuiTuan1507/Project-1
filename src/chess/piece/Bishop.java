package chess.piece;
import chess.model.Side;
import chess.model.Square;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{
    static int RatingBoard[][]={
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}};
    public Bishop(Square square, Side side)
    {
        super(square, side, 320,1);
    }


    @Override
    public String toString()
    {
        return side == Side.WHITE ? "WB" : "BB";
    }

    @Override
    public List<Square> computeLegalMoves()
    {
        List<Square> legalMoves = new ArrayList<Square>();
        legalMoves.addAll(computeLinearMoves(1, 1));
        legalMoves.addAll(computeLinearMoves(1, -1));
        legalMoves.addAll(computeLinearMoves(-1, -1));
        legalMoves.addAll(computeLinearMoves(-1, 1));
        return legalMoves;
    }

    @Override
    public int RatingBoard(Side side, Square square){
        int score;
        if (side==Side.WHITE) {
            score = RatingBoard[7-square.getRow()][7-square.getCol()];

        }
        else {
            score = RatingBoard[square.getRow()][square.getCol()];
        }
        return score;
    }
}
