package map;

import java.util.Set;

/**
 * XMap 인터페이스는 Map의 기본적인 기능을 제공합니다.
 *
 * @param <K> 키의 타입
 * @param <V> 값의 타입
 */
public interface XMap<K, V> {
    /**
     * 지정된 키와 값을 맵에 추가합니다.
     *
     * @param key   추가할 키
     * @param value 추가할 값
     * @return 이전에 해당 키에 매핑된 값, 없으면 null
     */
    V put(K key, V value);

    /**
     * 지정된 키에 매핑된 값을 반환합니다.
     *
     * @param key 찾을 키
     * @return 해당 키에 매핑된 값, 없으면 null
     */
    V get(K key);

    /**
     * 이 맵에 지정된 키가 포함되는지 여부를 반환합니다.
     *
     * @param key 확인할 키
     * @return 해당 키가 포함되면 true, 아니면 false
     */
    boolean containsKey(K key);

    /**
     * 이 맵에 지정된 값이 포함되는지 여부를 반환합니다.
     *
     * @param value 확인할 값
     * @return 해당 값이 포함되면 true, 아니면 false
     */
    boolean containsValue(V value);

    /**
     * 지정된 키에 매핑된 값을 제거합니다.
     *
     * @param key 제거할 키
     * @return 제거된 키에 매핑된 이전 값, 없으면 null
     */
    V remove(K key);

    /**
     * 이 맵을 비워줍니다.
     */
    void clear();

    /**
     * 이 맵의 크기를 반환합니다.
     *
     * @return 맵의 크기
     */
    int size();

    /**
     * 이 맵이 비어 있는지 여부를 반환합니다.
     *
     * @return 비어 있으면 true, 아니면 false
     */
    boolean isEmpty();

    /**
     * 이 맵의 모든 키를 포함하는 Set을 반환합니다.
     *
     * @return 모든 키를 포함하는 Set
     */
    Set<K> keySet();

    /**
     * 이 맵의 모든 값을 포함하는 Set을 반환합니다.
     *
     * @return 모든 값을 포함하는 Set
     */
    Set<V> values();
}
