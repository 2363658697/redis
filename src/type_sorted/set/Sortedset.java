package type_sorted.set;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * ��ʾsorted set ��������
 */
public class Sortedset {

	// ����redis���ݿ�
	private static Jedis jedis = new Jedis();
	
	/**
	 * zadd:��ӳ�Ա
	 * zcard:��ȡ��Ա����
	 * zcount:��ȡָ��������ĳ�Ա����
	 * zrem:ɾ��ָ����Ա
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
	 * zincrby:��ָ����Ա��ӷ���
	 * zrange:����ָ���±�֮��ĳ�Ա����������[������С����]
	 * zrevrange:����ָ���±�֮��ĳ�Ա����������[�����Ӵ�С]
	 * zrangebyscore:����ָ������֮��ĳ�Ա[������]
	 * zrevrangebyscore:����ָ������֮��ĳ�Ա[������][�����Ӵ�С]
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
	 *zrank: ���س�Ա���±꣨������С����
	 *zrevrank:���س�Ա���±꣨�����Ӵ�С��
	 *zscore:����ָ����Ա�ķ���
	 *zremrangebyrank:ɾ���±�֮��ĳ�Ա
	 *zremrangebyscore:ɾ��ָ������֮��ĳ�Ա
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
