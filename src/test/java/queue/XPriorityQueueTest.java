package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XPriorityQueueTest {

    private XQueue<Integer> intPriorityQueue;
    private XQueue<String> stringPriorityQueue;

    @BeforeEach
    void setUp() {
        // 기본 오름차순 정렬 (작은 값이 우선순위가 높음)
        intPriorityQueue = new XPriorityQueue<>(Integer::compareTo);

        // 문자열 길이 내림차순 정렬 (긴 문자열이 우선순위가 높음)
        stringPriorityQueue = new XPriorityQueue<>((a, b) -> b.length() - a.length());
    }

    @DisplayName("정수 우선순위 큐 기본 동작 테스트")
    @Test
    void testIntegerPriorityQueueBasicOperations() {
        assertTrue(intPriorityQueue.isEmpty());

        intPriorityQueue.enqueue(30);
        intPriorityQueue.enqueue(10);
        intPriorityQueue.enqueue(20);
        System.out.println(intPriorityQueue);
        assertEquals(3, intPriorityQueue.size());
        assertFalse(intPriorityQueue.isEmpty());
        assertEquals(10, intPriorityQueue.peek()); // 가장 작은 값이 우선순위가 높음

        assertEquals(10, intPriorityQueue.dequeue());
        assertEquals(2, intPriorityQueue.size());
        assertEquals(20, intPriorityQueue.peek());
        System.out.println(intPriorityQueue);
        assertEquals(20, intPriorityQueue.dequeue());
        assertEquals(30, intPriorityQueue.dequeue());
        assertTrue(intPriorityQueue.isEmpty());
        System.out.println(intPriorityQueue);
    }

    @DisplayName("문자열 우선순위 큐 길이 기반 정렬 테스트")
    @Test
    void testStringPriorityQueueWithCustomComparator() {
        stringPriorityQueue.enqueue("apple");     // 길이 5
        stringPriorityQueue.enqueue("banana");    // 길이 6
        stringPriorityQueue.enqueue("kiwi");      // 길이 4
        stringPriorityQueue.enqueue("strawberry"); // 길이 10

        assertEquals(4, stringPriorityQueue.size());
        assertEquals("strawberry", stringPriorityQueue.peek()); // 가장 긴 문자열

        assertEquals("strawberry", stringPriorityQueue.dequeue());
        assertEquals("banana", stringPriorityQueue.dequeue());
        assertEquals("apple", stringPriorityQueue.dequeue());
        assertEquals("kiwi", stringPriorityQueue.dequeue());
        assertTrue(stringPriorityQueue.isEmpty());
    }

    @DisplayName("다량의 데이터 삽입 및 추출 테스트")
    @Test
    void testManyElementsEnqueueDequeue() {
        // 100개의 무작위 정수 추가
        for (int i = 0; i < 100; i++) {
            intPriorityQueue.enqueue((int) (Math.random() * 1000));
        }

        assertEquals(100, intPriorityQueue.size());

        // 모든 요소를 추출하고 정렬 순서 검증
        Integer previous = intPriorityQueue.dequeue();
        while (!intPriorityQueue.isEmpty()) {
            Integer current = intPriorityQueue.dequeue();
            assertTrue(previous.compareTo(current) <= 0);
            previous = current;
        }
    }

    @DisplayName("우선순위 큐 복사 테스트")
    @Test
    void testCopy() {
        intPriorityQueue.enqueue(30);
        intPriorityQueue.enqueue(10);
        intPriorityQueue.enqueue(20);

        XQueue<Integer> copy = intPriorityQueue.copy();

        assertEquals(3, copy.size());
        assertEquals(intPriorityQueue.size(), copy.size());
        assertEquals(intPriorityQueue.peek(), copy.peek());

        // 원본 큐를 변경해도 복사본에 영향이 없어야 함
        intPriorityQueue.dequeue();
        assertEquals(2, intPriorityQueue.size());
        assertEquals(3, copy.size());
    }

    @DisplayName("빈 큐 연산 테스트")
    @Test
    void testEmptyQueueOperations() {
        assertTrue(intPriorityQueue.isEmpty());
        assertEquals(0, intPriorityQueue.size());

        // 빈 큐에서 peek과 dequeue는 예외를 발생시켜야 함
        assertThrows(IllegalStateException.class, () -> intPriorityQueue.peek());
        assertThrows(IllegalStateException.class, () -> intPriorityQueue.dequeue());
    }

    @DisplayName("null 요소 삽입 테스트")
    @Test
    void testNullElement() {
        assertThrows(NullPointerException.class, () -> intPriorityQueue.enqueue(null));
    }

    @DisplayName("clear 메서드 테스트")
    @Test
    void testClear() {
        intPriorityQueue.enqueue(30);
        intPriorityQueue.enqueue(10);
        intPriorityQueue.enqueue(20);

        assertFalse(intPriorityQueue.isEmpty());

        intPriorityQueue.clear();

        assertTrue(intPriorityQueue.isEmpty());
        assertEquals(0, intPriorityQueue.size());
        assertThrows(IllegalStateException.class, () -> intPriorityQueue.peek());
    }

    @DisplayName("반복자 테스트")
    @Test
    void testIterator() {
        intPriorityQueue.enqueue(30);
        intPriorityQueue.enqueue(10);
        intPriorityQueue.enqueue(20);

        int count = 0;
        for (Integer value : intPriorityQueue) {
            assertNotNull(value);
            count++;
        }

        assertEquals(3, count);
    }

    @DisplayName("우선순위 큐 요소 순서 테스트")
    @Test
    void testPriorityOrder() {
        // 순서를 섞어서 삽입
        intPriorityQueue.enqueue(50);
        intPriorityQueue.enqueue(10);
        intPriorityQueue.enqueue(30);
        intPriorityQueue.enqueue(20);
        intPriorityQueue.enqueue(40);

        // 정렬된 순서로 추출되어야 함
        assertEquals(10, intPriorityQueue.dequeue());
        assertEquals(20, intPriorityQueue.dequeue());
        assertEquals(30, intPriorityQueue.dequeue());
        assertEquals(40, intPriorityQueue.dequeue());
        assertEquals(50, intPriorityQueue.dequeue());
    }
}