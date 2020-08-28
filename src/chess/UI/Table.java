package chess.UI;

import chess.model.*;
import chess.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {
    private final JFrame GameChess;
    private final BoardPanel boardPanel;
    private ChessRoom chessRoom;
    private ChessPlayer human;
    public  ChessMove move123;
    protected Square square;
    protected Board chessBoard;
    private Square startSquare;
    private Square endSquare;
    private Piece humanPiece;
    private static String defaultPieceImagePath = "images/";
    private final static Dimension TILE_PANEL_DIMESION = new Dimension(10,10);
    private final  static  Dimension BOARD_PANEL_DIMENSION  = new Dimension(400,350);
    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    public Table(ChessRoom chessRoom, ChessPlayer human){
        this.GameChess = new JFrame("Chess");
        this.GameChess.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar =  createTableMenuBar();
        this.GameChess.setJMenuBar(tableMenuBar);
        this.chessRoom = chessRoom;
        this.human = human;
        this.chessBoard = chessRoom.getBoard();
        this.GameChess.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.GameChess.add(this.boardPanel,BorderLayout.CENTER);
        this.GameChess.setVisible(true);

    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("Menu");
        final JMenuItem openPGN = new JMenuItem("Hint");
        final JMenuItem pp = new JMenuItem("Undo Move");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pp");
            }
        });
        fileMenu.add(openPGN);
        pp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
         System.out.println("hello word");
            }
        });
        fileMenu.add(pp);
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit((0));
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }
    private class BoardPanel extends JPanel{
        final java.util.List<TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for (int r = 7;r>=0; r--){
                for (int c = 7;c>=0;c--){
                    final TilePanel tilePanel = new TilePanel(this, r,c);
                    this.boardTiles.add(tilePanel);
                    add(tilePanel);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();


        }

        public void drawBoard( Board board) {
            removeAll();
            for (final TilePanel tilePanel : boardTiles){
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }
    private class TilePanel extends JPanel{
        private final int row;
        private final int col;
        TilePanel (final BoardPanel boardPanel, final int row, final int col){
            super(new GridBagLayout());
            this.row = row;
            this.col = col;

            setPreferredSize(TILE_PANEL_DIMESION);
            assignTileColor();
           assignTilePieceIcon(chessBoard);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {

                    if (isRightMouseButton(e)) {
                        startSquare = null;
                        endSquare = null;
                        humanPiece = null;
                    }
                    else if (isLeftMouseButton(e)) {
                        if (startSquare == null) {
                            startSquare = chessBoard.getSquare(row, col);
                            humanPiece = startSquare.getPiece();


                            if (humanPiece == null){
                                startSquare = null;
                            }
                        }
                        else {
                            endSquare = chessBoard.getSquare(row, col);
                              ChessMove move  = new ChessMove(startSquare,endSquare,endSquare.getPiece());

                           // chessRoom.getPlayer(Side.WHITE).makeMove(move);




                        }

                    }
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run()
                        {
                            boardPanel.drawBoard(chessBoard);
                        }

                    });

                }



                @Override
                public void mousePressed(final MouseEvent e) {

                }

                @Override
                public void mouseReleased(final MouseEvent e) {

                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });
            validate();
        }


        void drawTile(Board board){
            assignTileColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }
        private void assignTilePieceIcon(final Board board){
            this.removeAll();
            if (board.getSquare(this.row,this.col).getPiece() != null){

                try {
                    final BufferedImage image =
                            ImageIO.read(new File(defaultPieceImagePath + board.getSquare(this.row, this.col).getPiece().toString()
                                    +".gif"));
                    add(new JLabel(new ImageIcon(image)));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        private void assignTileColor() {
            if (((this.row %2 ==0)&&( this.col %2 ==0))||((this.row %2 ==1)&&(this.col %2 ==1)))
                setBackground(Color.BLACK);
            else{
                setBackground(Color.WHITE);
            }
        }



                }
            }




