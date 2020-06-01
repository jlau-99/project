import java.util.ArrayList;
import java.util.List;

public class Pawn implements IPiece {
	public boolean white;
	public int[] point;
	public int firstMoveTurn, moves = 0;

	public Pawn(int x, int y, boolean a) {
		point = new int[2];
		point[0] = x;
		point[1] = y;
		white = a;
	}

	public boolean isWhite() {
		return white;
	}

	public List<Square> attackingSquares(Board b) {
		List<Square> list = new ArrayList<>();
		if (white) {
			if (point[0] != 0)
				list.add(b.board[point[0] - 1][point[1] + 1]);
			if (point[0] != 7)
				list.add(b.board[point[0] + 1][point[1] + 1]);
		} else {
			if (point[0] != 0)
				list.add(b.board[point[0] - 1][point[1] - 1]);
			if (point[0] != 7)
				list.add(b.board[point[0] + 1][point[1] - 1]);
		}
		return list;
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
		Square sqr, here;
		here = b.board[point[0]][point[1]];
		if (white) {
			tempList.add(b.board[point[0]][point[1] + 1]);
			if (point[0] != 0) {
				sqr = b.board[point[0] - 1][point[1] + 1];
				if (sqr.piece != null && !sqr.piece.isWhite())
					tempList.add(sqr);
				sqr = b.board[point[0] - 1][point[1]];

				// en passant
				if (sqr.piece != null && !sqr.piece.isWhite() && sqr.piece instanceof Pawn
						&& ((Pawn) sqr.piece).firstMoveTurn + 1 == b.turn)
					tempList.add(b.board[point[0] - 1][point[1] + 1]);
			}
			if (point[0] != 7) {
				sqr = b.board[point[0] + 1][point[1] + 1];
				if (sqr.piece != null && !sqr.piece.isWhite())
					tempList.add(sqr);
				sqr = b.board[point[0] + 1][point[1]];

				// en passant
				if (sqr.piece != null && !sqr.piece.isWhite() && sqr.piece instanceof Pawn
						&& ((Pawn) sqr.piece).firstMoveTurn + 1 == b.turn)
					tempList.add(b.board[point[0] - 1][point[1] + 1]);
			}
		}
		if (!white) {
			tempList.add(b.board[point[0]][point[1] - 1]);
			if (point[0] != 0) {
				sqr = b.board[point[0] - 1][point[1] - 1];
				if (sqr.piece != null && sqr.piece.isWhite())
					tempList.add(sqr);
				sqr = b.board[point[0] - 1][point[1]];

				// en passant
				if (sqr.piece != null && sqr.piece.isWhite() && sqr.piece instanceof Pawn
						&& ((Pawn) sqr.piece).firstMoveTurn + 1 == b.turn)
					tempList.add(b.board[point[0] - 1][point[1] - 1]);
			}
			if (point[0] != 7) {
				sqr = b.board[point[0] + 1][point[1] - 1];
				if (sqr.piece != null && sqr.piece.isWhite())
					tempList.add(sqr);
				sqr = b.board[point[0] + 1][point[1]];

				// en passant
				if (sqr.piece != null && sqr.piece.isWhite() && sqr.piece instanceof Pawn
						&& ((Pawn) sqr.piece).firstMoveTurn + 1 == b.turn)
					tempList.add(b.board[point[0] - 1][point[1] - 1]);
			}
		}
		if (moves == 0) {
			if (white)
				tempList.add(b.board[point[0]][point[1] + 2]);
			else
				tempList.add(b.board[point[0]][point[1] - 2]);
		}
		List<Square> list = new ArrayList<>();
		for (Square s : tempList) {
			if (notInCheck(here, s, b))
				list.add(s);
		}
		return list;
	}

	public void move(int x, int y) {
		point[0] = x;
		point[1] = y;
		moves++;
	}
}