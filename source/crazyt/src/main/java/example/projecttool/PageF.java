package example.projecttool;

import org.openqa.selenium.WebDriver;

import crazy.seleiumTools.PageFactory;
import crazy.tools.Dbtool;
import example.web.Page.LoginPage;
import example.web.Page.MainMenuPage;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��13�� ����9:19:46
 * ҳ�湤������ҳ�����ڴ���������
 */
public class PageF extends PageFactory{

	public MainMenuPage mainMenuPage;
	public LoginPage loginpage;


	public PageF(WebDriver driver, Dbtool dbtool) {
		super(driver, dbtool);
	}



}
