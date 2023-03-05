package trees;

import trees.RBTree.Color;//import the enum
import trees.RBTree.Node;//import the Node inner class
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

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

	// 1: test > 6 nodes tree with inorder iteration
	@Test
	public void test1() {
		RBTree testTree = new RBTree();
		testTree.insert(0);
		testTree.insert(2);
		testTree.insert(8);
		testTree.insert(100);
		testTree.insert(22);
		testTree.insert(1000);
		testTree.insert(600);
		testTree.insert(-191);
		testTree.insert(4);

		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: testTree.inOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-191, 0, 2, 4, 8, 22, 100, 600, 1000));
		
		assertEquals(expected,actual);
	}

	// 2: test > 6 nodes tree with preoder iteration
	@Test
	public void test2() {
		RBTree testTree = new RBTree();
		testTree.insert(0);
		testTree.insert(2);
		testTree.insert(8);
		testTree.insert(100);
		testTree.insert(22);
		testTree.insert(1000);
		testTree.insert(600);
		testTree.insert(-191);
		testTree.insert(4);

		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: testTree.preOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(2, 0, -191, 22, 8,4, 600, 100, 1000));
		
		assertEquals(expected,actual);
	}

	// 3: test > 6 nodes tree with preoder iteration
	@Test
	public void test3() {
		RBTree testTree = new RBTree();
		testTree.insert(0);
		testTree.insert(2);
		testTree.insert(8);
		testTree.insert(100);
		testTree.insert(22);
		testTree.insert(1000);
		testTree.insert(600);
		testTree.insert(-191);
		testTree.insert(4);

		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: testTree.postOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-191, 0, 4, 8, 100, 1000, 600, 22, 2));
		
		assertEquals(expected,actual);
	}

	// 4: test > 10 nodes tree with inorder iteration
	@Test
	public void test4() {
		RBTree testTree = new RBTree();
		testTree.insert(10);
		testTree.insert(85);
		testTree.insert(15);
		testTree.insert(70);
		testTree.insert(20);
		testTree.insert(60);
		testTree.insert(30);
		testTree.insert(50);
		testTree.insert(65);
		testTree.insert(80);
		testTree.insert(90);
		testTree.insert(40);
		testTree.insert(5);
		testTree.insert(55);

		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: testTree.inOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(5, 10, 15, 20, 30, 40, 50, 55, 60, 65, 70, 80, 85, 90));
		
		assertEquals(expected,actual);
	}

	// 5: test duplicate
	@Test
	public void test5() {
		RBTree testTree = new RBTree();
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(3);
		testTree.insert(0);
		testTree.insert(5);
		testTree.insert(22);
		testTree.insert(6);
		testTree.insert(9);
		testTree.insert(10);

		ArrayList<Integer> actual = new ArrayList<>();
		for(Node n: testTree.inOrder())
			actual.add(n.data);
		
		ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 6, 9, 10, 22));
		
		assertEquals(expected,actual);
	}
}

