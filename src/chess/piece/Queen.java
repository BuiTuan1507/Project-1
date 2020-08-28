package chess.piece;
import chess.model.Side;
import chess.model.Square;

import java.util.ArrayList;
import java.util.List;
public class Queen extends Piece {
    static int RatingBoard[][]={
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            { -5,  0,  5,  5,  5,  5,  0, -5},
            {  0,  0,  5,  5,  5,  5,  0, -5},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}};
    public Queen (Square square , Side side){
        super(square, side, 900,1);
    }
    @Override
    public String  toString (){
        return side == Side.WHITE ? "WQ":"BQ";

    }
    @Override
    public List<Square> computeLegalMoves() {
        List<Square> legalMoves = new ArrayList<Square>();
        legalMoves.addAll(computeLinearMoves(1, 0));
        legalMoves.addAll(computeLinearMoves(-1, 0));
        legalMoves.addAll(computeLinearMoves(0, 1));
        legalMoves.addAll(computeLinearMoves(0, -1));
        legalMoves.addAll(computeLinearMoves(1, 1));
        legalMoves.addAll(computeLinearMoves(1, -1));
        legalMoves.addAll(computeLinearMoves(-1, -1));
        legalMoves.addAll(computeLinearMoves(-1, 1));
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
