package chess.piece;
import chess.model.Side;
import chess.model.*;

import java.util.ArrayList;
import java.util.List;
public class Knight extends Piece {
    static int RatingBoard[][]={
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}};
    public Knight(Square square, Side side){
        super(square, side, 300,1);

    }
    @Override
    public String toString (){
        return side == Side.WHITE ? "WN" : "BN";
    }

    @Override
    public List<Square> computeLegalMoves() {
        List<Square> legalMoves = new ArrayList<Square>();

        Square targetSquare = square.getAdjacentSquare(1,2);
        checkSquare(legalMoves, targetSquare);
        targetSquare = square.getAdjacentSquare(-1,2);
        checkSquare(legalMoves, targetSquare);
        targetSquare = square.getAdjacentSquare(1,-2);
        checkSquare(legalMoves,targetSquare);
        targetSquare = square.getAdjacentSquare(-1,-2);
        checkSquare(legalMoves,targetSquare);
        targetSquare = square.getAdjacentSquare(2,1);
        checkSquare(legalMoves,targetSquare);
        targetSquare = square.getAdjacentSquare(-2,-1);
       checkSquare(legalMoves,targetSquare);
        targetSquare = square.getAdjacentSquare(2,-1);
        checkSquare(legalMoves,targetSquare);
        targetSquare = square.getAdjacentSquare(-2,1);
        checkSquare(legalMoves,targetSquare);
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
