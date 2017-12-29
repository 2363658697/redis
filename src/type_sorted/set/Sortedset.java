package type_sorted.set;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 演示sorted set 类型命令
 */
public class Sortedset {

	// 连接redis数据库
	private static Jedis jedis = new Jedis();
	
	/**
	 * zadd:添加成员
	 * zcard:获取成员数量
	 * zcount:获取指定分数间的成员数量
	 * zrem:删除指定成员
	 */
	@Test
	public void test1(){
		
		jedis.zadd("class1", 40,"ss");
		
		jedis.zcard("class1");
		
		jedis.zcount("class1", 40, 80);
		
		jedis.zrem("class1", "zs");
		jedis.close();
	}
	
	/**
	 * zincrby:给指定成员添加分数
	 * zrange:遍历指定下标之间的成员【及分数】[分数从小到大]
	 * zrevrange:遍历指定下标之间的成员【及分数】[分数从大到小]
	 * zrangebyscore:遍历指定分数之间的成员[及分数]
	 * zrevrangebyscore:遍历指定分数之间的成员[及分数][分数从大到小]
	 */
	@Test
	public void test2(){
		Map map=new HashMap<>();
		map.put("zs",34.5);
		map.put("ls",38.6);
		map.put("ww",38.3);
		jedis.zadd("class1", map);
		
		jedis.zincrby("class1", 40.0, "zs");
		
		Set<String> set=jedis.zrange("class1", 0, 10);
		System.out.println(set);
		
		Set<String> set2=jedis.zrevrange("class1", 0, 10);
		System.out.println(set2);
		
		Set<String> set3=jedis.zrangeByScore("class1", 30.0, 80.0);
		System.out.println(set3);
		
		Set<String> set4=jedis.zrevrangeByScore("class1", 30.0, 80.0);
		jedis.close();
	}
	
	
	/**
	 *zrank: 返回成员的下标（分数从小到大）
	 *zrevrank:返回成员的下标（分数从大到小）
	 *zscore:返回指定成员的分数
	 *zremrangebyrank:删除下标之间的成员
	 *zremrangebyscore:删除指定分数之间的成员
	 */
	@Test
	public void test3(){
		Map map=new HashMap<>();
		map.put("zs",34.5);
		map.put("ls",38.6);
		map.put("ww",38.3);
		jedis.zadd("class1", map);
		
		jedis.zrank("class1", "zs");
		
		jedis.zrevrank("class1", "zs");
	
		jedis.zremrangeByRank("class1",0,5);
		
		jedis.zremrangeByScore("class1", 30.0, 60.0);
		jedis.close();
	}
	
}
