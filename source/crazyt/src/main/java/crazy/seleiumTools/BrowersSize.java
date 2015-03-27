package crazy.seleiumTools;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30��  ����10:17:15
 * @see
 */
public class BrowersSize {
	private  Dimension screenDims;
	private int width;
	private int height;

	
	//��������
public static void maxBrowers(WebDriver driver){
	    
	   driver.manage().window().maximize();
	    
	    }
	//�õ���ʾ���ߴ�
	public  Dimension getScreenDims() {
		screenDims = Toolkit.getDefaultToolkit().getScreenSize();
	    //System.out.println(screenDims.getHeight());
	    //System.out.println(screenDims.getWidth());
		return screenDims;
	}
	
	//������������ڴ�С
	public void setBrowserSize(WebDriver driver, int width, int height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.moveTo(0,0)");
		this.width = width;
		this.height = height;
		js.executeScript("window.resizeTo(" + this.width + "," + this.height+ ")");
	}
	
	public void setBrowserSize(WebDriver driver, Dimension dimension){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.moveTo(0,0)");
		this.width = (int) dimension.getWidth();
		this.height = (int) dimension.getHeight();
		js.executeScript("window.resizeTo(" + this.width + "," + this.height+ ")");
	}

}
