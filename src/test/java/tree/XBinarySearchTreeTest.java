package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XBinarySearchTree 클래스의 테스트 클래스입니다.
 */
public class XBinarySearchTreeTest {
    private XBinaryTree<Integer> bst;

    /**
     * 각 테스트 메서드 실행 전 호출됩니다. 테스트에 필요한 설정을 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        bst = new XBinarySearchTree<>();
    }

    /**
     * 삽입과 검색 기능을 테스트합니다.
     */
    @Test
    void testInsertionAndSearch() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        assertTrue(bst.search(5));
        assertTrue(bst.search(3));
        assertTrue(bst.search(7));
        assertFalse(bst.search(10));
    }

    /**
     * null 값을 삽입하는 경우를 테스트합니다.
     */
    @Test
    void testInsertionWithNullValue() {
        Exception exception = assertThrows(NullPointerException.class, () -> bst.insert(null));
        assertEquals("Value cannot be null", exception.getMessage());
    }

    /**
     * null 값을 검색하는 경우를 테스트합니다.
     */
    @Test
    void testSearchWithNullValue() {
        Exception exception = assertThrows(NullPointerException.class, () -> bst.search(null));
        assertEquals("Value cannot be null", exception.getMessage());
    }

    /**
     * 삭제 기능을 테스트합니다.
     */
    @Test
    void testDeletion() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.delete(10);

        assertFalse(bst.search(10));
        assertEquals(2, bst.size());
    }

    /**
     * null 값을 삭제하는 경우를 테스트합니다.
     */
    @Test
    void testDeletionWithNullValue() {
        Exception exception = assertThrows(NullPointerException.class, () -> bst.delete(null));
        assertEquals("Value cannot be null", exception.getMessage());
    }

    /**
     * 트리의 크기를 테스트합니다.
     */
    @Test
    void testSize() {
        assertEquals(0, bst.size());
        bst.insert(1);
        bst.insert(2);
        assertEquals(2, bst.size());
    }

    /**
     * 트리의 높이를 테스트합니다.
     */
    @Test
    void testHeight() {
        assertEquals(-1, bst.height()); // 빈 트리의 높이는 -1
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertEquals(1, bst.height());
    }

    /**
     * 중위 순회 결과를 테스트합니다.
     */
    @Test
    void testInorderTraversal() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        // 중위 순회 결과가 "3 5 7" 순서로 출력되어야 함
        assertEquals("[3, 5, 7]", Arrays.toString(bst.inorderTraversal().toArray()));
    }
}
