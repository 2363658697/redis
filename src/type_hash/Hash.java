package type_hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 演示hash类型的命令
 * @author Administrator
 *
 */
public class Hash {
	 //连接redis数据库
	private static Jedis jedis=new Jedis();
	
	
	/**
	 * hset:给key中的filed字段赋值
	 * hget：获取key中的filed字段的值
	 */
	@Test
	public void test1(){
		jedis.hset("user1", "name", "a");
		String name=jedis.hget("user1", "name");
		System.out.println(name);
	}
	
	/**
	 * hexists：判断key中是否存在filed自段
	 * hlen：获取key中filed字段的数量
	 * hdel:删除key中filed字段
	 */
	@Test
	public void test2(){
		boolean bool=jedis.hexists("user1", "name");
		System.out.println(bool);
		long l=jedis.hlen("user1");
		System.out.println(l);
		long ll=jedis.hdel("user1", "name");
		System.out.println(ll);
		jedis.close();
	}
	
	/**
	 * hgetall:获取key中所有的filed和value
	 * hmset:同时设置多个filed和value
	 * hmget：同时获取多个filed的值
	 */
	@Test
	public void test3(){
		Map map=jedis.hgetAll("user1");
		System.out.println(map);	
		
		Map map2=new HashMap<>();
		map2.put("age", "11");
		map2.put("grade", "66");
		jedis.hmset("user1", map2);
		
		
		List list=jedis.hmget("user1",new String[]{"name","grade"});
		System.out.println(list);
		
		jedis.close();
	}
	
	/**
	 *hkeys: 获取所有的key
	 *hvals: 获取所有的value
	 */
	@Test
	public void test4(){
		Set<String> keys=jedis.hkeys("user1");
		
		List<String>  values= jedis.hvals("user1");
		
		jedis.close();
	}
	
}
