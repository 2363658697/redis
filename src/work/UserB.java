package work;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class UserB {
	public static Jedis jedis = new Jedis();
	public final static String RADIO_B = "radio_b";

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		new Thread_B().start();

		while (true) {
			System.out.println("请输入发布消息：");
			String message = sc.nextLine();
			jedis.publish(RADIO_B, message);
		}
	}

}

class Thread_B extends Thread {

	public static Jedis jedis = new Jedis();

	@Override
	public void run() {
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("收到来自" + UserA.RADIO_A + "消息：" + message);

			}
		}, UserA.RADIO_A);
	}
}