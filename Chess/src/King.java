import java.util.ArrayList;
import java.util.List;

public class King implements IPiece {
	public boolean white, unmoved = true;
	public int[] point;

	public King(boolean a) {
		point = new int[2];
		white = a;
		point[0] = 4;
		if (white)
			point[1] = 0;
		else
			point[1] = 7;
	}

	public boolean isWhite() {
		return white;
	}

	public List<Square> possibleMoves(Board b) {
		List<Square> list = new ArrayList<Square>();
		if (white) {
			if (point[1] < 7)
				for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
					if (!b.board[i][point[1] + 1].underBlackAttack(b))
						list.add(b.board[i][point[1] + 1]);
			if (point[1] > 0)
				for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
					if (!b.board[i][point[1] - 1].underBlackAttack(b))
						list.add(b.board[i][point[1] - 1]);
			if (point[0] != 0 && !b.board[point[0] - 1][point[1]].underBlackAttack(b))
				list.add(b.board[point[0] - 1][point[1]]);
			if (point[0] != 7 && !b.board[point[0] + 1][point[1]].underBlackAttack(b))
				list.add(b.board[point[0] + 1][point[1]]);

			// castling
			if (unmoved && !b.board[4][0].underBlackAttack(b) && b.board[5][0].piece == null
					&& !b.board[5][0].underBlackAttack(b) && b.board[6][0].piece == null
					&& !b.board[6][0].underBlackAttack(b) && b.board[7][0].piece != null
					&& b.board[7][0].piece instanceof Rook && ((Rook) b.board[7][0].piece).unmoved) {
				list.add(b.board[6][0]);
			}
			if (unmoved && !b.board[4][0].underBlackAttack(b) && b.board[1][0].piece == null
					&& b.board[2][0].piece == null && !b.board[2][0].underBlackAttack(b) && b.board[3][0].piece == null
					&& !b.board[3][0].underBlackAttack(b) && b.board[0][0].piece != null
					&& b.board[0][0].piece instanceof Rook && ((Rook) b.board[0][0].piece).unmoved) {
				list.add(b.board[2][0]);
			}
		}

		if (!white) {
			if (point[1] < 7)
				for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
					if (!b.board[i][point[1] + 1].underWhiteAttack(b))
						list.add(b.board[i][point[1] + 1]);
			if (point[1] > 0)
				for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
					if (!b.board[i][point[1] - 1].underWhiteAttack(b))
						list.add(b.board[i][point[1] - 1]);
			if (point[0] != 0 && !b.board[point[0] - 1][point[1]].underWhiteAttack(b))
				list.add(b.board[point[0] - 1][point[1]]);
			if (point[0] != 7 && !b.board[point[0] + 1][point[1]].underWhiteAttack(b))
				list.add(b.board[point[0] + 1][point[1]]);

			// castling
			if (unmoved && !b.board[4][7].underWhiteAttack(b) && b.board[5][7].piece == null
					&& !b.board[5][7].underWhiteAttack(b) && b.board[6][7].piece == null
					&& !b.board[6][7].underWhiteAttack(b) && b.board[7][7].piece != null
					&& b.board[7][7].piece instanceof Rook && ((Rook) b.board[7][7].piece).unmoved) {
				list.add(b.board[6][7]);
			}
			if (unmoved && !b.board[2][7].underWhiteAttack(b) && !b.board[3][7].underWhiteAttack(b)
					&& !b.board[4][7].underWhiteAttack(b) && b.board[1][7].piece == null && b.board[2][7].piece == null
					&& b.board[3][7].piece == null && b.board[0][7].piece != null && b.board[0][7].piece instanceof Rook
					&& ((Rook) b.board[0][7].piece).unmoved) {
				list.add(b.board[2][7]);
			}
		}
		return list;
	}

	public void move(int x, int y) {
		point[0] = x;
		point[1] = y;
		unmoved = false;
	}

	public List<Square> attackingSquares(Board b) {
		List<Square> list = new ArrayList<>();
		if (point[1] != 7)
			for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
				list.add(b.board[i][point[1] + 1]);
		if (point[1] != 0)
			for (int i = Math.max(0, point[0] - 1); i <= Math.min(7, point[0] + 1); i++)
				list.add(b.board[i][point[1] - 1]);
		if (point[0] != 0)
			list.add(b.board[point[0] - 1][point[1]]);
		if (point[0] != 7)
			list.add(b.board[point[0] + 1][point[1]]);
		return list;
	}

}