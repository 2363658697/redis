package work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 
 * @author Administrator
 *
 */
public class RedisClient {

	public static Jedis jedis;

	/**
	 * 
	 * redis-cli -h localhost -p 6379
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "127.0.0.1";
		String port = "6379";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-h")) {
				host = args[i + 1];
			}
			if (args[i].equals("-p")) {
				port = args[i + 1];
			}
		}
		jedis = new Jedis(host, Integer.parseInt(port));
		//jedis.asking();
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.print(host + ":" + port + ">");
			String command = s.nextLine();

			command = command.replaceAll(" +", " ").trim();
			//set
			if (command.startsWith("set ")) {
				String[] c = command.split(" ");
				String key = c[1];
				String value = c[2];
				jedis.set(key, value);
			}
			//get
			if (command.startsWith("get ")) {
				String[] c = command.split(" ");
				String key = c[1];
				String value = jedis.get(key);
				System.out.println(value);
			}
			//ping
			if (command.equals("ping")) {
				System.out.println("pong");
			}
			//echo
			if (command.startsWith("echo ")) {
				String[] c = command.split(" ");
				String text=c[1];
				System.out.println(text);
			}
			//quit
			if (command.equals("ping")) {
				System.exit(0);
			}			
			//select
			if (command.startsWith("select ")) {
				String[] c = command.split(" ");
				String text=c[1];
				String str=jedis.select(Integer.valueOf(text));
				System.out.println(str);
			}
			//dbsize
			if (command.equals("dbsize")) {
				long len=jedis.dbSize();
				System.out.println(len);
			}
			//flushdb
			if (command.equals("flushdb")) {
				String str=jedis.flushDB();
				System.out.println(str);
			}
			//flushall
			if (command.equals("flushall")) {
				String str=jedis.flushAll();
				System.out.println(str);
			}
			//append
			if (command.startsWith("append ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String value=c[2];
				Long len=jedis.append(key, value);
				System.out.println(len);
			}
			//incr
			if (command.startsWith("incr ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Long len=jedis.incr(key);
				System.out.println(len);
			}
			//desc
			if (command.startsWith("desc ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Long len=jedis.incr(key);
				System.out.println(len);
			}
			//strlen
			if (command.startsWith("strlen ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Long len=jedis.strlen(key);
				System.out.println(len);
			}
			//hexists
			if (command.startsWith("hexists ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String field=c[2];
				boolean bool=jedis.hexists(key, field);
				System.out.println(bool);
			}
			//hlen
			if (command.startsWith("hlen ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Long len=jedis.hlen(key);
				System.out.println(len);
			}
			//hdel
			if (command.startsWith("hdel ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String[] fields=new String[c.length-2];
				int index=0;
				for(int i=2;i<c.length;i++){
					fields[index++]=c[i];
				}
				Long l=jedis.hdel(key, fields);
				System.out.println(l);
			}
			//hgetall
			if (command.startsWith("hgetall ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Map<String,String> map=jedis.hgetAll(key);
				System.out.println(map);
			}
			//hmset
			if (command.startsWith("hmset ")) {
				String[] c = command.split(" ");
				String key=c[1];				
				Map<String,String> hash=new HashMap<>();
				for(int i=2;i<c.length;i++){
					String ke=c[i];
					String value=c[++i];
					hash.put(ke, value);
				}			
				String str=jedis.hmset(key, hash);
				System.out.println(str);
			}
			//lpush
			if (command.startsWith("lpush ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String field=c[2];
				long l=jedis.lpush(key, field);
				System.out.println(l);
			}
			//rpush
			if (command.startsWith("rpush ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String field=c[2];
				long l=jedis.lpush(key, field);
				System.out.println(l);
			}
			//lrange
			if (command.startsWith("lrange ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String start=c[2];
				String end=c[3];
				List<String> list=jedis.lrange(key, Integer.valueOf(start), Integer.valueOf(end));
				System.out.println(list);
			}
			//lpop
			if (command.startsWith("lpop ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String str=jedis.lpop(key);
				System.out.println(str);
			}
			//rpop
			if (command.startsWith("rpop ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String str=jedis.rpop(key);
				System.out.println(str);
			}
			//sadd
			if (command.startsWith("sadd ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String[] members=new String[c.length-2];
				int index=0;
				for(int i=2;i<c.length;i++){
					members[index++]=c[i];
				}
				Long l=jedis.sadd(key, members);
				System.out.println(l);
			}
			//smembers
			if (command.startsWith("smembers ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Set<String> set=jedis.smembers(key);
				System.out.println(set);
			}
			//scard
			if (command.startsWith("scard ")) {
				String[] c = command.split(" ");
				String key=c[1];
				long l=jedis.scard(key);
				System.out.println(l);
			}
			//srem
			if (command.startsWith("srem ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String[] members=new String[c.length-2];
				int index=0;
				for(int i=2;i<c.length;i++){
					members[index++]=c[i];
				}
				long l=jedis.srem(key, members);
				System.out.println(l);
			}
			//spop
			if (command.startsWith("spop ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String str=jedis.spop(key);
				System.out.println(str);
			}
			//zadd
			if (command.startsWith("zadd ")) {
				String[] c = command.split(" ");
				String key=c[1];
				Map<String,Double> scoreMembers=new HashMap<>();
				for(int i=2;i<c.length;i++){
					String value=c[i];
					String ke=c[++i];
					scoreMembers.put(ke, Double.valueOf(value));
				}
				Long l=jedis.zadd(key, scoreMembers);
				System.out.println(l);
			}
			//zcard
			if (command.startsWith("zcard ")) {
				String[] c = command.split(" ");
				String key=c[1];
				long l=jedis.zcard(key);
				System.out.println(l);
			}
			//zcount
			if (command.startsWith("zcount ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String start=c[2];
				String end=c[3];
				long l=jedis.zcount(key, Integer.valueOf(start), Integer.valueOf(end));
				System.out.println(l);
			}
			//zrange
			if (command.startsWith("zrange ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String start=c[2];
				String end=c[3];
				Set<String> set=jedis.zrange(key, Long.valueOf(start), Long.valueOf(end));
				System.out.println(set);
			}
			//zrevrange
			if (command.startsWith("zrevrange ")) {
				String[] c = command.split(" ");
				String key=c[1];
				String start=c[2];
				String end=c[3];
				Set<String> set=jedis.zrevrange(key, Long.valueOf(start), Long.valueOf(end));
				System.out.println(set);
			}
			//save
			if (command.equals("save")) {
				jedis.save();
			}
			//publish
			if (command.startsWith("publish ")) {
				String[] c = command.split(" ");
				String channel=c[1];
				StringBuilder sb=new StringBuilder();
				for(int i=2;i<c.length;i++){
					sb.append(c[i]);
				}
				jedis.publish(channel, new String(sb));
			}
			//
			if (command.startsWith("subscribe ")) {
				String[] c = command.split(" ");
				String channels=c[1];
				jedis.subscribe(new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						System.out.println(message);
					}
				}, channels);
			}
		}
	}

}
