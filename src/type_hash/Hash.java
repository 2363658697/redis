package type_hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * ��ʾhash���͵�����
 * @author Administrator
 *
 */
public class Hash {
	 //����redis���ݿ�
	private static Jedis jedis=new Jedis();
	
	
	/**
	 * hset:��key�е�filed�ֶθ�ֵ
	 * hget����ȡkey�е�filed�ֶε�ֵ
	 */
	@Test
	public void test1(){
		jedis.hset("user1", "name", "a");
		String name=jedis.hget("user1", "name");
		System.out.println(name);
	}
	
	/**
	 * hexists���ж�key���Ƿ����filed�Զ�
	 * hlen����ȡkey��filed�ֶε�����
	 * hdel:ɾ��key��filed�ֶ�
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
	 * hgetall:��ȡkey�����е�filed��value
	 * hmset:ͬʱ���ö��filed��value
	 * hmget��ͬʱ��ȡ���filed��ֵ
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
	 *hkeys: ��ȡ���е�key
	 *hvals: ��ȡ���е�value
	 */
	@Test
	public void test4(){
		Set<String> keys=jedis.hkeys("user1");
		
		List<String>  values= jedis.hvals("user1");
		
		jedis.close();
	}
	
}
