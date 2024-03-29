import javax.swing.ImageIcon;
import java.util.ArrayList;
// -------------------------------------------------------------------------
/**
 * Represents a Pawn game piece. Unique in that it can move two locations on its
 * first turn and therefore requires a new 'notMoved' variable to keep track of
 * its turns.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Pawn
    extends ChessGamePiece{
    private boolean notMoved;
    // ----------------------------------------------------------
    /**
     * Create a new Pawn object.
     *
     * @param board
     *            the board to create the pawn on
     * @param row
     *            row of the pawn
     * @param col
     *            column of the pawn
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Pawn( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color, true );
        notMoved = true;
        possibleMoves = calculatePossibleMoves( board );
    }
    /**
     * Moves this pawn to a row and col
     *
     * @param board
     *            the board to move on
     * @param row
     *            the row to move to
     * @param col
     *            the col to move to
     * @return boolean true if the move was successful, false otherwise
     */
    @Override
    public boolean move(ChessGameBoard board, int row, int col) {
        if (super.move(board, row, col)) {
            notMoved = false;
            possibleMoves = calculatePossibleMoves(board);
            if ((getColorOfPiece() == ChessGamePiece.BLACK && row == 7)
                    || (getColorOfPiece() == ChessGamePiece.WHITE && row == 0)) {
                promoteToQueen(board, row, col);
            }
            return true;
        }
        return false;
    }
    /**
     * Calculates the possible moves for this piece. These are ALL the possible
     * moves, including illegal (but at the same time valid) moves.
     *
     * @param board
     *            the game board to calculate moves on
     * @return ArrayList<String> the moves
     */
    //Code Smell: LONG METHOD. El método calculatePossibleMoves() es extenso. 
    //Técnica: EXTRACT METHOD. Se dividió la lógica en métodos más pequeños y específicos.
    @Override
    protected ArrayList<String> calculatePossibleMoves(ChessGameBoard board) {
        ArrayList<String> moves = new ArrayList<String>();
        if (isPieceOnScreen()) {
            int currRow = getColorOfPiece() == ChessGamePiece.WHITE ? (pieceRow - 1) : (pieceRow + 1);
            int count = 1;
            int maxIter = notMoved ? 2 : 1;
            // check for normal moves
            while (count <= maxIter && isOnScreen(currRow, pieceColumn) && isEmptyCell(board, currRow, pieceColumn)) {
                moves.add(currRow + "," + pieceColumn);
                currRow = getColorOfPiece() == ChessGamePiece.WHITE ? (currRow - 1) : (currRow + 1);
                count++;
            }
            // check for enemy capture points
            if (getColorOfPiece() == ChessGamePiece.WHITE) {
                checkEnemyCapture(board, moves, pieceRow - 1, pieceColumn - 1);
                checkEnemyCapture(board, moves, pieceRow - 1, pieceColumn + 1);
            } else {
                checkEnemyCapture(board, moves, pieceRow + 1, pieceColumn - 1);
                checkEnemyCapture(board, moves, pieceRow + 1, pieceColumn + 1);
            }
        }
        return moves;
    }

    private boolean isEmptyCell(ChessGameBoard board, int row, int col) {
        return board.getCell(row, col).getPieceOnSquare() == null;
    }
    private void checkEnemyCapture(ChessGameBoard board, ArrayList<String> moves, int row, int col) {
        if (isEnemy(board, row, col)) {
            moves.add(row + "," + col);
        }
    }

    /**
     * Creates an icon for this piece depending on the piece's color.
     *
     * @return ImageIcon the ImageIcon representation of this piece.
     */
   private void promoteToQueen(ChessGameBoard board, int row, int col) {
        board.getCell(row, col).setPieceOnSquare(new Queen(board, row, col, getColorOfPiece()));
    }

    @Override
    public ImageIcon createImageByPieceType() {
        String imageFileName;
        if (getColorOfPiece() == ChessGamePiece.WHITE) {
            imageFileName = "chessImages/WhitePawn.gif";
        } else if (getColorOfPiece() == ChessGamePiece.BLACK) {
            imageFileName = "chessImages/BlackPawn.gif";
        } else {
            imageFileName = "chessImages/default-Unassigned.gif";
        }
        return new ImageIcon(getClass().getResource(imageFileName));
    }
}
