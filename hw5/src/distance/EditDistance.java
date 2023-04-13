package distance;



public class EditDistance {
	
	//Enum for storing how characters align
	enum Operation {
		MATCH, MISMATCH, INSERT, DELETE;
	}
	
	//inner class for storing not only the edit distance, but also the path for back tracking
	static class Path {
		char row, col; //what characters am I looking at
		int cost; //how much does this cell cost
		Operation op; //did I get here by insertion, deletion, match, or mismatch;
		Path next; //where do I go next;
		
	Path(char r, char c, int cost, Operation o, Path next){
		row = r; //character from current row
		col = c; //character from current column
		this.cost = cost;
		op = o;
		this.next = next;
	}
	}
	
	private static final int  DELETE_COST = 1;
	private static final int  INSERT_COST  = 1;
	private static final int  MISMATCH_COST = 2;
	
	static Path minEditDistance(String source, String target) {
		int n = source.length();
		int m = target.length();
		Path [][] distances = new Path[n+1][m+1];
		distances[0][0] = new Path('-','-',0,Operation.MATCH,null);
		
		for (int i = 1; i <= n; i++) {
			distances[i][0].cost = distances[i-1][0].cost + DELETE_COST;
		}

		for (int j = 1; j <= m; j++) {
			distances[0][j].cost = distances[0][j-1].cost + INSERT_COST;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int min1 = Math.min(distances[i-1][j].cost + DELETE_COST, distances[i][j-1].cost + INSERT_COST);
				int min2 = Math.min(distances[i-1][j].cost + DELETE_COST, distances[i-1][j-1].cost + MISMATCH_COST);
				distances[i][j].cost = Math.min(min1, min2);
			}
		}
		
		return distances[n][m];
	}
	
	/*
	 * This method returns a pretty version of the path that marks insertions, deletions, and (mis)matches
	 */
    static String printPath(Path p) {
		String s = "";
		Path current = p;
		while(current.next!=null) {//ignore the empty strings
			s = current.row+ " "+current.col + " "+ current.op + "\n" + s;
			current = current.next;	
		}
		return s;
	}
	public static void main(String[] args) {
		
	}
    
	
}
