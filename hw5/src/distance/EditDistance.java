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
    
}
