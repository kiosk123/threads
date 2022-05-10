# 현재 스레드 가져오기, 스레드 이름 설정, 스레드 이름 가져오기
```java
package ch02_01;

public class GetThreadName {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread(); // 현재 쓰레드를 가져옴
        System.out.println("프로그램 시작 스레드 이름: " + mainThread.getName()); 

        Thread threadA = new Thread();
        threadA.setName("ThreadA"); // 쓰레드 이름 설정
        System.out.println("작업 스레드 이름 : " + threadA.getName());
        threadA.start();
        
        Thread threadB = new Thread();
        threadB.setName("ThreadB"); // 쓰레드 이름 설정
        System.out.println("작업 스레드 이름 : " + threadB.getName());
        threadB.start();
    }
}

```