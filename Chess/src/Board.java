import java.util.List;

public class Board {
	public Square[][] board;
	int turn;
	Square whiteKing, blackKing;

	public Board() {
		board = new Square[8][8];
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new Square(x, y);
			}
		}
		turn = 0;
		for (int i = 0; i < 8; i++) {
			board[i][1].piece = new Pawn(i, 1, true);
		}
		board[0][0].piece = new Rook(0, 0, true);
		board[7][0].piece = new Rook(7, 0, true);
		board[1][0].piece = new Knight(1, 0, true);
		board[6][0].piece = new Knight(6, 0, true);
		board[2][0].piece = new Bishop(2, 0, true);
		board[5][0].piece = new Bishop(5, 0, true);
		board[3][0].piece = new Queen(true);
		board[4][0].piece = new King(true);
		whiteKing = board[4][0];
		for (int i = 0; i < 8; i++) {
			board[i][6].piece = new Pawn(i, 6, false);
		}
		board[0][7].piece = new Rook(0, 7, false);
		board[7][7].piece = new Rook(7, 7, false);
		board[1][7].piece = new Knight(1, 7, false);
		board[6][7].piece = new Knight(6, 7, false);
		board[2][7].piece = new Bishop(2, 7, false);
		board[5][7].piece = new Bishop(5, 7, false);
		board[3][7].piece = new Queen(false);
		board[4][7].piece = new King(false);
		blackKing = board[4][7];
	}

	public List<Square> select(int x, int y) {
		if (turn%2==0 ^ board[x][y].piece.isWhite())
			return null;
		return board[x][y].piece.possibleMoves(this);
	}

	public void moveKing(Square a, Square b) {
		if (a.x == 4 && b.x == 6) {
			b.piece = a.piece;
			a.piece.move(b.x, b.y);
			a.piece = null;
			board[5][a.y].piece = board[7][a.y].piece;
			board[5][a.y].piece.move(5, a.y);
			board[7][a.y].piece = null;
		} else if (a.x == 4 && b.x == 2) {
			b.piece = a.piece;
			a.piece.move(b.x, b.y);
			a.piece = null;
			board[5][a.y].piece = board[7][a.y].piece;
			board[5][a.y].piece.move(5, a.y);
			board[7][a.y].piece = null;
		} else {
			b.piece = a.piece;
			a.piece.move(b.x, b.y);
			a.piece = null;
		}
		if (b.piece.isWhite())
			whiteKing = b;
		else
			blackKing = b;
	}

	public void movePawn(Square a, Square b) {
		if (((Pawn) a.piece).moves == 0)
			((Pawn) a.piece).firstMoveTurn = turn;
		if (b.piece == null && a.x != b.x) {
			b.piece = a.piece;
			a.piece = null;
			b.piece.move(b.x, b.y);
			if (b.piece.isWhite()) {
				board[b.x][b.y - 1].piece = null;
			} else {
				board[b.x][b.y + 1].piece = null;
			}
		} else {
			b.piece = a.piece;
			a.piece.move(b.x, b.y);
			a.piece = null;
		}
	}

	/*
	 * x=1: queen	x=2: rook 	x=3: knight	 x=4: bishop
	 */
	public void promote(Square a, Square b, int x) {
		boolean white = a.piece.isWhite();
		switch (x) {
		case 1:
			b.piece = new Queen(white);
			b.piece.move(b.x, b.y);
			break;
		case 2:
			b.piece = new Rook(b.x, b.y, white);
			break;
		case 3:
			b.piece = new Knight(b.x, b.y, white);
			break;
		case 4:
			b.piece = new Bishop(b.x, b.y, white);
			break;
		default:
			break;
		}
		a.piece = null;
	}

	public void move(Square a, Square b) {
		if (a.piece == null || !a.piece.possibleMoves(this).contains(b))
			return;
		if (turn%2==0 ^ a.piece.isWhite())
			return;
		else if (a.piece instanceof King) {
			moveKing(a, b);
		} else if (a.piece instanceof Pawn) {
			movePawn(a, b);
		} else {
			b.piece = a.piece;
			a.piece.move(b.x, b.y);
			a.piece = null;
		}
		turn++;
	}

	public void print() {
		String[] s = new String[8];
		for (int y = 7; y >= 0; y--) {
			for (int x = 0; x < 8; x++) {
				if (board[x][y].piece != null)
					s[x] = board[x][y].piece.getClass().getName();
				else
					s[x] = "Nothing";
			}
			System.out.format("%10s%10s%10s%10s%10s%10s%10s%10s",s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7]);
			System.out.println();
		}
	}
}
