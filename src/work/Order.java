package work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Tools.ObjectUtils;
import redis.clients.jedis.Jedis;

public class Order {
	
    static Jedis jedis=new Jedis();
    final static String ORDER_KEY="orderList";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		byte[] srcByte=jedis.lpop(ORDER_KEY.getBytes());
		Map map=(Map)ObjectUtils.byteToObject(srcByte);
		
		/*StringBuilder sb=new StringBuilder();
		Set set=map.keySet();
		Iterator iter=set.iterator();
		while(iter.hasNext()){
			String key=(String) iter.next();
			sb.append(map.get(key)).append(",");
		}
		
		String str=sb.substring(0, sb.length()-1);
		
		System.out.println(str);*/

		String id=(String) map.get("id");
		String name=(String) map.get("name");
		String age=(String) map.get("age");
		String sex=(String) map.get("sex");
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(id).append(",").append(name).append(",").append(age).append(",").append(sex);
		
		String str=new String(sb);
		System.out.println(str);
		
		FileOutputStream fos=new FileOutputStream("C:\\ProgramData\\MySQL\\MySQL Server 5.5\\data\\work\\ee.CSV ",true);
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
		
		bw.write(str);
		bw.close();
		
	}

}
