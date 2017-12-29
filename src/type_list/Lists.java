package type_list;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * ��ʾlist�����е�����
 * @author Administrator
 *
 */
public class Lists {
	 //����redis���ݿ�
		private static Jedis jedis=new Jedis();
		
		
		/**
		 * lpush:��listͷ�����ֵ
		 * rpush:��listβ�����ֵ
		 * lrange:��ȡָ��λ�õ�ֵ
		 */
		@Test
		public void test1(){
			jedis.lpush("grade406", "����");
			jedis.rpush("grade406", "�⾩");
			
			List<String> list=jedis.lrange("grade406", 0, 20);
			System.out.println(list);
			jedis.close();
		}
		
		/**
		 * lpop:��ͷ������key��ֵ��ɾ����
		 * rpop:��β������key��ֵ��ɾ����
		 */
		@Test
		public void test2(){
			String str=jedis.lpop("grade406");
			System.out.println(str);
			String str2=jedis.rpop("grade406");
			System.out.println(str2);
			jedis.close();
		}
		
		/**
		 * rpoplpush:����listβ����ֵ����һ��list
		 * llen:����key�ĳ���
		 * lindex:�����±��ֵ
		 * ltrim:����listָ��λ�õ�ֵ
		 */
		@Test
		public void test3(){
			long l=jedis.llen("grade406");
			String st=jedis.lindex("grade406", 1);
	
			String str=jedis.ltrim("grade406", 0, 4);
			System.out.println(str);
			
			jedis.lpush("stu1", "a");
			jedis.rpoplpush("grade406","stu1");
			jedis.close();
		}
}
