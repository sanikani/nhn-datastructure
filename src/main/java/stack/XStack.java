package stack;

/**
 * 스택 인터페이스를 정의합니다.
 *
 * @param <T> 스택에 저장할 요소의 타입
 */
public interface XStack<T> extends Iterable<T> {
    /**
     * 스택에 요소를 추가합니다.
     *
     * @param element 스택에 추가할 요소
     */
    void push(T element);

    /**
     * 스택에서 요소를 제거하고 반환합니다.
     *
     * @return 스택에서 제거된 요소
     */
    T pop();

    /**
     * 스택의 맨 위 요소를 반환합니다. 요소를 제거하지 않습니다.
     *
     * @return 스택의 맨 위 요소
     */
    T peek();

    /**
     * 스택이 비어 있는지 확인합니다.
     *
     * @return 스택이 비어 있으면 true, 아니면 false
     */
    boolean isEmpty();

    /**
     * 스택의 크기를 반환합니다.
     *
     * @return 스택의 크기
     */
    int size();

    /**
     * 스택을 비워줍니다.
     */
    void clear();
}
