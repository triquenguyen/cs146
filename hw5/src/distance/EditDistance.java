package distance;

public class EditDistance {

	// Enum for storing how characters align
	enum Operation {
		MATCH, MISMATCH, INSERT, DELETE;
	}

	// inner class for storing not only the edit distance, but also the path for
	// back tracking
	static class Path {
		char row, col; // what characters am I looking at
		int cost; // how much does this cell cost
		Operation op; // did I get here by insertion, deletion, match, or mismatch;
		Path next; // where do I go next;

		Path(char r, char c, int cost, Operation o, Path next) {
			row = r; // character from current row
			col = c; // character from current column
			this.cost = cost;
			op = o;
			this.next = next;
		}
	}

	private static final int DELETE_COST = 1;
	private static final int INSERT_COST = 1;
	private static final int MISMATCH_COST = 2;

	static Path minEditDistance(String source, String target) {
		int n = source.length();
		int m = target.length();
		Path[][] distances = new Path[n + 1][m + 1];
		distances[0][0] = new Path('-', '-', 0, Operation.MATCH, null);

		for (int i = 1; i <= n; i++) {
			distances[i][0] = new Path(
					source.charAt(i - 1),
					'-',
					i + DELETE_COST,
					Operation.DELETE,
					distances[i - 1][0]);
		}

		for (int j = 1; j <= m; j++) {
			distances[0][j] = new Path(
					'-',
					target.charAt(j - 1),
					j + INSERT_COST,
					Operation.INSERT,
					distances[0][j - 1]);
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int deleteCost = distances[i - 1][j].cost + DELETE_COST;
				int insertCost = distances[i][j - 1].cost + INSERT_COST;
				int misMatchCost = distances[i - 1][j - 1].cost + MISMATCH_COST;
				int minCost = min(deleteCost, insertCost, misMatchCost);

				char setRow, setCol;
				Operation setOp;
				Path setNextPath;

				if (minCost == misMatchCost) {
					setRow = source.charAt(i - 1);
					setCol = target.charAt(j - 1);
					setOp = source.charAt(i - 1) == target.charAt(j - 1) ? Operation.MATCH : Operation.MISMATCH;

					if (setOp == Operation.MATCH) {
						minCost = distances[i - 1][j - 1].cost;
					}

					setNextPath = distances[i - 1][j - 1];

				} else if (deleteCost == minCost) {
					setRow = source.charAt(i - 1);
					setCol = '-';
					setOp = Operation.DELETE;
					setNextPath = distances[i - 1][j];

				} else {
					setRow = '-';
					setCol = target.charAt(j - 1);
					setOp = Operation.INSERT;
					setNextPath = distances[i][j - 1];
				}

				distances[i][j] = new Path(setRow, setCol, minCost, setOp, setNextPath);
			}
		}

		return distances[n][m];
	}

	static int min(int a, int b, int c) {
		if (a <= b && a <= c) {
			return a;
		} else if (b <= a && b <= c) {
			return b;
		} else {
			return c;
		}
	}

	/*
	 * This method returns a pretty version of the path that marks insertions,
	 * deletions, and (mis)matches
	 */
	static String printPath(Path p) {
		String s = "";
		Path current = p;
		while (current.next != null) {// ignore the empty strings
			s = current.row + " " + current.col + " " + current.op + "\n" + s;
			current = current.next;
		}
		return s;
	}

	public static void main(String[] args) {
		String source = "thisissorandom";
		String target = "ihopethereisnomatch";
		Path testPath = minEditDistance(source, target);

		System.out.println(printPath(testPath));
		System.out.println(testPath.cost);
	}
}
