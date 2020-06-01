
public class Test {

	public static void main(String[] args) {
		Board b = new Board();
		Square[][] q = b.board;
		b.move(q[4][1],q[4][3]);
		b.move(q[5][6], q[5][5]);
		b.move(q[3][0], q[7][4]);
		b.print();
		for (Square s : b.board[6][6].piece.possibleMoves(b)) {
			s.print();
		}
	}

}
