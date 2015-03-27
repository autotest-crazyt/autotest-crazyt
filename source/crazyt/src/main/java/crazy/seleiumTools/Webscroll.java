package crazy.seleiumTools;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30��  ����10:17:56
 * @see
 */
public class Webscroll {
	private WebDriver drr = null;

	public Webscroll(WebDriver driver) {
		this.drr = driver;
	}


	//������ָ��Ԫ��λ�� 
	public void scrollToWebElement (WebElement element){
		
		((JavascriptExecutor) drr).executeScript("arguments[0].scrollIntoView();",element ); 
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	/**
	 * ���element��id��ҳ����Ψһ������ʹ�ø÷������й�����ƫ������ʹelement��������ͼ��
	 *  @author xian_crazy QQ��330126160
	 */
	public void scrollToElement(WebElement element){
		JavascriptExecutor	jse=(JavascriptExecutor) drr;	
		
		String js="var el=document.getElementById(\""+element.getAttribute("id")+"\");"
				//��λ���й�����������
				+ "var fdiv=el.offsetParent;"
				//����������Ӹ߶�С��el��ƫ�Ƹ߶ȣ�������϶�����������
				+ "while(fdiv.offsetHeight<el.offsetTop+el.offsetHeight){"
				//���������¹���
				+ "fdiv.scrollTop=fdiv.scrollTop+el.offsetHeight;"
				//���������������el���֣���ֹͣ����
				+ "if(fdiv.scrollTop+fdiv.offsetHeight-el.offsetHeight>el.offsetTop+el.offsetHeight){"
				+ "break;}"
				+ "}"
				;
		jse.executeScript(js);
	}
	
	public void scrollToTop (){
		JavascriptExecutor jse = (JavascriptExecutor)drr;
		jse.executeScript("window.scrollBy(0,0)", "");
 
	}
	
	
	
}
