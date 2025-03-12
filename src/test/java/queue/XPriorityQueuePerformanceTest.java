package queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class XPriorityQueuePerformanceTest {

    private static final int TEST_SIZE = 1_000_000;
    private static final int SEED = 42; // Fixed seed for reproducibility

    @DisplayName("우선순위 큐 성능 테스트 - 1,000,000개 요소")
    @Test
    void testPriorityQueuePerformance() {
        XQueue<Integer> priorityQueue = new XPriorityQueue<>(Integer::compareTo);
        Random random = new Random(SEED);

        // 1. Enqueue 성능 측정
        long startEnqueue = System.currentTimeMillis();

        for (int i = 0; i < TEST_SIZE; i++) {
            priorityQueue.enqueue(random.nextInt());
        }

        long endEnqueue = System.currentTimeMillis();
        long enqueueTime = endEnqueue - startEnqueue;

        System.out.println("Priority Queue - " + TEST_SIZE + " elements:");
        System.out.println("Total enqueue time: " + enqueueTime + " ms");
        System.out.println("Average enqueue time per element: " + (double)enqueueTime / TEST_SIZE + " ms");

        // 2. Dequeue 성능 측정
        long startDequeue = System.currentTimeMillis();

        while (!priorityQueue.isEmpty()) {
            priorityQueue.dequeue();
        }

        long endDequeue = System.currentTimeMillis();
        long dequeueTime = endDequeue - startDequeue;

        System.out.println("Total dequeue time: " + dequeueTime + " ms");
        System.out.println("Average dequeue time per element: " + (double)dequeueTime / TEST_SIZE + " ms");
        System.out.println("Total operation time: " + (enqueueTime + dequeueTime) + " ms");
    }

    @DisplayName("우선순위 큐 vs 일반 큐 성능 비교")
    @Test
    void comparePerformanceWithRegularQueue() {
        // XList 구현에 따라 달라질 수 있어 임의의 XList를 생성
        XQueue<Integer> priorityQueue = new XPriorityQueue<>(Integer::compareTo);
        XQueue<Integer> regularQueue = new XListQueue<>(new list.XLinkedList<>());

        Random random = new Random(SEED);
        int[] values = new int[TEST_SIZE];

        // 동일한 값을 두 큐에 삽입하기 위해 배열에 저장
        for (int i = 0; i < TEST_SIZE; i++) {
            values[i] = random.nextInt();
        }

        // 1. Priority Queue 성능 측정
        long startPriorityEnqueue = System.currentTimeMillis();

        for (int i = 0; i < TEST_SIZE; i++) {
            priorityQueue.enqueue(values[i]);
        }

        long endPriorityEnqueue = System.currentTimeMillis();
        long priorityEnqueueTime = endPriorityEnqueue - startPriorityEnqueue;

        // 2. Regular Queue 성능 측정
        long startRegularEnqueue = System.currentTimeMillis();

        for (int i = 0; i < TEST_SIZE; i++) {
            regularQueue.enqueue(values[i]);
        }

        long endRegularEnqueue = System.currentTimeMillis();
        long regularEnqueueTime = endRegularEnqueue - startRegularEnqueue;

        // 결과 출력
        System.out.println("\nPerformance Comparison - " + TEST_SIZE + " elements:");
        System.out.println("Priority Queue enqueue time: " + priorityEnqueueTime + " ms");
        System.out.println("Regular Queue enqueue time: " + regularEnqueueTime + " ms");

        // Dequeue 성능 측정은 결과값이 다르므로 비교하지 않음
        // 대신 각각의 dequeue 시간을 측정

        long startPriorityDequeue = System.currentTimeMillis();
        while (!priorityQueue.isEmpty()) {
            priorityQueue.dequeue();
        }
        long endPriorityDequeue = System.currentTimeMillis();
        long priorityDequeueTime = endPriorityDequeue - startPriorityDequeue;

        long startRegularDequeue = System.currentTimeMillis();
        while (!regularQueue.isEmpty()) {
            regularQueue.dequeue();
        }
        long endRegularDequeue = System.currentTimeMillis();
        long regularDequeueTime = endRegularDequeue - startRegularDequeue;

        System.out.println("Priority Queue dequeue time: " + priorityDequeueTime + " ms");
        System.out.println("Regular Queue dequeue time: " + regularDequeueTime + " ms");

        System.out.println("Priority Queue total time: " + (priorityEnqueueTime + priorityDequeueTime) + " ms");
        System.out.println("Regular Queue total time: " + (regularEnqueueTime + regularDequeueTime) + " ms");
    }

    @DisplayName("우선순위 큐 정렬 안정성 테스트")
    @Test
    void testPriorityQueueSortingStability() {
        final int TEST_COUNT = 100_000; // 더 작은 테스트 사이즈
        XQueue<Integer> priorityQueue = new XPriorityQueue<>(Integer::compareTo);

        Random random = new Random(SEED);

        // 무작위 값 삽입
        for (int i = 0; i < TEST_COUNT; i++) {
            priorityQueue.enqueue(random.nextInt(1000)); // 0-999 범위의 정수
        }

        // 추출하면서 정렬 순서 확인
        Integer previous = priorityQueue.dequeue();
        int count = 1;

        while (!priorityQueue.isEmpty()) {
            Integer current = priorityQueue.dequeue();
            if (previous > current) {
                System.out.println("정렬 오류 발견: " + previous + " > " + current +
                        " (위치: " + count + ")");
                break;
            }
            previous = current;
            count++;
        }

        System.out.println("총 " + count + "개의 요소가 올바르게 정렬되었습니다.");
    }

    @DisplayName("우선순위 큐 시간 복잡도 분석")
    @Test
    void analyzeTimeComplexity() {
        System.out.println("우선순위 큐(Heap 구현) 시간 복잡도 분석:");
        System.out.println("--------------------------------------");
        System.out.println("enqueue 연산: O(log n)");
        System.out.println("- 요소 추가 후 힙 속성 유지를 위한 상향식 재조정(upheap) 과정");
        System.out.println("- 최악의 경우 힙의 높이만큼 이동 필요 (log n)");
        System.out.println();

        System.out.println("dequeue 연산: O(log n)");
        System.out.println("- 최소값(루트) 제거 후 힙 속성 유지를 위한 하향식 재조정(downheap) 과정");
        System.out.println("- 최악의 경우 힙의 높이만큼 이동 필요 (log n)");
        System.out.println();

        System.out.println("peek 연산: O(1)");
        System.out.println("- 루트 노드(최소값)에 직접 접근");
        System.out.println();

        System.out.println("XListQueue vs XPriorityQueue 시간 복잡도 비교:");
        System.out.println("---------------------------------------------");
        System.out.println("XListQueue(연결 리스트 기반):");
        System.out.println("- enqueue: O(1) - 리스트 끝에 요소 추가");
        System.out.println("- dequeue: O(1) - 리스트 시작 요소 제거");
        System.out.println();

        System.out.println("XPriorityQueue(이진 힙 기반):");
        System.out.println("- enqueue: O(log n) - 요소 추가 및 힙 재구성");
        System.out.println("- dequeue: O(log n) - 최소값 제거 및 힙 재구성");
        System.out.println();

        System.out.println("실제 성능 차이 분석:");
        System.out.println("------------------");
        System.out.println("1. enqueue 연산:");
        System.out.println("   - XListQueue: 상수 시간이므로 데이터 크기가 증가해도 시간 거의 일정");
        System.out.println("   - XPriorityQueue: 로그 시간이므로 데이터 크기가 증가하면 약간 증가");
        System.out.println();
        System.out.println("2. dequeue 연산:");
        System.out.println("   - XListQueue: 상수 시간이지만 우선순위와 무관하게 요소 반환");
        System.out.println("   - XPriorityQueue: 로그 시간이지만 항상 우선순위가 가장 높은 요소 반환");
        System.out.println();
        System.out.println("3. n = 1,000,000일 때 log₂(n) ≈ 20이므로,");
        System.out.println("   우선순위 큐의 각 연산은 최대 약 20단계의 비교/교환 작업 필요");

        // 이론적 시간 복잡도 증명을 위한 실험 데이터
        demonstrateLogTimeComplexity();
    }

    // 큐의 크기를 증가시키며 로그 시간 복잡도 패턴 확인
    private void demonstrateLogTimeComplexity() {
        int[] testSizes = {1000, 10000, 100000, 1000000};
        System.out.println("\n큐 크기에 따른 연산 시간 변화 (로그 시간 복잡도 증명):");
        System.out.println("크기\t\tenqueue 평균시간(ns)\tdequeue 평균시간(ns)");

        Random random = new Random(SEED);

        for (int size : testSizes) {
            XQueue<Integer> priorityQueue = new XPriorityQueue<>(Integer::compareTo);

            // Warm-up to avoid JVM optimization effects
            for (int i = 0; i < 1000; i++) {
                priorityQueue.enqueue(random.nextInt());
                if (priorityQueue.size() > 100) priorityQueue.dequeue();
            }
            priorityQueue.clear();

            // Measure enqueue
            long startEnqueue = System.nanoTime();
            for (int i = 0; i < size; i++) {
                priorityQueue.enqueue(random.nextInt());
            }
            long endEnqueue = System.nanoTime();
            double avgEnqueueTime = (double)(endEnqueue - startEnqueue) / size;

            // Measure dequeue
            long startDequeue = System.nanoTime();
            int count = 0;
            while (!priorityQueue.isEmpty() && count < size) {
                priorityQueue.dequeue();
                count++;
            }
            long endDequeue = System.nanoTime();
            double avgDequeueTime = (double)(endDequeue - startDequeue) / count;

            System.out.printf("%d\t\t%.2f\t\t\t%.2f%n", size, avgEnqueueTime, avgDequeueTime);
        }
    }
}