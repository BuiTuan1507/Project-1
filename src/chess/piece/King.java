package chess.piece;
import chess.model.Side;
import chess.model.Square;

import java.util.ArrayList;
import java.util.List;
public class King extends Piece{

    static int RatingBoard[][]={
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            { 20, 20,  0,  0,  0,  0, 20, 20},
            { 20, 30, 10,  0,  0, 10, 30, 20}};
    public King(Square square , Side side){
        super(square, side, 10000  ,1);
    }

    @Override
    public String toString (){
        return side == Side.WHITE? "WK" : "BK";
    }


    @Override
    public List<Square> computeLegalMoves() {
        List<Square> legalMoves = new ArrayList<Square>();
        for (int h = -1; h<=1;h++){
            for (int v=-1; v<=1;v++){
                Square targetSquare = square.getAdjacentSquare(h,v);
                checkSquare(legalMoves,targetSquare);

            }
        }
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
