package list;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class XArrayListTest {
    private XList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new XArrayList<>();
    }

    // 요소 추가 및 조회 테스트
    @Test
    void testAddAndGet() {
        list.add(10);
        list.add(20);
        list.add(1, 15);
        System.out.println(list.toString());
        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    // 특정 인덱스에 요소 추가 테스트
    @Test
    void testAddAtIndex() {
        list.add(10);
        list.add(20);
        list.add(1, 15);

        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    // 요소 삭제 테스트
    @Test
    void testRemoveByIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list.toString());
        assertEquals(20, list.remove(1)); // 20을 삭제
        System.out.println(list.toString());
        assertEquals(2, list.size());
        assertEquals(30, list.get(1));
    }

    // 특정 요소 삭제 테스트
    @Test
    void testRemoveByElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.remove(Integer.valueOf(20)));
        assertFalse(list.contains(20));
        assertEquals(2, list.size());
    }

    // 요소 포함 여부 테스트
    @Test
    void testContains() {
        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertFalse(list.contains(30));
    }

    // 요소 인덱스 조회 테스트
    @Test
    void testIndexOf() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(40));
    }

    // 정렬 테스트
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

    // 부분 리스트 테스트
    @Test
    void testSubList() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        XList<Integer> subList = list.subList(1, 3);
        System.out.println(subList);
        assertEquals(2, subList.size());
        assertEquals(20, subList.get(0));
        assertEquals(30, subList.get(1));
    }

    // 리스트 병합 테스트
    @Test
    void testAddAll() {
        list.add(10);
        XList<Integer> xlist = new XArrayList<>();
        xlist.add(20);
        xlist.add(30);
        xlist.add(40);
        list.addAll(xlist);

        assertEquals(4, list.size());
        assertEquals(30, list.get(2));
    }

    // 리스트 비우기 테스트
    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.copy().isEmpty());
    }

    // 리스트 복사 테스트
    @Test
    void testCopy() {
        list.add(10);
        list.add(20);
        XList<Integer> copiedList = list.copy();

        assertEquals(2, copiedList.size());
        assertEquals(10, copiedList.get(0));
        assertEquals(20, copiedList.get(1));
    }

    // 잘못된 인덱스 접근 테스트
    @Test
    void testInvalidIndexAccess() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    // 잘못된 인덱스 삭제 테스트
    @Test
    void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    // **null** 입력 검증 테스트
    @Test
    void testNullValidation() {
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertThrows(NullPointerException.class, () -> list.remove(null));
    }
}
