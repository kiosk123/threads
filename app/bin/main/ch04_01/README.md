# 4-1. 동기화 메소드 및 동기화 블록  
스레드가 사용중인 객체를 다른 스레드가 변경할 수 없도록 하려면 스레드 작업이 끝날 때까지 객체에  
잠금을 걸어서 다른 스레드가 사용할 수 없도록 해야한다. 자바는 이를 위해 동기화 메소드와 동기화 블록을 제공한다.  
스레드가 객체 내부의 동기화 메소드 또는 블록에 들어가면 즉시 객체에 잠금을 걸어 다른 스레드가 임계 영역 코드를 실행하지  
못하도록 한다.  
  
동기화 메소드를 만드는 방법은 다음과 같이 메소드 선언에 `synchronized` 키워드를 붙이면 된다.
```java
public synchronized void method() {
    //임계 영역 - 단하나의 스레드만 실행
}
```

```java
public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public synchronized void setMemory(int memory) {
        this.memory = memory;
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
```

동기화 메소드는 메소드 전체 내용이 임계 영역이므로 스레드가 동기화 메소드를 실행하는 즉시 객체는 잠금이 일어나고,  
스레드가 동기화 메소드를 실행 종료하면 잠금이 풀린다. 메소드 전체 내용이 아니라, 일부 내용만 임계 영역으로 만들고 싶다면  
다음과 가같이 동기화 블록을 만든다.

```java
public void method() {
    //여러 스레드가 실행가능한 영역

    synchronized(공유객체) {
        // 임계 영역 - 단 하나의 스레드만 실행
    }
    // 여러 스레드가 실행 가능 영역
}
```

```java
public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public synchronized void setMemory(int memory) {

        synchronized(this) {
            this.memory = memory;
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ": " + this.memory);
        }
        
    }
}
```