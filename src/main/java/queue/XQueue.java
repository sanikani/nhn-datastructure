package queue;

import java.util.Iterator;

/**
 * 큐 인터페이스
 *
 * @param <T> 큐에 저장되는 요소의 타입
 */
public interface XQueue<T> extends Iterable<T> {

    /**
     * 큐에 요소를 추가한다.
     *
     * @param element 큐에 추가할 요소
     * @return 요소가 성공적으로 추가되면 true, 그렇지 않으면 false
     */
    boolean enqueue(T element);

    /**
     * 큐에서 요소를 제거한다.
     *
     * @return 제거된 요소
     */
    T dequeue();

    /**
     * 큐의 첫 번째 요소를 조회한다.
     *
     * @return 큐의 첫 번째 요소
     */
    T peek();

    /**
     * 큐의 크기를 반환한다.
     *
     * @return 큐의 크기
     */
    int size();

    /**
     * 큐가 비어 있는지 확인한다.
     *
     * @return 큐가 비어 있으면 true, 그렇지 않으면 false
     */
    boolean isEmpty();

    /**
     * 큐를 비운다.
     */
    void clear();

    /**
     * 큐를 복사한다.
     *
     * @return 복사된 큐
     */
    XQueue<T> copy();

    /**
     * 큐의 요소를 순회하는 반복자를 반환한다.
     *
     * @return 큐의 요소를 순회하는 반복자
     */
    Iterator<T> iterator();
}
