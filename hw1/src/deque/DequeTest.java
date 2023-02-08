package deque;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.BeforeClass;
import org.junit.Test;

public class DequeTest {
	
	static ArrayList<Integer> toCompareOne;
	static ArrayList<Integer> toCompareMany;
	static ArrayList<Integer> sorted;
	
	 @BeforeClass
	    public static void setup() {
			toCompareOne = new ArrayList<>();
			toCompareOne.add(1);
			
			toCompareMany = new ArrayList<>();
			for(int i=0;i<10;i++) {
				toCompareMany.add((int)(Math.random()*i));
			}
			
			sorted=new ArrayList<>();
			for(int i=0;i<5;i++) {
				sorted.add(i);
			}
	    }
	
	@Test
	public void oneElement() {
		Deque one = new Deque();
		one.insertHead(1);
		
		assertEquals(one.toArrayList(),toCompareOne);
	}
	
	@Test
	public void ManyElementsTail() {
		Deque many = new Deque();
		for(int i:toCompareMany)
			many.insertTail(i);
		assertEquals(many.toArrayList(),toCompareMany);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeHeadFromEmpty() {
		Deque empty = new Deque();
		empty.removeHead();
	}

	@Test(expected = NoSuchElementException.class)
	public void removeTailFromEmpty() {
		Deque empty = new Deque();
		empty.removeTail();
	}

	@Test 
	public void sorted() {
		Deque notSorted = new Deque();
		for(int i: sorted) {
			notSorted.insertHead(i);
		}
		Deque sortTest = notSorted.sort();
		assertEquals(sortTest.toArrayList(),sorted);
	}

	/*
	 * ---------- My test cases start here ----------
	 */

	// 1. Test the one node deque case -> return an empty string when call toString() method
	@Test 
	public void removeHeadFromOneNodeDeque() {
		Deque oneNode = new Deque();
		oneNode.insertHead(1);
		oneNode.removeHead();

		assertEquals("", oneNode.toString());
	}

	// 2. Test the one node deque case -> return an empty string when call toString() method
	@Test 
	public void removeTailFromOneNodeDeque() {
		Deque oneNode = new Deque();
		oneNode.insertHead(1);
		oneNode.removeTail();

		assertEquals("", oneNode.toString());
	}	

	// 3. Test randomly inserting from head and tail feature. Goal to return a deque consists 0 to 9
	@Test
	public void insertRandom() {
		String testStr = "0,1,2,3,4,5,6,7,8,9";
		Deque testDeque = new Deque();

		testDeque.insertHead(5);
		testDeque.insertTail(6);
		testDeque.insertHead(4);
		testDeque.insertHead(3);
		testDeque.insertHead(2);		
		testDeque.insertTail(7);
		testDeque.insertTail(8);
		testDeque.insertHead(1);
		testDeque.insertHead(0);
		testDeque.insertTail(9);

		assertEquals(testStr, testDeque.toString());
	}
	
	// 4. Test sorting an empty deque. Goal to return an empty string
	@Test 
	public void sortEmpty() {
		Deque testDeque = new Deque();
		assertEquals("", testDeque.sort().toString());
	}

	// 5. Test a deque with insert and sort
	@Test
	public void insertAndSort() {
		String test = "0,1,2,3,4,5,6,7,8,9";
		Deque testDeque = new Deque();

		testDeque.insertTail(3);
		testDeque.insertHead(2);		
		testDeque.insertHead(7);
		testDeque.insertTail(6);
		testDeque.insertHead(4);		
		testDeque.insertHead(1);
		testDeque.insertHead(0);
		testDeque.insertTail(9);
		testDeque.insertTail(8);
		testDeque.insertHead(5);
		
		assertEquals(test, testDeque.sort().toString());
	}

	// 6. Test remove tail in regular case
	@Test
	public void removeTailFromRegularDeque() {
		String test = "3,4,5,6";
		Deque testDeque = new Deque();

		testDeque.insertTail(3);
		testDeque.insertTail(4);
		testDeque.insertTail(5);
		testDeque.insertTail(6);
		testDeque.insertTail(7);

		testDeque.removeTail();
		
		assertEquals(test, testDeque.toString());
	}

}
