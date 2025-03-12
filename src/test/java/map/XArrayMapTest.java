package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class XArrayMapTest {
    private XMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new XArrayMap<>();
    }

    @Test
    @DisplayName("요소 추가 및 가져오기 테스트")
    void testPutAndGet() {
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        assertEquals(25, map.get("Alice"));
        assertEquals(30, map.get("Bob"));
        assertEquals(35, map.get("Charlie"));
    }

    @Test
    @DisplayName("키 존재 여부 확인 테스트")
    void testContainsKey() {
        map.put("Alice", 25);
        map.put("Bob", 30);

        assertTrue(map.containsKey("Bob"));
        assertFalse(map.containsKey("David"));
    }

    @Test
    @DisplayName("값 존재 여부 확인 테스트")
    void testContainsValue() {
        map.put("Alice", 25);
        map.put("Bob", 30);

        assertTrue(map.containsValue(30));
        assertFalse(map.containsValue(40));
    }

    @Test
    @DisplayName("요소 삭제 테스트")
    void testRemove() {
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        map.remove("Charlie");

        assertFalse(map.containsKey("Charlie"));
        assertTrue(map.containsKey("Alice"));
        assertTrue(map.containsKey("Bob"));
        assertNull(map.remove("sani"));
    }

    @Test
    @DisplayName("모든 키 출력 테스트")
    void testKeySet() {
        map.put("Alice", 25);
        map.put("Bob", 30);

        assertEquals(new HashSet<>(Arrays.asList("Alice", "Bob")), map.keySet());
    }

    @Test
    @DisplayName("모든 값 출력 테스트")
    void testValues() {
        map.put("Alice", 25);
        map.put("Bob", 30);

        assertTrue(map.values().contains(25));
        assertTrue(map.values().contains(30));
        assertEquals(2, map.values().size());
    }

    @Test
    @DisplayName("전체 삭제 후 확인 테스트")
    void testClearAndIsEmpty() {
        map.put("Alice", 25);
        map.put("Bob", 30);

        assertFalse(map.isEmpty());

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("맵 크기 확인 테스트")
    void testSize() {
        assertEquals(0, map.size());

        map.put("Alice", 25);
        assertEquals(1, map.size());

        map.put("Bob", 30);
        assertEquals(2, map.size());

        map.remove("Alice");
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("맵에 동일한 키 추가 테스트")
    void testPutSameKey() {
        map.put("Alice", 25);
        map.put("Alice", 30);

        assertEquals(1, map.size());
        assertEquals(30, map.get("Alice"));
    }

    @Test
    @DisplayName("맵에 null 키 추가 시도 테스트")
    void testPutNullKey() {
        assertThrows(NullPointerException.class, () -> map.put(null, 25));
    }

    @Test
    @DisplayName("맵에 null 값 추가 시도 테스트")
    void testPutNullValue() {
        assertThrows(NullPointerException.class, () -> map.put("Alice", null));
    }


}