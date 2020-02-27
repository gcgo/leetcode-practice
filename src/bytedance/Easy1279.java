package bytedance;

import java.util.concurrent.Semaphore;

/**
 * 这是两条路的交叉路口。第一条路是 A 路，车辆可沿 1 号方向由北向南行驶，也可沿 2 号方向由南向北行驶。
 * 第二条路是 B 路，车辆可沿 3 号方向由西向东行驶，也可沿 4 号方向由东向西行驶。
 * <p>
 * 每条路在路口前都有一个红绿灯。红绿灯可以亮起红灯或绿灯。
 * <p>
 * 绿灯表示两个方向的车辆都可通过路口。
 * 红灯表示两个方向的车辆都不可以通过路口，必须等待绿灯亮起。
 * 两条路上的红绿灯不可以同时为绿灯。
 * 这意味着，当 A 路上的绿灯亮起时，B 路上的红灯会亮起；当 B 路上的绿灯亮起时，A 路上的红灯会亮起.
 * <p>
 * 开始时，A 路上的绿灯亮起，B 路上的红灯亮起。
 * 当一条路上的绿灯亮起时，所有车辆都可以从任意两个方向通过路口，直到另一条路上的绿灯亮起。不同路上的车辆不可以同时通过路口。
 * <p>
 * 给这个路口设计一个没有死锁的红绿灯控制系统。
 * <p>
 * 实现函数 void carArrived(carId, roadId, direction, turnGreen, crossCar) :
 * <p>
 * carId 为到达车辆的编号。
 * roadId 为车辆所在道路的编号。
 * direction 为车辆的行进方向。
 * turnGreen 是一个函数，调用此函数会使当前道路上的绿灯亮起。
 * crossCar 是一个函数，调用此函数会允许车辆通过路口。
 * 当你的答案避免了车辆在路口出现死锁，此答案会被认定为正确的。当路口已经亮起绿灯时仍打开绿灯，此答案会被认定为错误的。
 * <p>
 * 示例 1:
 * 输入: cars = [1,3,5,2,4], directions = [2,1,2,4,3], arrivalTimes = [10,20,30,40,50]
 * 输出: [
 * "Car 1 Has Passed Road A In Direction 2",    // A 路上的红绿灯为绿色，1 号车可通过路口。
 * "Car 3 Has Passed Road A In Direction 1",    // 红绿灯仍为绿色，3 号车通过路口。
 * "Car 5 Has Passed Road A In Direction 2",    // 红绿灯仍为绿色，5 号车通过路口。
 * "Traffic Light On Road B Is Green",          // 2 号车在 B 路请求绿灯。
 * "Car 2 Has Passed Road B In Direction 4",    // B 路上的绿灯现已亮起，2 号车通过路口。
 * "Car 4 Has Passed Road B In Direction 3"     // 红绿灯仍为绿色，4 号车通过路口。
 * ]
 * <p>
 * 示例 2:
 * 输入: cars = [1,2,3,4,5], directions = [2,4,3,3,1], arrivalTimes = [10,20,30,40,40]
 * 输出: [
 * "Car 1 Has Passed Road A In Direction 2",    // A 路上的红绿灯为绿色，1 号车可通过路口。
 * "Traffic Light On Road B Is Green",          // 2 号车在 B 路请求绿灯。
 * "Car 2 Has Passed Road B In Direction 4",    // B 路上的绿灯现已亮起，2 号车通过路口。
 * "Car 3 Has Passed Road B In Direction 3",    // B 路上的绿灯现已亮起，3 号车通过路口。
 * "Traffic Light On Road A Is Green",          // 5 号车在 A 路请求绿灯。
 * "Car 5 Has Passed Road A In Direction 1",    // A 路上的绿灯现已亮起，5 号车通过路口。
 * "Traffic Light On Road B Is Green",          // 4 号车在 B 路请求绿灯。4 号车在路口等灯，直到 5 号车通过路口，B 路的绿灯亮起。
 * "Car 4 Has Passed Road B In Direction 3"     // B 路上的绿灯现已亮起，4 号车通过路口。
 * ]
 * 解释: 这是一个无死锁的方案。注意，在 A 路上的绿灯亮起、5 号车通过前让 4 号车通过，也是一个正确且可被接受的方案。
 *  
 * <p>
 * 提示：
 * 1 <= cars.length <= 20
 * cars.length = directions.length
 * cars.length = arrivalTimes.length
 * cars 中的所有值都是唯一的。
 * 1 <= directions[i] <= 4
 * arrivalTimes 是非递减的。
 * <p>
 * 思路：共享资源是绿灯，两个路口只有一个能获得绿灯
 * 理解这个arriveTimes,他是表示线程睡眠时间，后面相对于前面的线程多睡多少，看差值
 * 这里一辆车代表一个线程，每辆车过来以后都想要获取绿灯。显然绿灯就是共享资源
 * 一个方向一个绿灯，两个绿灯互斥
 * 这个道路1对应12方向，道路2对应34方向
 * 题目说，开始时，1 路上的绿灯亮起，2 路上的红灯亮起。
 */
