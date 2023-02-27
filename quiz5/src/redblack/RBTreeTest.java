package redblack;
import static org.junit.Assert.*;
import org.junit.Test;
import redblack.RBTree;

public class RBTreeTest {
  @Test 
	public void testOneElement() {
		String expectString = "{(0,RED),(1,BLACK),(2,BLACK),(3,RED),(5,BLACK),(22,RED)}";
    RBTree testTree = new RBTree();
		testTree.insert(2);
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(0);
		testTree.insert(5);
		testTree.insert(22);
		String result = testTree.toString();
    assertEquals(expectString,result);
	}
}
