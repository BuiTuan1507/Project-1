package  chess.model;

import chess.piece.Piece;

public class Board {
    public static final int LENGTH = 8;
    private Square[][] squares;
    private Army whiteArmy;
    private Army blackArmy;

    public Board(Army whiteArmy, Army blackArmy) {
        this.whiteArmy = whiteArmy;
        this.blackArmy = blackArmy;
        squares = new Square[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                squares[r][c] = new Square(this, r, c);
            }
        }
    }

    public Army getArmy(Side side) {
        return side == Side.WHITE ? whiteArmy : blackArmy;
    }

    public Square getSquare(int row, int col) {
        return (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) ? null : squares[row][col];
    }

    public int computeRating(Side side) {
        int whiteScore = 0;
        int blackScore = 0;
        for (Piece p : whiteArmy.getAlivePieces()) {
            whiteScore += p.getScore();
            whiteScore += p.RatingBoard(Side.WHITE, p.getSquare());

        }
        //System.out.print("whiteScore =" +whiteScore);

        for (Piece p : blackArmy.getAlivePieces()) {
            blackScore += p.getScore();
            blackScore += p.RatingBoard(Side.BLACK, p.getSquare());

        }
        //System.out.print("\nblackScore =" +blackScore);
        return side == Side.BLACK ? blackScore - whiteScore : whiteScore - blackScore;
    }

    public int Rating(Board board, Side side) {
        int rating = 0;
        for (Piece p : board.getArmy(side).getAlivePieces()) {
            rating += p.getScore();
            rating += p.RatingBoard(side, p.getSquare());
        }
        return rating;
    }

    public void display(Board board) {
        for (int r = Board.LENGTH - 1; r >= 0; r--) {
            for (int c = 0; c < Board.LENGTH; c++) {
                if ((board.getSquare(r, c).getPiece() != null) && (board.getSquare(r, c).getPiece().getCheck() == 1)) {
                    System.out.print(board.getSquare(r, c).getPiece());
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }


}
