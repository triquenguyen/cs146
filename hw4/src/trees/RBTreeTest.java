package trees;

import trees.RBTree.Color;//import the enum
import trees.RBTree.Node;//import the Node inner class
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RBTreeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		RBTree rootTest = new RBTree();
		boolean result = rootTest.insert(1);
		Node n = rootTest.root;
		assertTrue(result);
		assertEquals((Integer)1,n.data);
		assertEquals(Color.BLACK,n.color);
	}
	
	@Test
	public void testInsert1() {
		RBTree test = new RBTree();
		test.insert(1);
		test.insert(0);
		test.insert(2);
		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: test.inOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>();
		for(int i=0;i<3;i++)
			expected.add(i);
		assertEquals(expected,actual);
		
		
		
	}

}
