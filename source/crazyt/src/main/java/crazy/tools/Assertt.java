package crazy.tools;

import org.testng.Assert;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��12��17��  ����3:24:31
 * @see
 */
public class Assertt {

	private static boolean bool=true;
	private static String result="������£�\n";

	
	/**
	 * obj[][0]  ʵ��ֵ
	 * obj[][1] Ԥ��ֵ
	 * obj[][2]  message
	 * @see
	 * @param obj
	 */
	static public void massert(Object[][] obj){
		//obj =new Object[3];
		for(Object[] cobj:obj){
		if(!cobj[0].equals(cobj[1])){
			bool=false;
			result+="\n"+cobj[2]+"\n";
		}
		}
		
		Assert.assertEquals(bool,true,result);
		
	}
	

}
