package chess.player;

import chess.model.*;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RamdomPlayer extends ChessPlayer {
    private Random random;
    public RamdomPlayer(Board board, Side side) {
        super(board, side);
        random = new Random();
    }
    @Override
    public ChessMove decideMove(Board board, Side side) {
        List<Piece> pieces = pieces = board.getArmy(side).getAlivePieces();
        List<Square> legalMoves;
        Piece randomPiece;
        do {
            int randomPieceIndex = random.nextInt(pieces.size());
            randomPiece = pieces.get(randomPieceIndex);
            legalMoves = new ArrayList<>(randomPiece.computeLegalMoves());
        }
        while (legalMoves.size() == 0);
        int randomMoveIndex = random.nextInt(legalMoves.size());
        Square targetSquare = legalMoves.get(randomMoveIndex);
        return new ChessMove(randomPiece.getSquare(), targetSquare, targetSquare.getPiece());
    }
    @Override
    public ChessMove intel(Board board, Side side, ChessPlayer engine) {
        ChessMove bestMove = null;
        int maxScore = 0;
        int rating = 0;
        int rating1 = board.computeRating(side);
        int rating2 = 0;
        List<ChessMove> legalMoves = engine.computeAllLegalMoves();
        for (ChessMove p : legalMoves) {
            engine.makeMove(p);
            rating2 = board.Rating(board, side);
            rating = rating2 - rating1;
            if (maxScore < rating) {
                maxScore = rating;
                bestMove = p;
            }
            engine.undoMove(p);


        }
        return bestMove;
    }
    @Override
    public String toString() {
        return "RANDOM PLAYER";
    }
}


