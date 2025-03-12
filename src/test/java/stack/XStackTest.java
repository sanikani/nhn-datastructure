package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * XArrayStack 클래스의 테스트를 수행합니다.
 */
@DisplayName("배열 기반 스택 구현 테스트")
public class XStackTest {
    private XStack<Integer> stack;

    @BeforeEach
    @DisplayName("테스트 전 스택 초기화")
    void setUp() {
//        stack = new XArrayStack<>();
        stack = new XLinkedStack<>();
    }

    @Test
    @DisplayName("스택에 요소 추가 및 크기 확인")
    void testPushAndSize() {
        assertTrue(stack.isEmpty(), "초기 스택은 비어있어야 함");
        assertEquals(0, stack.size(), "초기 스택의 크기는 0이어야 함");

        stack.push(10);
        assertEquals(1, stack.size(), "첫 번째 요소 추가 후 크기는 1이어야 함");
        assertFalse(stack.isEmpty(), "요소 추가 후 스택은 비어있지 않아야 함");

        stack.push(20);
        assertEquals(2, stack.size(), "두 번째 요소 추가 후 크기는 2여야 함");
    }

    @Test
    @DisplayName("스택에서 요소 제거 및 반환 테스트")
    void testPop() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop(), "마지막에 추가된 요소(30)가 먼저 반환되어야 함");
        assertEquals(2, stack.size(), "pop 후 크기가 2로 줄어들어야 함");

        assertEquals(20, stack.pop(), "이전에 추가된 요소(20)가 다음으로 반환되어야 함");
        assertEquals(1, stack.size(), "pop 후 크기가 1로 줄어들어야 함");

        assertEquals(10, stack.pop(), "처음 추가된 요소(10)가 마지막으로 반환되어야 함");
        assertEquals(0, stack.size(), "모든 요소 제거 후 크기는 0이어야 함");
        assertTrue(stack.isEmpty(), "모든 요소 제거 후 스택은 비어있어야 함");
    }

    @Test
    @DisplayName("빈 스택에서 pop 호출 시 예외 발생 테스트")
    void testPopOnEmptyStack() {
        assertTrue(stack.isEmpty(), "테스트 전 스택이 비어있는지 확인");

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> stack.pop(),
                "빈 스택에서 pop 호출 시 NoSuchElementException이 발생해야 함"
        );
    }

    @Test
    @DisplayName("스택의 맨 위 요소 확인 테스트 (peek)")
    void testPeek() {
        stack.push(10);
        assertEquals(10, stack.peek(), "하나의 요소만 있을 때 해당 요소를 반환해야 함");
        assertEquals(1, stack.size(), "peek 후에도 스택 크기는 변하지 않아야 함");

        stack.push(20);
        assertEquals(20, stack.peek(), "새로운 요소 추가 후 맨 위 요소를 반환해야 함");
        assertEquals(2, stack.size(), "peek 후에도 스택 크기는 변하지 않아야 함");

        // peek 후 pop 해서 같은 값이 나오는지 확인
        Integer peekedValue = stack.peek();
        Integer poppedValue = stack.pop();
        assertEquals(peekedValue, poppedValue, "peek와 pop의 결과는 같아야 함");
    }

    @Test
    @DisplayName("빈 스택에서 peek 호출 시 예외 발생 테스트")
    void testPeekOnEmptyStack() {
        assertTrue(stack.isEmpty(), "테스트 전 스택이 비어있는지 확인");

        assertThrows(
                NoSuchElementException.class,
                () -> stack.peek(),
                "빈 스택에서 peek 호출 시 NoSuchElementException이 발생해야 함"
        );
    }

    @Test
    @DisplayName("스택 비어있음 여부 확인 테스트")
    void testIsEmpty() {
        assertTrue(stack.isEmpty(), "초기 스택은 비어있어야 함");

        stack.push(10);
        assertFalse(stack.isEmpty(), "요소 추가 후 스택은 비어있지 않아야 함");

        stack.pop();
        assertTrue(stack.isEmpty(), "모든 요소 제거 후 스택은 비어있어야 함");
    }

    @Test
    @DisplayName("스택 모든 요소 제거 테스트 (clear)")
    void testClear() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        assertEquals(3, stack.size(), "초기에 스택에 3개의 요소가 있어야 함");

        stack.clear();
        assertTrue(stack.isEmpty(), "clear 후 스택은 비어있어야 함");
        assertEquals(0, stack.size(), "clear 후 스택 크기는 0이어야 함");

        // clear 후 새 요소 추가가 정상 작동하는지 확인
        stack.push(40);
        assertEquals(1, stack.size(), "clear 후 새 요소 추가가 정상 작동해야 함");
        assertEquals(40, stack.peek(), "추가된 요소가 정확히 저장되어야 함");
    }

    @Test
    @DisplayName("스택에 null 값 입력 시 예외 발생 테스트")
    void testNullValidation() {
        assertThrows(
                NullPointerException.class,
                () -> stack.push(null),
                "null 값 push 시 NullPointerException이 발생해야 함"
        );
    }

    @Test
    @DisplayName("스택 용량 자동 확장 테스트")
    void testAutoExpansion() {
        // 기본 용량을 초과하는 요소 추가 테스트
        XStack<Integer> smallStack = new XArrayStack<>(2);

        smallStack.push(1);
        smallStack.push(2);
        assertEquals(2, smallStack.size(), "초기 용량까지 요소가 추가되어야 함");
        // 용량을 초과하는 요소 추가
        smallStack.push(3);
        assertEquals(3, smallStack.size(), "용량 자동 확장 후에도 요소가 올바르게 추가되어야 함");
        // 요소들이 올바른 순서로 반환되는지 확인
        assertEquals(3, smallStack.pop(), "LIFO 순서로 3이 먼저 반환되어야 함");
        assertEquals(2, smallStack.pop(), "LIFO 순서로 2가 다음에 반환되어야 함");
        assertEquals(1, smallStack.pop(), "LIFO 순서로 1이 마지막에 반환되어야 함");
    }
}