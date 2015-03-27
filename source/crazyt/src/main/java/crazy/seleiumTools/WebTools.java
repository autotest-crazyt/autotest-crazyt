package crazy.seleiumTools;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import crazy.tools.BasicTools;
import crazy.tools.Dbtool;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30�� ����10:18:04
 * @see
 */
public class WebTools extends BasicTools {
	private WebDriver dr = null;
	private Alert alert = null;
	public Webscroll webscroll = null;
	public Actions a = null;
	

	// private Dbconn conn=null;

	// public WebTools(WebDriver driver) {
	// this.dr = driver;
	// webscroll = new Webscroll(this.dr);
	// }

	public WebTools(WebDriver driver, Dbtool dbtool) {
		super(driver, dbtool);
		this.dr = driver;
		webscroll = new Webscroll(this.dr);
		a = new Actions(dr);
		
	}

	/**
	 * �ж��Ƿ���alert������
	 */
	public boolean isAlertPresent() {
		try {
			for (int i = 0; i < 10; i++) {
				if (this.dr.switchTo().alert() != null) {
					this.alert = this.dr.switchTo().alert();
					break;
				}

				this.threadSleep(1);
			}

			return true;
		} catch (NoAlertPresentException Ex) {
			this.pt("��ʾ�򲻴���");
			return false;
		}
	}

	/**
	 * ��ȡ�����������alert
	 */
	public String getAlert() {
		this.isAlertPresent();
		String str = alert.getText();

		return str;
	}

	/**
	 * ���������alert��ȷ����ť
	 */
	public void clickAlertOkBotton() {
		this.isAlertPresent();
		this.threadSleep(3);
		this.alert.accept();
	}

	/**
	 * ͨ��uiSelector xPath ��Ԫ�أ����ȴ�50��<br/>
	 * �������elementNameΪ��ǰpage��excel�е�elementName<br/>
	 * ����������List ������ͨ��&&���зָ� excel��Listѡ������Ҫͨ��&&���б�ע���磺<br/>
	 * ���------Ԫ������ --------elementName---------UiSelecter <br/>
	 * 1-------�ֻ��������------phoneNoEditText-----new
	 * UiSelector().className(\"android.widget.EditText\")&&0<br/>
	 * 
	 * 
	 * @param uiSelector
	 */

	public WebElement getWebElement(String tablenameindb, String elementnameindb,
			SelectorType type, int timeout) {

		return this.setHighLight(super.getElement(tablenameindb, elementnameindb, type, timeout));

		// return super.getElement(tablenameindb, elementnameindb, type, timeout);

	}

	// ͨ��ע��jQuery
	public void injectjQuery() {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		jse.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');"
				+ "newScript.type = 'text/javascript';" + "newScript.src = "
				+ "'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
	
	/**
	 * ����js�ű�
	 * @see
	 *
	 */
	public void runjs(String jsstr) {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		jse.executeScript(jsstr);
	}
	
	/**
	 * ����js�ű�
	 * @see
	 * @param element  =jsstr�е�arguments[0];
	 * @return String
	 */
	public String runjs(String jsstr,WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) dr;
		return (String) jse.executeScript(jsstr,element);
	}
	
	

	public WebElement setHighLight(WebElement element) {

		if (element == null) {
			return element;
		} else {
			JavascriptExecutor js = (JavascriptExecutor) this.dr;
			js.executeScript(
					"element = arguments[0];"
							+ "original_style = element.getAttribute('style');"
							+ "element.setAttribute('style', original_style + \";background: rgba(33, 255, 0, 0.69);  \");"
//							+ "setTimeout(function(){element.setAttribute('style', "
//							+ "element.getAttribute('style').replace(\";background: red;  \",\"\"));}, 200);"
							,
					element);
			// a.moveToElement(element).perform();
			// a.moveByOffset(0, 0).perform();

			return element;
		}

	}

	/**
	 * ͨ��uiSelector xPath ��Ԫ��list�����ȴ�50��<br/>
	 * �������elementNameΪ��ǰpage��excel�е�elementName<br/>
	 * ����������List ������ͨ��&&���зָ� excel��Listѡ������Ҫͨ��&&���б�ע���磺<br/>
	 * ���------Ԫ������ --------elementName---------UiSelecter <br/>
	 * 1-------�ֻ��������------phoneNoEditText-----new
	 * UiSelector().className(\"android.widget.EditText\")&&0<br/>
	 * 
	 * 
	 * @param uiSelector
	 */

	public List<WebElement> getWebElements(String tablenameindb, String elementnameindb,
			SelectorType type) {

		return super.getElements(tablenameindb, elementnameindb, type, 50);

	}

	/**
	 * ��ȡList�е����Webelement start��ʾlist�������Χ��ʼ�±�ֵ,end ��ʾ�����Χ�����±�
	 * �����end����list����±꣬ȡ����±�
	 */
	public WebElement getRandomWebElementFromList(List<WebElement> list, int start, int end) {
		super.pt("list.size = " + list.size());
		int j = list.size() - 1;
		end = end > j ? j : end;
		int r = this.getRandomInt(start, end);
		super.pt("����ѡ��ֵΪ��list[" + r + "]=" + list.get(r).getText());
		return list.get(r);
	}

	/**
	 * ��ȡList�е����һ��Webelement ֵ
	 */
	public WebElement getLastWebElementFromList(List<WebElement> list) {
		super.pt("list.size = " + list.size());
		int r = list.size() - 1;
		super.pt("����ѡ��ֵΪ��list[" + r + "]=" + list.get(r).getText());
		return list.get(r);
	}

