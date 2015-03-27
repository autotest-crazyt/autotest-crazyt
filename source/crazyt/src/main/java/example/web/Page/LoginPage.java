package example.web.Page;

import org.openqa.selenium.WebDriver;

import crazy.seleiumTools.WebBasicPage;
import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��12��  ����3:03:50
 * 
 */
public class LoginPage extends WebBasicPage{

	/**
	 * @param wdriver
	 * @param dbtool
	 */
	public LoginPage(WebDriver wdriver, Dbtool dbtool) {
		super(wdriver, dbtool);
		// TODO Auto-generated constructor stub
	}
	
	public LoginPage login(String username,String password){
		super.btool.rpt("�����û��������¼");
		super.editInputElement("�û���", username)
		.editInputSecret("����", password)
		.clickWebElement("��¼");
		return this;
	}

}
