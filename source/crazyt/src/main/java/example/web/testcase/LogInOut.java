package example.web.testcase;



import org.testng.Assert;
import org.testng.annotations.Test;

import example.projecttool.BATest;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��12�� ����5:02:18
 * @see
 */
public class LogInOut extends BATest{
	

	@Test
	public void login(){
	
		String username=BATest.getDbtool().getValuebyKey("�û���", "userData", "���", "1");
		String pw=BATest.getDbtool().getValuebyKey("����", "userData", "���", "1");
	pf.loginpage.login(username, pw);
	pf.mainMenuPage.toHome();
	Assert.assertEquals(BATest.getDbtool().getValuebyKey("�ǳ�", "userData", "���", "1"), 
			pf.mainMenuPage.getNickname());
	}
	
	
	@Test(dependsOnMethods="login")
	public void logout(){
		pf.mainMenuPage.logOut();
	}

	
	

}
