package type_set;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
/**
 * ��ʾset���͵�����
 * @author Administrator
 *
 */
public class Sets {
	 //����redis���ݿ�
	private static Jedis jedis=new Jedis();
	
	
	/**
	 * sadd:���ֵ
	 * smembers:��������
	 * scard:��ȡkey�ĳ�Ա����
	 * srem:ɾ��ָ����Ա
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
	 * sismember:�жϳ�Ա�Ƿ����
	 * spop:�������һ��ֵ��ɾ����
	 * srandmember:�������һ��ֵ����ɾ����
	 * smove:�ƶ�һ�����ϵĳ�Ա����һ������
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
	 * sdiff:�󼯺ϵĲ
	 * sdiffstore:�󼯺ϵĲ���浽�¼�����
	 * sinter:�󼯺ϵĲ���
	 * sinterstore:�󼯺ϵĲ������浽�¼�����
	 * sunion:�󼯺ϵĽ���
	 * sunionstore:�󼯺ϵĽ������浽�¼�����
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
