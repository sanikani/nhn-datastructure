package queue;

import list.XArrayList;
import list.XLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

/**
 * Unit tests for XQueue implementation.
 */
class XQueueTest {
    private XQueue<Integer> queue;

    @BeforeEach
    void setUp() {
//        queue = new XListQueue<>(new XArrayList<>());
        queue = new XListQueue<>(new XLinkedList<>());
//        queue = new XArrayQueue<>();
    }

    @Test
    @DisplayName("요소 추가 및 조회 테스트")
    void testEnqueueAndPeek() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue);
        assertEquals(10, queue.peek());  // 첫 번째 요소 확인
        assertEquals(3, queue.size());   // 크기 확인
    }

    @Test
    @DisplayName("요소 제거 테스트")
    void testDequeue() {
        queue.enqueue(5);
        queue.enqueue(15);
        queue.enqueue(25);
        assertEquals(5, queue.dequeue());  // FIFO 확인
        assertEquals(15, queue.peek());    // 제거 후 다음 원소 확인
        assertEquals(2, queue.size());     // 크기 감소 확인
    }

    @Test
    @DisplayName("비어있는 큐에서 dequeue() 시 예외 발생")
    void testDequeueFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    @DisplayName("비어있는 큐에서 peek() 시 예외 발생")
    void testPeekFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    @DisplayName("큐 크기 및 비어 있는지 확인")
    void testSizeAndIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("큐 초기화(clear) 테스트")
    void testClear() {
        queue.enqueue(100);
        queue.enqueue(200);
        queue.clear();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("큐 복사(copy) 테스트")
    void testCopy() {
        queue.enqueue(7);
        queue.enqueue(14);
        queue.enqueue(21);

        XQueue<Integer> copyQueue = queue.copy();

        assertEquals(3, copyQueue.size());
        assertEquals(7, copyQueue.peek());
        assertNotSame(queue, copyQueue);  // 복사본이 원본과 다르지만 값은 같아야 함
    }

    @Test
    @DisplayName("반복자(iterator) 테스트")
    void testIterator() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("큐가 가득 찼을 때 용량 확장 테스트")
    void testExpandCapacity() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertEquals(10, queue.size());
        System.out.println(queue);
        queue.enqueue(10);
        System.out.println(queue);
        assertEquals(11, queue.size());
        assertEquals(0, queue.peek());
    }

    @Test
    @DisplayName("null 요소 추가 시 예외 발생 테스트")
    void testEnqueueNullElement() {
        assertThrows(NullPointerException.class, () -> queue.enqueue(null));
    }

    @Test
    @DisplayName("1,000,000개의 요소를 enqueue()하고 dequeue()하여 성능 테스트")
    void testEnqueueAndDequeuePerformance() {
        int numberOfElements = 1_000_000;

        long startEnqueueTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfElements; i++) {
            queue.enqueue(i);
        }
        long endEnqueueTime = System.currentTimeMillis();
        long enqueueDuration = endEnqueueTime - startEnqueueTime;

        long startDequeueTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfElements; i++) {
            queue.dequeue();
        }
        long endDequeueTime = System.currentTimeMillis();
        long dequeueDuration = endDequeueTime - startDequeueTime;

        System.out.println("Enqueue duration: " + enqueueDuration + " ms");
        System.out.println("Dequeue duration: " + dequeueDuration + " ms");

        assertEquals(0, queue.size());
    }
}