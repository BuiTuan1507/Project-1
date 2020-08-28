package chess.player;
import chess.model.*;

import java.util.List;
public class AlphaBetaPlayer extends ChessPlayer
    {
        private static final int INITIAL_DEPTH = 4;
        private static final int TIMEOUT_MILISECONDS = 10000;
        private int currentDepth;
        private ChessMove bestMove;
        private ChessMove globalBestMove;
        private long start;
        private boolean timeout;
        public AlphaBetaPlayer(Board board, Side side)
        {
            super(board, side);
        }
        @Override
        public ChessMove decideMove(Board board, Side side)
        {
            timeout = false;
            start = System.currentTimeMillis();
            for (int d = 0;  ; d++)
            {
                if (d > 0)
                {
                    globalBestMove = bestMove;
                    System.out.println("Tim kiem voi do sau " + d + ". Nuoc di tot nhat: " + globalBestMove);
                }
                currentDepth = INITIAL_DEPTH + d;
                maximum(currentDepth, Integer.MIN_VALUE , Integer.MAX_VALUE);

                if (timeout)
                {
                    System.out.println();
                    return globalBestMove;
                }
            }
        }
        private int maximum(int depth, int alpha, int beta)
        {
            if (System.currentTimeMillis() - start > TIMEOUT_MILISECONDS)
            {
                timeout = true;
                return alpha;
            }
            if (depth == 0)
            {
                return board.computeRating(Side.BLACK);
            }
            List<ChessMove> legalMoves = computeAllLegalMoves();
            for (ChessMove move : legalMoves)
            {
                makeMove(move);
                side = side.opposite();
                int rating =  minimum(depth - 1, alpha, beta);
                side = side.opposite();
                undoMove(move);

                if (rating > alpha)
                {
                    alpha = rating;

                    if (depth == currentDepth)
                    {
                        bestMove = move;
                    }
                }

                if (alpha >= beta)
                {
                    return alpha;
                }
            }
            return alpha;
        }
        private  int minimum(int depth, int alpha, int beta)// voi nguoi
        {
            if (depth == 0)
            {
                return board.computeRating(Side.BLACK);
            }
            List<ChessMove> legalMoves = computeAllLegalMoves();
            for (ChessMove move : legalMoves)
            {
                makeMove(move);
                side = side.opposite();
                int rating = maximum(depth - 1, alpha, beta);
                side = side.opposite();
                undoMove(move);
                if (rating <= beta)
                {
                    beta = rating;
                }
                if (alpha >= beta)
                {
                    return beta;
                }
            }
            return beta;
        }
    }

