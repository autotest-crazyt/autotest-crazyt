package example.web.Page;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import crazy.seleiumTools.WebBasicPage;
import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��12��  ����5:43:37
 * @see
 */
public class MainMenuPage extends WebBasicPage{
	/**
	 * @param wdriver
	 * @param dbtool
	 */
	public MainMenuPage(WebDriver wdriver, Dbtool dbtool) {
		super(wdriver, dbtool);
	}
	
	public String getNickname(){
		return super.getWebElementTextBy160SpaceSplit("�ǳ�");
	}

	
	public MainMenuPage clickWeiZhi(){
		super.clickWebElement("λ�÷���");
		return this;
	}
	
	public MainMenuPage clickDingWei(){
		super.clickWebElement("��λ����");
		
		super.toNewWindws("��λ����-������ƽ̨");
		return this;
	}
	
	public MainMenuPage clickGuiJi(){
		super.clickWebElement("�켣�ط�");
		super.toNewWindws("�켣�ط�-������ƽ̨");
		return this;
	}
	
	public MainMenuPage clickWuLiu(){
		super.clickWebElement("������ͼ");
		super.toNewWindws("������ͼ-������ƽ̨");
		return this;
	}
	
	
	
	public MainMenuPage clickTongJiBaoBiao(){
		super.clickWebElement("ͳ�Ʊ���");
		return this;
	}
	
	public MainMenuPage logOut(){
		super.hovermouseFornewElementThenClick("�ǳ�", "�˳�");
		super.toNewWindws("������ƽ̨");
		return this;
	}
	
	/**
	 * ��ͳ�Ʊ�����ӱ���
	 * @see
	 * @param subMenuName
	 */
	public void toTongJiBaoBiao_SubMenuPage(String subMenuName){
		boolean t=true;
		for(int i=0;i<3&&t;i++){
		t=!super.hovermouseFornewElementThenClick("ͳ�Ʊ���", subMenuName)		
		.toNewWindws("������ƽ̨");
		}
		Assert.assertEquals(t, false,"ͳ�Ʊ���˵�δ�ҵ�"+subMenuName);
		super.getWebElementInTime("���ر���ͳ��", 10);
		super.threadSleep(5);
	}
	
	/**
	 * ��ͳ�Ʊ���˵��е�һ������
	 */
	public void  open1stReport(){
		this.toTongJiBaoBiao_SubMenuPage(super.findWebElement("��ǰ��ѡ����").getAttribute("text"));
	}
	
	
	/**
	 * ����ҳ
	 */
	public void toHome(){
		while(!super.getDriver().getTitle().equals("��ҳ-������ƽ̨")){
			super.clickWebElement("��ҳ");
			super.toNewWindws("��ҳ-������ƽ̨");
		}
	}
	
	
	
	
	


}
