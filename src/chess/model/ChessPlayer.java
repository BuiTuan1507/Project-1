package chess.model;
import chess.piece.*;
import java.util.ArrayList;
import java.util.List;
public class ChessPlayer {
    protected Board board;
    protected Side side;



    public ChessPlayer(Board board, Side side) {

        this.board = board;
        this.side = side;
    }

    public ChessMove decideMove(Board board, Side side) {
        return null;
    }

    public ChessMove intel(Board board, Side side, ChessPlayer engine) {
        return null;
    }

    public void makeMove(ChessMove move)
    {
        if (move.getTargetPiece() != null)
        {
            Army opponentArmy = board.getArmy(side.opposite());
            opponentArmy.buryPiece(move.getTargetPiece());
        }
        Piece movingPiece = move.getInitialSquare().getPiece();
         movingPiece.setSquare(move.getTargetSquare());
        move.getTargetSquare().setPiece(movingPiece);
        move.getInitialSquare().setPiece(null);
    }

    public void undoMove(ChessMove move)
    {
        Square initialSquare = move.getInitialSquare();
        Square targetSquare = move.getTargetSquare();
        Piece targetPiece = move.getTargetPiece();
        Piece movingPiece = targetSquare.getPiece();

        movingPiece.setSquare(initialSquare);
        initialSquare.setPiece(movingPiece);
        targetSquare.setPiece(null);

        if (targetPiece != null)
        {
            targetPiece.setSquare(targetSquare);
            targetSquare.setPiece(targetPiece);
            Army oppponentArmy = board.getArmy(side.opposite());
            oppponentArmy.revivePiece(targetPiece);
        }
    }

    public List<ChessMove> computeAllLegalMoves(){
        List<Piece> alivePieces = board.getArmy(side).getAlivePieces();//dua cac gia tri quan co con song vao mang alivePiece
        List<ChessMove> legalMoves = new ArrayList<ChessMove>();// khai bao 1 danh sach cac nuoc di hop le
        for (Piece p : alivePieces){
            for (Square s : p.computeLegalMoves()){
                ChessMove move = new ChessMove(p.getSquare(),s,s.getPiece());
                legalMoves.add(move);

            }
        }
        return legalMoves;
    }
// ham tinh toan bo cac nuoc di hop le cua quan co

@Override
    public String toString()
{
    return "PLAYER";
}


}
