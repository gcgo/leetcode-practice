package bytedance;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 假设有这么一个类：
 * <p>
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 * public void fizz(printFizz) { ... }          // only output "fizz"
 * public void buzz(printBuzz) { ... }          // only output "buzz"
 * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 * public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <p>
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 * <p>
 * 思路：Semaphore信号量控制，注意，信号量没acquire一次，信号量就减一，无论是不是当前线程再次acquire，和lock不要混淆
 */
public class Medium1195 {
    static class FizzBuzz {
        private int n;
        private Semaphore fizz;
        private Semaphore buzz;
        private Semaphore fizzbuzz;
        private Semaphore number;

        public FizzBuzz(int n) {
            this.n = n;
            fizz = new Semaphore(0);
            buzz = new Semaphore(0);
            fizzbuzz = new Semaphore(0);
            number = new Semaphore(1);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 || i % 5 == 0) continue;
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 5 != 0 || i % 3 == 0) continue;
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 5 != 0 || i % 3 != 0) continue;
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                number.acquire();
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    number.release();
                } else if (i % 3 == 0&&i % 5 != 0) {
                    fizz.release();
                } else if (i % 5 == 0&&i % 3 != 0) {
                    buzz.release();
                } else if (i % 3 == 0 && i % 5 == 0) {
                    fizzbuzz.release();
                }
            }
        }
    }

    public static void main(String[] args) {//跑多线程不要用Test类，就用main函数
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread t1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.print("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.print("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.print("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                fizzBuzz.number(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
