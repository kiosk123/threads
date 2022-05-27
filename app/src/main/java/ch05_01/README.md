# 5-1. 쓰레드 상태  

| 상태 | 열거 상수 | 설명 |
|---|---|---|
| 객체 생성 | NEW | 스레드 객체가 생성, 아직 START() 메소드가 호출되지 않은 상태 |
| 실행 대기 | RUNNABLE | 실행 상태로 언제든지 갈 수 있는 상태 |
| 일시정지 | WATING | 다른 스레드가 통지할 때까지 기다리는 상태 |
| 일시정지 | TIMED_WAITING | 주어진 시간 동안 기다리는 상태 |
| 일시정지  | BLOCKED | 사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태 |
| 종료  | TERMINATED | 실행을 마친 상태 |

다음은 스레드의 상태를 출력하는 클래스다. 생성자 매개값으로 받은 타겟스레드의 상태를 0.5초주기로 출력한다.
```java
public class StatePrintThread extends Thread {
    private Thread targetThread;

    public StatePrintThread(Thread targetThread) {
        this.targetThread = targetThread;
    }

    @Override
    public void run() {
        while (true) {
            Thread.State state = targetThread.getState(); // 스레드 상태 얻기
            System.out.println("타겟 스레드 상태 : " + state);

            if (state == Thread.State.NEW) { // 객체 생성 상태 일경우
                targetThread.start(); // 실행 대기 상태로 만듬
            }

            if (state == Thread.State.TERMINATED) { // 종료 상태일 경우 while 문 종료
                break;
            }

            try {
                //0.5초간 일시 정지
                Thread.sleep(500);
            } catch(Exception e) {}
        }
    }
}

```


다음은 타겟스레드다. 10억번 반복문으로 RUNNABLE 상태를 유지하고 SLEEP() 메소드를 호출해서 1.5초간 TIMED_WAITING 상태를 유지한다.  
그리고 다시 10억번 반복문으로 RUNNABLE 상태를 유지한다.
```java
public class TargetThread extends Thread {

    @Override
    public void run() {
        for (long i = 0; i < 1000000000; i++);

        try {
            Thread.sleep(1500); // 1.5초간 정지
        } catch (InterruptedException e) {}

        for (long i = 0; i < 1000000000; i++);
    }

}
```


다음은 StatePrinThread를 생성해서 매개값으로 전달 받은 TargetThread의 상태를 출력하도록 작성된 실행 클래스이다.
```java
public class ThreadStateExample {
    public static void main(String[] args) {
        StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
        statePrintThread.start();
    }
}
```