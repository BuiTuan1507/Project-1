package chess.piece;
import chess.model.Side;
import chess.model.*;

import java.util.ArrayList;
import java.util.List;
public class BlackPawn extends Piece {
    static int RatingBoard[][]={
            { 0,  0,  0,  0,  0,  0,  0,  0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            { 5,  5, 10, 25, 25, 10,  5,  5},
            { 0,  0,  0, 20, 20,  0,  0,  0},
            { 5, -5,-10,  0,  0,-10, -5,  5},
            { 5, 10, 10,-20,-20, 10, 10,  5},
            { 0,  0,  0,  0,  0,  0,  0,  0}};
    public BlackPawn(Square square, Side side){
        super(square, side, 100,1);
    }
    public String toString(){
        return "BP";
    }


        @Override
        public List<Square> computeLegalMoves()
        {
            List<Square> legalMoves = new ArrayList<Square>();
            Square targetSquare = square.getAdjacentSquare(0, -1);

            if (targetSquare != null && targetSquare.getPiece() == null)
            {
                legalMoves.add(targetSquare);
                targetSquare = square.getAdjacentSquare(0, -2);

                if (square.getRow() == 6 && targetSquare.getPiece() == null)
                {
                    legalMoves.add(targetSquare);
                }
            }

            targetSquare = square.getAdjacentSquare(1, -1);

            if (targetSquare != null && targetSquare.getPiece() != null && targetSquare.getPiece().side != side)
            {
                legalMoves.add(targetSquare);
            }

            targetSquare = square.getAdjacentSquare(-1, -1);

            if (targetSquare != null && targetSquare.getPiece() != null && targetSquare.getPiece().side != side)
            {
                legalMoves.add(targetSquare);
            }
            return legalMoves;
        }

    @Override
    public int RatingBoard(Side side, Square square){
        int score;
        score = RatingBoard[square.getRow()][square.getCol()];
        return score;
    }

}


