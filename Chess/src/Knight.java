import java.util.ArrayList;
import java.util.List;

public class Knight implements IPiece{
	public boolean white;
	public int[] point;
	public Knight(int x, int y, boolean a) {
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
		List<Square> tempList = new ArrayList<>();
		int x = point[0];
		int y = point[1];
		Square here = b.board[x][y];
		int[][] points = {{x+1,y+2},{x+2,y+1},{x-1,y-2},{x-2,y-1},{x-1,y+2}
				,{x-2,y+1},{x+1,y-2},{x+2,y-1}};
		for (int i = 0; i < 8; i++) {
			x = points[i][0];
			y = points[i][1];
			if (0 <= x && x < 8 && 0 <= y && y < 8) {
				if (b.board[x][y].piece == null || (white ^ b.board[x][y].piece.isWhite()))
				tempList.add(b.board[x][y]);
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
		int[][] points = {{x+1,y+2},{x+2,y+1},{x-1,y-2},{x-2,y-1},{x-1,y+2}
				,{x-2,y+1},{x+1,y-2},{x+2,y-1}};
		for (int i = 0; i < 8; i++) {
			x = points[i][0];
			y = points[i][1];
			if (0 <= x && x < 8 && 0 <= y && y < 8) {
				list.add(b.board[x][y]);
			}
		}
		return list;
	}

}
