package set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

/**
 * XHashSet 클래스의 테스트 클래스입니다.
 */
public class XHashSetTest {
    private XSet<Integer> setA;
    private XSet<Integer> setB;

    /**
     * 각 테스트 메서드 실행 전 초기화 작업을 수행합니다.
     * setA와 setB를 XHashSet 인스턴스로 초기화합니다.
     */
    @BeforeEach
    void setUp() {
        setA = new XHashSet<>();
        setB = new XHashSet<>();
    }

    /**
     * 요소 추가 및 조회 기능을 테스트합니다.
     * 요소의 추가 성공 여부와 존재 여부를 확인합니다.
     */
    @Test
    void testAddAndContains() {
        assertTrue(setA.add(1));
        assertTrue(setA.add(2));
        assertFalse(setA.add(2)); // 중복 추가 실패

        assertTrue(setA.contains(1));
        assertTrue(setA.contains(2));
        assertFalse(setA.contains(3));
    }

    /**
     * 요소 삭제 기능을 테스트합니다.
     * 요소의 삭제 성공 여부와 삭제 후 존재 여부를 확인합니다.
     */
    @Test
    void testRemove() {
        setA.add(10);
        setA.add(20);
        assertTrue(setA.remove(10));
        assertFalse(setA.contains(10));

        assertFalse(setA.remove(30)); // 존재하지 않는 원소 제거 시 실패
    }

    /**
     * 집합의 크기 및 공집합 여부를 테스트합니다.
     * 집합이 비어 있는지 여부와 요소 추가 후의 집합 크기를 확인합니다.
     */
    @Test
    void testSizeAndIsEmpty() {
        assertTrue(setA.isEmpty());
        setA.add(5);
        assertFalse(setA.isEmpty());
        assertEquals(1, setA.size());
        setA.add(10);
        assertEquals(2, setA.size());
    }

    /**
     * 집합 연산: 합집합을 테스트합니다.
     * 두 집합의 합집합을 계산하고 결과 집합의 크기 및 요소 존재 여부를 확인합니다.
     */
    @Test
    void testUnion() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);

        XSet<Integer> result = setA.union(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(3, result.size());
    }

    /**
     * 집합 연산: 교집합을 테스트합니다.
     * 두 집합의 교집합을 계산하고 결과 집합의 크기 및 요소 존재 여부를 확인합니다.
     */
    @Test
    void testIntersection() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);

        XSet<Integer> result = setA.intersection(setB);
        System.out.println(result);
        assertTrue(result.contains(2));
        assertFalse(result.contains(1));
        assertFalse(result.contains(3));
        assertEquals(1, result.size());
    }

    /**
     * 집합 연산: 차집합을 테스트합니다.
     * 두 집합의 차집합을 계산하고 결과 집합의 크기 및 요소 존재 여부를 확인합니다.
     */
    @Test
    void testDifference() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);

        XSet<Integer> result = setA.difference(setB);
        System.out.println(result);
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertFalse(result.contains(2));
        assertEquals(2, result.size());
    }

    /**
     * 집합 연산: 대칭 차집합을 테스트합니다.
     * 두 집합의 대칭 차집합을 계산하고 결과 집합의 크기 및 요소 존재 여부를 확인합니다.
     */
    @Test
    void testSymmetricDifference() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);

        XSet<Integer> result = setA.symmetricDifference(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertFalse(result.contains(2));
        assertEquals(2, result.size());
    }

    /**
     * 부분집합 여부를 테스트합니다.
     * 한 집합이 다른 집합의 부분집합인지 여부를 확인합니다.
     */
    @Test
    void testIsSubsetOf() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        setB.add(3);

        assertTrue(setA.isSubsetOf(setB));
        assertFalse(setB.isSubsetOf(setA));
    }

    /**
     * 상위집합 여부를 테스트합니다.
     * 한 집합이 다른 집합의 상위집합인지 여부를 확인합니다.
     */
    @Test
    void testIsSupersetOf() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(1);
        setB.add(2);

        assertTrue(setA.isSupersetOf(setB));
        assertFalse(setB.isSupersetOf(setA));
    }

    /**
     * 원소 복사 기능을 테스트합니다.
     * 집합의 복사본을 생성하고 원소 존재 여부 및 크기를 확인합니다.
     */
    @Test
    void testCopy() {
        setA.add(100);
        setA.add(200);

        XSet<Integer> copy = setA.copy();
        assertEquals(setA.size(), copy.size());
        assertTrue(copy.contains(100));
        assertTrue(copy.contains(200));
        assertNotSame(setA, copy);
    }

    /**
     * 반복자 기능을 테스트합니다.
     * 집합의 반복자를 사용하여 모든 요소를 확인합니다.
     */
    @Test
    void testIterator() {
        setA.add(5);
        setA.add(10);
        setA.add(15);

        Iterator<Integer> iterator = setA.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            assertTrue(setA.contains(iterator.next()));
            count++;
        }
        assertEquals(3, count);
    }
}