	// ����Ҽ�����
	public void rightClickTheMouse(WebElement element) {

		this.setHighLight(element);
		Actions actions = new Actions(this.dr);
		actions.contextClick(element).perform();

	}

	// ���˫�����
	public void doubleClickTheLeftMouseButton(WebElement element) {

		this.setHighLight(element);
		Actions action = new Actions(this.dr);
		action.doubleClick(element).perform();

	}

	// ����������
	public void clickTheLeftMouseButton(WebElement element) {

		Actions action = new Actions(this.dr);
		action.click(element).perform();

	}

	// ����϶�Ԫ�ص�ָ��Ԫ����
	public void dragElementToElementByMouse(WebElement source, WebElement target) {

		this.setHighLight(source);

		Actions action = new Actions(this.dr);
		action.dragAndDrop(source, target);
		this.setHighLight(target);

	}

	// ��Ŀ��Ԫ����ק��ָ����������
	public void dragElementToArea(WebElement element, int xOffset, int yOffset) {

		Actions action = new Actions(this.dr);
		action.dragAndDropBy(element, xOffset, yOffset);

	}

	public void dragAndDrop(WebElement e, int x, int y) {
	a.clickAndHold(e).perform();
	a.moveByOffset(x, y).release().perform();
		
		//a.dragAndDropBy(e, x, y);
	}

	public void dragAndDrop(WebElement e, WebElement toe) {
		a.dragAndDrop(e, toe).perform();
	}

	/**
	 * ��������������Ԫ�����ڴ�
	 */
	public WebElement scrollerToElement(WebElement el) {
		this.webscroll.scrollToWebElement(el);
		return el;
	}

	public Select getDrop_DownList(String tablenameindb, String elementnameindb, SelectorType type) {
		Select select = new Select(this.getWebElement(tablenameindb, elementnameindb, type, 10));

		return select;
	}

	// ===================�����´�����ת������=====================

	public boolean toNewWindows(String newWindowsTitle) {

		Set<String> handles = null;	
		WebDriver window = null;
		boolean t = false;
		for (int i = 0; i < 5; i++) {
		
			handles = this.dr.getWindowHandles();// ��ȡ���д��ھ��
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {

				try {
					window = this.dr.switchTo().window(it.next());// �л����´���
				} catch (TimeoutException e) {
					super.rpt(window.getTitle() + "  ҳ����س���50��  ���ȴ�������� ����ִ��");
				}
				super.pt("the" + i + "times search :one of titles is:"
						+ window.getTitle());

				if (window.getTitle().equals(newWindowsTitle)) {
					super.rpt("New page title is:" + window.getTitle());
					t = true;
					break;
				}
				// window.close();//�ر��´���
			}
			if (t) {
				break;
			}
			super.threadSleep(1);
		}
		BrowersSize.maxBrowers(this.dr);
		
		if(this.dr.getTitle().equals(newWindowsTitle)){
			return true;
		}
		return false;	
	}

	public void toNewFrame(WebElement frameElement) {
		this.dr.switchTo().frame(frameElement);
	}

	public void toDefaultFrame() {
		this.dr.switchTo().defaultContent();
		
	}

	// firefox  ie64λ���İ� �ļ��ϴ�
	public void clickfileUploadElementThenUploadByAutoItforfireFox_64(WebElement el,
			String fileallURL) {

		int i = fileallURL.lastIndexOf("\\");
		String str = fileallURL.substring(i + 1);

		fileallURL = fileallURL.replace("\\" + str, "   " + str);
		el.click();
		getRuntimeexec(System.getProperty("user.dir") + "\\tool\\fileUploadByAutoItforfireFox_64.exe"
				+ "  " + fileallURL);
	}

	// cmd�������
	public static void getRuntimeexec(String cmdStr) {

		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmdStr);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			// super.pt(p.toString());
		} finally {

		}

	}
	
	// cmd������ð�������ֵ
	public int getRuntimeexecValue(String cmdStr) {

		Process p = null;
		int res=0;
		try {
			p = Runtime.getRuntime().exec(cmdStr);
			res=p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			// super.pt(p.toString());
		} finally {

		}
		return res;

	}


	public WebElement getWebElementByReplaceSelectorOfindb(String tablenameindb,
			String elementnameindb, SelectorType type, String valueOfvar) {
		return this.setHighLight(super.getWebElementByReplaceSelectorOfindb(tablenameindb,
				elementnameindb, type, valueOfvar, 10));

	}

	/**
	 * ������� ������ ��һ���� �ɿ���� x,y����ΪelPoint���������
	 * 
	 */
	public void getFrameInElByMouse(WebElement el, String  elDescription,int sx, int sy, int ex, int ey) {
		super.rpt("��"+elDescription+"������");
	
		sy = (el.getSize().height / 2 < sy || sy <= (int) (el.getSize().height * 0.15)) ? el
				.getSize().height / 4 : sy;
		sx = (el.getSize().width / 2 < sx || sx <= (int) (el.getSize().width * 0.15)) ? el
				.getSize().width / 4 : sx;

		ey = (el.getSize().height < ey || ey <= sy) ? (int) (el.getSize().height * 0.5) : ey;
		ex = (el.getSize().width < ey || ex <= sx) ? (int) (el.getSize().width * 0.5) : ex;

		super.pt("sx:" + sx + "  sy:" + sy + "  ex:" + ex + "  ey:" + ey);
		a.moveToElement(el, sx, sy).perform();
		a.clickAndHold().perform();
		a.moveByOffset(ex, ey).perform();
		a.release().perform();
	}
	


	
	
}
