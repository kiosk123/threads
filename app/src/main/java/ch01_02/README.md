# 메인스레드와 작업스레드가 동시에 실행
```java
package ch01_02;

import java.awt.*;

public class ConcurrentActionMainAndWorkerThread {
    public static void main(String[] args) {
        /**
         * 메인 스레드와 작업 스레드 동시 실행하므로 비프음과 띵 글자 동시 출력
         */
        Thread workerThread = new Thread( () -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; i++) {
                toolkit.beep();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        workerThread.start();
        
        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
```