package Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 对象工具类
 * @author Administrator
 *
 */
public class ObjectUtils {
	/**
	 * 将对象转换为字节数组
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	public static byte[] objectToByte(Object obj) throws IOException{
		ByteArrayOutputStream boas=new ByteArrayOutputStream();
		ObjectOutputStream oops=new ObjectOutputStream(boas);
		oops.writeObject(obj);
		oops.close();
		return boas.toByteArray();
	}
	
	/**
	 * 将字节数组转换为对象
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	public static Object byteToObject(byte[] src) throws Exception{
		ByteArrayInputStream boas=new ByteArrayInputStream(src);
		ObjectInputStream ois=new ObjectInputStream(boas);
		return ois.readObject();
	}
}
