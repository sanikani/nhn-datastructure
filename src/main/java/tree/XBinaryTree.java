package tree;

import java.util.List;

/**
 * 이진 트리 인터페이스입니다.
 *
 * @param <T> 이진 트리 노드가 저장할 수 있는 타입. Comparable 인터페이스를 구현해야 합니다.
 */
public interface XBinaryTree<T extends Comparable<T>> {
    /**
     * 이진 트리에 값을 삽입합니다.
     *
     * @param value 삽입할 값
     */
    void insert(T value);

    /**
     * 이진 트리에서 값을 검색합니다.
     *
     * @param value 검색할 값
     * @return 값이 존재하면 true, 그렇지 않으면 false
     */
    boolean search(T value);

    /**
     * 이진 트리에서 값을 삭제합니다.
     *
     * @param value 삭제할 값
     */
    void delete(T value);

    /**
     * 이진 트리의 크기를 반환합니다.
     *
     * @return 이진 트리의 크기
     */
    int size();

    /**
     * 이진 트리의 높이를 반환합니다.
     *
     * @return 이진 트리의 높이
     */
    int height();

    /**
     * 중위 순회를 통해 이진 트리의 모든 값을 반환합니다.
     *
     * @return 중위 순회 결과로 얻은 값 목록
     */
    List<T> inorderTraversal();
}

