
public class Square {
	public int x,y;
	public IPiece piece;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean underWhiteAttack(Board b) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (b.board[x][y].piece!=null && b.board[x][y].piece.isWhite())
					if (b.board[x][y].piece.attackingSquares(b).contains(this))
						return true;
			}
		}
		return false;
	}
	
	public boolean underBlackAttack(Board b) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (b.board[x][y].piece!=null && !b.board[x][y].piece.isWhite())
					if (b.board[x][y].piece.attackingSquares(b).contains(this))
						return true;
			}
		}
		return false;
	}
	
	public void print() {
		System.out.print("("+x+","+y+"), ");
	}
}
