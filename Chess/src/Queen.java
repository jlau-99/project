import java.util.ArrayList;
import java.util.List;

public class Queen implements IPiece {
	public boolean white;
	public int[] point;

	public Queen(boolean a) {
		white = a;
		point = new int[2];
		point[0] = 3;
		if (white)
			point[1] = 0;
		else
			point[1] = 7;
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
		int index = point[0];
		while (index > 0) {
			if (b.board[--index][point[1]].piece == null)
				tempList.add(b.board[index][point[1]]);
			else {
				if (white ^ b.board[index][point[1]].piece.isWhite())
					tempList.add(b.board[index][point[1]]);
				break;
			}
		}
		index = point[0];
		while (index < 7) {
			if (b.board[++index][point[1]].piece == null)
				tempList.add(b.board[index][point[1]]);
			else {
				if (white ^ b.board[index][point[1]].piece.isWhite())
					tempList.add(b.board[index][point[1]]);
				break;
			}
		}
		index = point[1];
		while (index > 0) {
			if (b.board[point[0]][--index].piece == null)
				tempList.add(b.board[point[0]][index]);
			else {
				if (white ^ b.board[point[0]][index].piece.isWhite())
					tempList.add(b.board[point[0]][index]);
				break;
			}
		}
		index = point[1];
		while (index < 7) {
			if (b.board[point[0]][++index].piece == null)
				tempList.add(b.board[point[0]][index]);
			else {
				if (white ^ b.board[point[0]][index].piece.isWhite())
					tempList.add(b.board[point[0]][index]);
				break;
			}
		}
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
		int index = point[0];
		while (index > 0) {
			if (b.board[--index][point[1]].piece == null)
				list.add(b.board[index][point[1]]);
			else {
				list.add(b.board[index][point[1]]);
				break;
			}
		}
		index = point[0];
		while (index < 7) {
			if (b.board[++index][point[1]].piece == null)
				list.add(b.board[index][point[1]]);
			else {
				list.add(b.board[index][point[1]]);
				break;
			}
		}
		index = point[1];
		while (index > 0) {
			if (b.board[point[0]][--index].piece == null)
				list.add(b.board[point[0]][index]);
			else {
				list.add(b.board[point[0]][index]);
				break;
			}
		}
		index = point[1];
		while (index < 7) {
			if (b.board[point[0]][++index].piece == null)
				list.add(b.board[point[0]][index]);
			else {
				list.add(b.board[point[0]][index]);
				break;
			}
		}
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
