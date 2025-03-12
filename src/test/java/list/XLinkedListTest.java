package list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

@DisplayName("연결 리스트 테스트")
public class XLinkedListTest {
    private XList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new XLinkedList<>();
    }

    @DisplayName("요소 추가 및 조회 테스트")
    @Test
    void testAddAndGet() {
        list.add(10);
        list.add(20);
        list.add(1, 15);

        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @DisplayName("특정 인덱스에 요소 추가 테스트")
    @Test
    void testAddAtIndex() {
        list.add(10);
        list.add(20);
        list.add(1, 15);

        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @DisplayName("인덱스로 요소 삭제 테스트")
    @Test
    void testRemoveByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(20, list.remove(1)); // 20을 삭제

        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @DisplayName("특정 요소 삭제 테스트")
    @Test
    void testRemoveByElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.remove(Integer.valueOf(20)));
        assertFalse(list.contains(20));
        assertTrue(list.contains(10));
        assertEquals(2, list.size());
    }

    @DisplayName("요소 포함 여부 테스트")
    @Test
    void testContains() {
        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertFalse(list.contains(30));
    }

    @DisplayName("요소 인덱스 조회 테스트")
    @Test
    void testIndexOf() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(1, list.indexOf(20));
        assertEquals(0, list.indexOf(10));
        assertEquals(-1, list.indexOf(40));
    }

    @DisplayName("요소 변경 테스트")
    @Test
    void testSetElement(){
        list.add(10);
        list.add(20);
        list.set(1,1);
        assertEquals(1,list.get(1));
    }

    @DisplayName("정렬 테스트")
    @Test
    void testSort() {
        list.add(30);
        list.add(10);
        list.add(20);

        list.sort(Comparator.naturalOrder()); // 오름차순 정렬

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @DisplayName("부분 리스트 테스트")
    @Test
    void testSubList() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        XList<Integer> subList = list.subList(1, 3);
        assertEquals(2, subList.size());
        assertEquals(20, subList.get(0));
        assertEquals(30, subList.get(1));
    }

    @DisplayName("리스트 병합 테스트")
    @Test
    void testAddAll() {
        list.add(10);
        XList<Integer> xList = new XLinkedList<>();
        xList.add(20);
        xList.add(30);
        xList.add(40);
        list.addAll(xList);
        assertEquals(4, list.size());
        assertEquals(30, list.get(2));
    }

    @DisplayName("리스트 비우기 테스트")
    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.copy().isEmpty());
    }

    @DisplayName("리스트 복사 테스트")
    @Test
    void testCopy() {
        list.add(10);
        list.add(20);
        XList<Integer> copiedList = list.copy();

        System.out.println(list);
        System.out.println(copiedList);
        assertEquals(2, copiedList.size());
        assertEquals(10, copiedList.get(0));
        assertEquals(20, copiedList.get(1));
    }

    @DisplayName("잘못된 인덱스 접근 테스트")
    @Test
    void testInvalidIndexAccess() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @DisplayName("잘못된 인덱스 삭제 테스트")
    @Test
    void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @DisplayName("null 입력 검증 테스트")
    @Test
    void testNullValidation() {
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertThrows(NullPointerException.class, () -> list.remove(null));
    }

    @DisplayName("인덱스 메소드로 첫 요소 삭제 테스트")
    @Test
    void testRemoveFirstElementWithIndexMethod() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer removed = list.remove(0);

        assertEquals(10, removed);
        assertEquals(2, list.size());
        assertEquals(20, list.get(0));
    }

    @DisplayName("인덱스 메소드로 마지막 요소 삭제 테스트")
    @Test
    void testRemoveLastElementWithIndexMethod() {
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println(list);
        Integer removed = list.remove(2);

        assertEquals(30, removed);
        assertEquals(2, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @DisplayName("값으로 삭제 경계 케이스 테스트")
    @Test
    void testRemoveByValueEdgeCases() {
        list.add(10);
        boolean result = list.remove(Integer.valueOf(10));

        assertTrue(result);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(10);
        list.add(20);
        list.add(30);

        result = list.remove(Integer.valueOf(10));

        assertTrue(result);
        assertEquals(2, list.size());
        assertEquals(20, list.get(0));

        list.add(40);
        result = list.remove(Integer.valueOf(40));

        assertTrue(result);
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    @DisplayName("첫 요소 추가 메소드 테스트")
    @Test
    void testAddFirstMethod() {
        list.add(20);
        list.add(30);
        list.add(0, 10);
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @DisplayName("빈 리스트 연산 테스트")
    @Test
    void testEmptyListOperations() {
        // 빈 리스트에서 다양한 연산 테스트
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertFalse(list.contains(10));
        assertEquals(-1, list.indexOf(10));

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));
    }
}