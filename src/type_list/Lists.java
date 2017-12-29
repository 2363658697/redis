package type_list;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 演示list类型中的命令
 * @author Administrator
 *
 */
public class Lists {
	 //连接redis数据库
		private static Jedis jedis=new Jedis();
		
		
		/**
		 * lpush:在list头部添加值
		 * rpush:在list尾部添加值
		 * lrange:获取指定位置的值
		 */
		@Test
		public void test1(){
			jedis.lpush("grade406", "胡歌");
			jedis.rpush("grade406", "吴京");
			
			List<String> list=jedis.lrange("grade406", 0, 20);
			System.out.println(list);
			jedis.close();
		}
		
		/**
		 * lpop:从头部弹出key的值（删除）
		 * rpop:从尾部弹出key的值（删除）
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
		 * rpoplpush:弹出list尾部的值到另一个list
		 * llen:返回key的长度
		 * lindex:返回下标的值
		 * ltrim:返回list指定位置的值
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
