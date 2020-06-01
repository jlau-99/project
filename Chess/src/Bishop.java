import java.util.ArrayList;
import java.util.List;

public class Bishop implements IPiece {
	public boolean white;
	public int[] point;

	public Bishop(int x, int y, boolean a) {
		point = new int[2];
		point[0] = x;
		point[1] = y;
		white = a;
	}

	public boolean notInCheck(Square a, Square b, Board brd) {
		boolean ret;
		IPiece tempA = a.piece;
		IPiece tempB = b.piece;
		b.piece = a.piece;
		a.piece = null;
		if (white && brd.whiteKing.underBlackAttack(brd))
			ret = false;
		else if (!white && brd.blackKing.underWhiteAttack(brd))
			ret = false;
		else
			ret = true;
		a.piece = tempA;
		b.piece = tempB;
		return ret;
	}

	public List<Square> possibleMoves(Board b) {
		Square here = b.board[point[0]][point[1]];
		List<Square> tempList = new ArrayList<>();
		int x = point[0];
		int y = point[1];
		while (x > 0 && y > 0) {
			if (b.board[--x][--y].piece == null)
				tempList.add(b.board[x][y]);
			else {
				if (white ^ b.board[x][y].piece.isWhite())
					tempList.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x < 7 && y > 0) {
			if (b.board[++x][--y].piece == null)
				tempList.add(b.board[x][y]);
			else {
				if (white ^ b.board[x][y].piece.isWhite())
					tempList.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x > 0 && y < 7) {
			if (b.board[--x][++y].piece == null)
				tempList.add(b.board[x][y]);
			else {
				if (white ^ b.board[x][y].piece.isWhite())
					tempList.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x < 7 && y < 7) {
			if (b.board[++x][++y].piece == null)
				tempList.add(b.board[x][y]);
			else {
				if (white ^ b.board[x][y].piece.isWhite())
					tempList.add(b.board[x][y]);
				break;
			}
		}
		List<Square> list = new ArrayList<>();
		for (Square s : tempList) {
			if (notInCheck(here, s, b))
				list.add(s);
		}
		return list;
	}

	public boolean isWhite() {
		return white;
	}

	public void move(int x, int y) {
		point[0] = x;
		point[1] = y;
	}

	public List<Square> attackingSquares(Board b) {
		List<Square> list = new ArrayList<>();
		int x = point[0];
		int y = point[1];
		while (x > 0 && y > 0) {
			if (b.board[--x][--y].piece == null)
				list.add(b.board[x][y]);
			else {
				list.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x < 7 && y > 0) {
			if (b.board[++x][--y].piece == null)
				list.add(b.board[x][y]);
			else {
				list.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x > 0 && y < 7) {
			if (b.board[--x][++y].piece == null)
				list.add(b.board[x][y]);
			else {
				list.add(b.board[x][y]);
				break;
			}
		}
		x = point[0];
		y = point[1];
		while (x < 7 && y < 7) {
			if (b.board[++x][++y].piece == null)
				list.add(b.board[x][y]);
			else {
				list.add(b.board[x][y]);
				break;
			}
		}
		return list;
	}

}
