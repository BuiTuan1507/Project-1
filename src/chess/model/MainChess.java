package chess.model;

import chess.UI.Table;
import chess.piece.Piece;

import java.util.Scanner;
public class MainChess {
    public static void main(String[] args) {
        ChessRoom chessRoom = new ChessRoom();
        Board board = chessRoom.getBoard();
        ChessPlayer human = chessRoom.getPlayer(Side.WHITE);
        ChessPlayer engine = chessRoom.getPlayer(Side.BLACK);
        board.display(board);
        while ((-8000 < board.computeRating(Side.BLACK) && (board.computeRating(Side.BLACK) < 8000))) {
            Table table = new Table(chessRoom, human);
            Scanner scanner = new Scanner(System.in);
            int r1,c1;
            int r2,c2;
            r1= scanner.nextInt();
            c1=scanner.nextInt();
            r2= scanner.nextInt();
            c2=scanner.nextInt();
            ChessMove move;
            move = new ChessMove(board.getSquare(r1,c1), board.getSquare(r2,c2),board.getSquare(r2,c2).getPiece());
            human.makeMove(move);
            //ChessMove move2 =  human.intel(board, Side.WHITE,human);
           // human.makeMove(move2);
            ChessMove move1 = engine.decideMove(board, Side.BLACK);
            engine.makeMove(move1);
            board.display(board);
            if (board.computeRating(Side.BLACK) < -8000) {
                System.out.print("\n TRANG THANG");
            }
            if (board.computeRating(Side.WHITE) < -8000) {
                System.out.print("\n MAY THANG");
            }
        }
    }
}
