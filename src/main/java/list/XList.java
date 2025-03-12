package list;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * XList 인터페이스는 리스트의 기본 동작을 정의합니다.
 *
 * @param 리스트에 저장되는 요소의 타입
 */
public interface XList<T> {
    /**
     * 리스트의 끝에 요소를 추가합니다.
     *
     * @param element 추가할 요소
     */
    void add(T element);

    /**
     * 특정 위치에 요소를 삽입합니다.
     *
     * @param index   삽입할 위치
     * @param element 삽입할 요소
     */
    void add(int index, T element);

    /**
     * 특정 위치의 요소를 제거하고 반환합니다.
     *
     * @param index 제거할 요소의 위치
     * @return 제거된 요소
     */
    T remove(int index);

    /**
     * 특정 요소를 제거합니다.
     *
     * @param element 제거할 요소
     * @return 제거에 성공하면 true, 그렇지 않으면 false
     */
    boolean remove(T element);

    /**
     * 특정 요소가 리스트에 존재하는지 확인합니다.
     *
     * @param element 확인할 요소
     * @return 요소가 존재하면 true, 그렇지 않으면 false
     */
    boolean contains(T element);

    /**
     * 특정 요소의 인덱스를 반환합니다.
     *
     * @param element 인덱스를 찾을 요소
     * @return 요소의 인덱스
     */
    int indexOf(T element);

    /**
     * 특정 인덱스의 요소를 반환합니다.
     *
     * @param index 반환할 요소의 인덱스
     * @return 요소
     */
    T get(int index);

    /**
     * 특정 인덱스의 요소를 변경합니다.
     *
     * @param index   변경할 요소의 인덱스
     * @param element 변경할 요소
     */
    void set(int index, T element);

    /**
     * 리스트를 정렬합니다.
     *
     * @param comparator 정렬 기준
     */
    void sort(Comparator<T> comparator);

    /**
     * 리스트의 일부분을 반환합니다.
     *
     * @param fromIndex 시작 인덱스
     * @param toIndex   끝 인덱스
     * @return 부분 리스트
     */
    XList<T> subList(int fromIndex, int toIndex);

    /**
     * 다른 리스트의 모든 요소를 추가합니다.
     *
     * @param otherList 추가할 리스트
     */
    void addAll(XList<T> otherList);

    /**
     * 리스트의 모든 요소를 순회합니다.
     */
    void forEach(Consumer<T> action);

    /**
     * 리스트의 크기를 반환합니다.
     *
     * @return 리스트의 크기
     */
    int size();

    /**
     * 리스트가 비어있는지 확인합니다.
     *
     * @return 리스트가 비어있으면 true, 그렇지 않으면 false
     */
    boolean isEmpty();

    /**
     * 리스트의 모든 요소를 제거합니다.
     */
    void clear();

    /**
     * 리스트의 복사본을 반환합니다.
     *
     * @return 리스트의 복사본
     */
    XList<T> copy();
}

