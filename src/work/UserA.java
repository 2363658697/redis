package work;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class UserA {

	public static Jedis jedis = new Jedis();
	public final static String RADIO_A = "radio_a";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		new Thread_A().start();

		while (true) {
			System.out.println("请输入发布消息：");
			String message = sc.nextLine();
			jedis.publish(RADIO_A, message);
		}
	}
}

class Thread_A extends Thread {
	public static Jedis jedis = new Jedis();

	@Override
	public void run() {
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("收到来自" + UserB.RADIO_B + "消息：" + message);
			}
		}, UserB.RADIO_B);
	}
}
