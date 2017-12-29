package type_set;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
/**
 * 演示set类型的命令
 * @author Administrator
 *
 */
public class Sets {
	 //连接redis数据库
	private static Jedis jedis=new Jedis();
	
	
	/**
	 * sadd:添加值
	 * smembers:遍历集合
	 * scard:获取key的成员数量
	 * srem:删除指定成员
	 */
	@Test
	public void test1(){
		jedis.sadd("class365", new String[]{"a","b","c"});
		Set<String> set=jedis.smembers("class365");
		System.out.println(set);
		
		long l=jedis.scard("class365");
		
		jedis.srem("class365", "b");
		jedis.close();
	}
	
	/**
	 * sismember:判断成员是否存在
	 * spop:随机弹出一个值（删除）
	 * srandmember:随机弹出一个值（不删除）
	 * smove:移动一个集合的成员到另一个集合
	 */
	@Test
	public void test2(){
		jedis.sismember("class365", "b");
		
		String str=jedis.spop("class365");
		
		jedis.srandmember("calss365");
		
		jedis.sadd("class2","66");
		
		jedis.smove("class365","class2","a");
		jedis.close();
	}
	
	/**
	 * sdiff:求集合的差集
	 * sdiffstore:求集合的差集并存到新集合中
	 * sinter:求集合的并集
	 * sinterstore:求集合的并集并存到新集合中
	 * sunion:求集合的交集
	 * sunionstore:求集合的交集并存到新集合中
	 */
	@Test
	public void test3(){
		jedis.sadd("user1", new String[]{"1","2","3"});
		jedis.sadd("user2", "2");
		
		Set<String> set=jedis.sdiff(new String[]{"user1","user2"});
		System.out.println(set);
		
		jedis.sdiffstore("user3", new String[]{"user1","user2"});
		
		jedis.sinter(new String[]{"user1","user2"});
		
		jedis.sinterstore("user4", new String[]{"user1","user2"});
		
		jedis.sunion(new String[]{"user1","user2"});
		
		jedis.sunionstore("user5", new String[]{"user1","user2"});
		jedis.close();
	}
	
	
}
