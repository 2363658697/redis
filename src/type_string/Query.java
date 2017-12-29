package type_string;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;
/**
 * 从CSV文件读取信息到redis缓存
 * @author Administrator
 *
 */
public class Query {
	private static Jedis jedis=new Jedis();
	
	public static void main(String[] args) throws Exception {
			String name="c";
			String str=query(name);
			System.out.println(str);
	}


	
	public static String query(String name) throws IOException{
		if(jedis.keys(name).size()>0){
			return jedis.get(name);
		}
		
		FileInputStream fis=new FileInputStream("C:\\ProgramData\\MySQL\\MySQL Server 5.5\\data\\work\\ee.CSV ");
		BufferedReader br=new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String result=null;
		String line=null;
		while((line=br.readLine())!=null){
			String[]  str=line.split(",");
			String value=str[0].replace("\"", "")+str[1].replace("\"", "")+str[2].replace("\"", "")+str[3].replace("\"", "");
			String key=str[1].replace("\"", "");
			if(name.equals(key)){
				jedis.set(key, value);
				result=jedis.get(key);
				break;
			}	
		}
		fis.close();
		br.close();
		jedis.close();
		return result;
	}
	
}
