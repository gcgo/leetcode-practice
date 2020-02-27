package bytedance;

/**
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *
 * 思路：
 * 1设置三个属性，表示三个方法各自是否执行完，通过循环判断属性状态，来阻塞或放行方法执行
 * 2CountDownLatch
 * 3synchronized+while+wait
 */
public class Easy1114 {
    static class Foo {
        private boolean firstDone;
        private boolean secondDone;
        private boolean thirdDone;

        public Foo() {
            this.firstDone = false;
            this.secondDone = false;
            this.thirdDone = false;
        }

        public boolean isFirstDone() {
            return firstDone;
        }

        public void setFirstDone(boolean firstDone) {
            this.firstDone = firstDone;
        }

        public boolean isSecondDone() {
            return secondDone;
        }

        public void setSecondDone(boolean secondDone) {
            this.secondDone = secondDone;
        }

        public boolean isThirdDone() {
            return thirdDone;
        }

        public void setThirdDone(boolean thirdDone) {
            this.thirdDone = thirdDone;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            this.setFirstDone(true);
        }

        public void second(Runnable printSecond) throws InterruptedException {
			while (true){
				if (this.isFirstDone())break;
			}
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
			this.setSecondDone(true);
        }

        public void third(Runnable printThird) throws InterruptedException {
			while (true){
				if (this.isSecondDone())break;
			}
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
			this.setThirdDone(true);
        }
    }

    public static void main(String[] args) {//跑多线程不要用Test类，就用main函数
        Foo foo = new Foo();

        Thread t1 = new Thread(() -> {
            try {
                foo.first(() -> System.out.println("one"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo.second(() -> System.out.println("two"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo.third(() -> System.out.println("three"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

		t1.start();
		t3.start();
		t2.start();
	}
}
