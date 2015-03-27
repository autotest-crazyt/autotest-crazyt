package example.web.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��12��  ����6:19:02
 * @see  ���˵������֤�Ƿ���ȷ��ת���Ѿ�ʵ��λ�÷��������˵���
 */
public class MainMenuCheck extends ClassConfig{


	@Test(groups =  "level0" )   
	public void mainMenuCheckDingWei(){
	
		pf.mainMenuPage.clickWeiZhi().clickDingWei();
		Assert.assertEquals(super.getDriver().getTitle(), "��λ����-������ƽ̨");
	}
	
	@Test(groups =  "level0" )   
	public void mainMenuCheckGuiJi(){
		pf.mainMenuPage.clickWeiZhi().clickGuiJi();
		Assert.assertEquals(super.getDriver().getTitle(), "�켣�ط�-������ƽ̨");
	}
	
	@Test
	public void mainMenuCheckWuLiu(){
		pf.mainMenuPage.clickWeiZhi().clickWuLiu();
		Assert.assertEquals(super.getDriver().getTitle(), "������ͼ-������ƽ̨");
	}

}
