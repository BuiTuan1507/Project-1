package chess.piece;
import chess.model.Side;
import chess.model.Square;

import java.util.ArrayList;
import java.util.List;
public class Rook extends Piece {
    static int RatingBoard[][]={
            { 0,  0,  0,  0,  0,  0,  0,  0},
            { 5, 10, 10, 10, 10, 10, 10,  5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            { 0,  0,  0,  5,  5,  0,  0,  0}};
    public Rook(Square square,Side side){
        super(square, side,500,1);
    }
    @Override
    public String toString (){
        return side ==  Side.WHITE ? "WR" : "BR";
    }

    @Override
    public List<Square> computeLegalMoves() {
        List<Square> legalMoves = new ArrayList<Square>();
        legalMoves.addAll(computeLinearMoves(1, 0));
        legalMoves.addAll(computeLinearMoves(-1, 0));
        legalMoves.addAll(computeLinearMoves(0, 1));
        legalMoves.addAll(computeLinearMoves(0, -1));
        return legalMoves;
    }
    @Override
    public int RatingBoard(Side side, Square square){
        int score;
        if (side == Side.WHITE) {
            score = RatingBoard[7-square.getRow()][7-square.getCol()];

        }
        else {
            score = RatingBoard[square.getRow()][square.getCol()];
        }
        return score;
    }
}
