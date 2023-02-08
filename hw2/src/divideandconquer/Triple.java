package divideandconquer;

public class Triple<First,Middle,Last> {
	
	First first;
	Middle middle;
	Last last;
	
	public Triple(First first, Middle middle, Last last) {
		this.first= first;
		this.middle = middle;
		this.last = last;
	}

	public First getFirst() {
		return first;
	}

	public void setFirst(First first) {
		this.first = first;
	}

	public Middle getMiddle() {
		return middle;
	}

	public void setMiddle(Middle middle) {
		this.middle = middle;
	}

	public Last getLast() {
		return last;
	}

	public void setLast(Last last) {
		this.last = last;
	}
	
	

}
