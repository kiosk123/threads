# 쓰레드 우선 순위  
자바의 스레드 스케줄링은 우선순위 방식과 순환 할당 방식을 사용  
우선순위 방식은 우선 순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링 하는 것을 말함  
  
우선 순위 방식에서 우선순위는 1에서 10까지 부여, 숫자가 높을 수록 우선순위가 높음  
우선 순위를 부여하지 않으면 기본적으로 5의 우선순위를 할당  

## 우선순위 변경
```java
thread.setPriority(우선순위);

// Thread 클래스 상수를 통한 우선순위 설정
thread.setPriority(Thread.MAX_PRIORITY);
thread.setPriority(Thread.NORM_PRIORITY);
thread.setPriority(Thread.MIN_PRIORITY);
```

## 예제
작업스레드
```java
package ch03_01;

public class CalcThread extends Thread {
    public CalcThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000000000; i++) {
            System.out.println(getName());
        }
    }
}
```

우선순위를 정해서 스레드 실행
```java
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
```

## 결과
```
thread1
thread5
thread3
thread3
thread3
thread3
thread2
thread2
thread2
thread2
thread2
thread2
thread2
thread2
thread2
thread2
thread10
...
```