public class Easy1279 {
    static class TrafficLight {
        private Semaphore greenLight;//红绿灯遥控器
        private boolean road1CanGo;//表示道路1是绿灯
        private boolean road2CanGo;//表示道路2是绿灯

        public TrafficLight() {
            this.greenLight = new Semaphore(1, true);
            this.road1CanGo = true;
            this.road2CanGo = false;
        }

        public void carArrived(
                int carId,           // ID of the car
                int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
                int direction,       // Direction of the car
                Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
                Runnable crossCar    // Use crossCar.run() to make car cross the intersection
        ) {
            try {
                greenLight.acquire();//申请获取遥控器
                //如果当前车道已经是绿灯了，直接通过
                if ((roadId == 1 && road1CanGo) || (roadId == 2 && road2CanGo)) crossCar.run();
                else if (roadId == 1 && !road1CanGo) {//否则，如果道路1不是绿灯，用遥控器变成绿灯
                    turnGreen.run();
                    road1CanGo = true;
                    road2CanGo = false;
                    crossCar.run();
                } else if (roadId == 2 && !road2CanGo) {//如果道路2不是绿灯，用遥控器变成绿灯
                    turnGreen.run();
                    road2CanGo = true;
                    road1CanGo = false;
                    crossCar.run();
                }
                greenLight.release();//最后把遥控器归还
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {//跑多线程不要用Test类，就用main函数
        TrafficLight trafficLight = new TrafficLight();

        Thread t1 = new Thread(() -> trafficLight.carArrived(1, 1, 2,
                () -> System.out.println(1 + "号路上" + "方向" + 2 + "绿灯"),
                () -> System.out.println(1 + "号车" + "在" + 1 + "号路上" + "方向" + 2 + "通过")));

        Thread t2 = new Thread(() -> trafficLight.carArrived(2, 2, 4,
                () -> System.out.println(2 + "号路上" + "方向" + 4 + "绿灯"),
                () -> System.out.println(2 + "号车" + "在" + 2 + "号路上" + "方向" + 4 + "通过")));

        Thread t3 = new Thread(() -> trafficLight.carArrived(3, 2, 3,
                () -> System.out.println(3 + "号路上" + "方向" + 3 + "绿灯"),
                () -> System.out.println(3 + "号车" + "在" + 2 + "号路上" + "方向" + 3 + "通过")));

        Thread t4 = new Thread(() -> trafficLight.carArrived(4, 2, 3,
                () -> System.out.println(4 + "号路上" + "方向" + 3 + "绿灯"),
                () -> System.out.println(4 + "号车" + "在" + 2 + "号路上" + "方向" + 3 + "通过")));

        Thread t5 = new Thread(() -> trafficLight.carArrived(5, 1, 1,
                () -> System.out.println(5 + "号路上" + "方向" + 1 + "绿灯"),
                () -> System.out.println(5 + "号车" + "在" + 1 + "号路上" + "方向" + 1 + "通过")));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
