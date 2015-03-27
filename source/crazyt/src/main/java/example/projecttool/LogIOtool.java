package example.projecttool;

import org.testng.Assert;

import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��12��2��  ����11:30:14
 * @see
 */
public class LogIOtool {

	
	public static void login(PageF pf,Dbtool dbtool){
		System.out.println(dbtool.toString());
		String username=dbtool.getValuebyKey("�û���", "userData", "���", "1");
		String pw=dbtool.getValuebyKey("����", "userData", "���", "1");
	pf.loginpage.login(username, pw);
	pf.mainMenuPage.toHome();
	Assert.assertEquals(dbtool.getValuebyKey("�ǳ�", "userData", "���", "1"), 
			pf.mainMenuPage.getNickname());
	}
	
	public static void logout(PageF pf){
		pf.mainMenuPage.logOut();
	}
}
