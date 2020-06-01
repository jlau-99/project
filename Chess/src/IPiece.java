import java.util.List;

public interface IPiece {
	public List<Square> possibleMoves(Board b);

	public boolean isWhite();

	public void move(int x, int y);

	public List<Square> attackingSquares(Board b);
}
