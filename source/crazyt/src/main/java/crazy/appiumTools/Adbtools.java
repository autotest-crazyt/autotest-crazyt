package crazy.appiumTools;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30��  ����10:16:16
 * @see
 */
public class Adbtools {

	public AndroidAction action;

  /**
   * ͨ��adb�ķ�ʽ��ģ����edittext����������ַ����������������ַ�
   * @param engstr
   */
    public void adbinputtext(AppiumDriver dr,AndroidElement el,String engstr){
    	action = new AndroidAction(dr);
    	this.action.tapOneTime(el);
    	//el.click();
    	Runtime run = Runtime.getRuntime();
        Process pr;
        String cmd = "adb shell input text "+engstr;
        String cmdreturn = "";
      
		try {
			pr = run.exec(cmd);
			pr.waitFor();

        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
     
			while ((cmdreturn=buf.readLine())!=null) {
				if(!cmdreturn.equals("")){
			     System.out.println("cmdreturn="+cmdreturn);
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @Test 
    public void adbtest(){
//    	this.adbinputtext("123456");
    }
	
    

}
