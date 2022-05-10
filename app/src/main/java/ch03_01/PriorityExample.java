package ch03_01;

public class PriorityExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new CalcThread("thread" + i);
            if (i != 10) {
                thread.setPriority(Thread.MIN_PRIORITY); // 우선순위 가장 낮게
            } else {
                thread.setPriority(Thread.MAX_PRIORITY); // 우선 순위 가장 높게
            }

            thread.start();
        }
    }
}
