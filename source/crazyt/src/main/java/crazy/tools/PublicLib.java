package crazy.tools;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class PublicLib {

	public AndroidDriver androidDriver = null;

	public PublicLib(AndroidDriver androidDriver) {
		this.androidDriver = androidDriver;
	}

	/**
	 * ������ͬһ��ҳ����10��ImageView���󣬶����ǵ�Ŀ��Ԫ�ص�indexΪ4����ͬΪImageView��indexΪ4��ҳ��ֻ��2����
	 * �����ǾͿ��Ը�����2����������ϲ�ѯ���������Ч��
	 * 
	 * @param classname
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByClassAndIndex(String classname,
			int index) {
		List<WebElement> list = null;
		list = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().className("
						+ classname + ").index(" + index + ")");
		return list;
	}

	/**
	 * ������ͬһ��ҳ����10��ImageView���󣬶�����indexΪ4����5��������ʱ���Ƿ������ǵ�Ŀ��Ԫ�ص���clickable�ġ�
	 * Ȼ��reviewҳ�淢�֣�ͬʱ��������������ֻ��2����
	 * 
	 * @param classname
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByClassAndIndexAndClickable(
			String classname, int index) {
		List<WebElement> lis = null;
		lis = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().className("
						+ classname + ").index(" + index + ").clickable(true)");
		return lis;
	}

	/**
	 * �������ڷ���ҳ��Ԫ�ص�ʱ���֣�ҳ����ԱȽϼ򵥣���������ֻ��Ŀ��Ԫ�ص�indexΪ4.
	 * 
	 * @param index
	 * @return
	 */
	public WebElement getElementByIndex(int index) {
		return androidDriver
				.findElementByAndroidUIAutomator("new UiSelector().index("
						+ index + ")");
	}
	
	/**
	 * �������ڷ���ҳ��Ԫ�ص�ʱ���֣�ҳ����ԱȽϼ򵥣���������Ŀ��Ԫ�ص�indexΪ4ֻ��2��.
	 * @param index
	 * @return
	 */
	public List<WebElement> getElementsByIndex(int index) {
		List<WebElement> lis = null;
		lis = androidDriver
				.findElementsByAndroidUIAutomator("new UiSelector().index("
						+ index + ")");
		return lis;
	}

	/**
	 * �����������Ĭ��text���ҵ���ѡ��ʱ��������յ���յĴ���
	 * 
	 * @param el
	 */
	public void clear(WebElement el) {
		el.click(); // ѡ�������
		androidDriver.sendKeyEvent(123);// ������Ƶ����
		String txt = el.getText(); // ��ȡ�ַ�������
		for (int i = 0; i < txt.length(); i++) {
			androidDriver.sendKeyEvent(67);// һ������ɾ������������
		}
	}

	/**
	 * ��ȡ��ǰҳ���xml
	 * 
	 * @return String
	 */
	public String getPageSource() {
		return androidDriver.getPageSource();
	}

	/**
	 * appium��֧���������� �ο���robotium����js��ʽΪԪ��ֱ������value������
	 * ����Selenium��Webdriverִ��js����ʵ����������
	 * 
	 * @param id
	 * @param str
	 */
	public void inputChinese(String id, String str) {
		JavascriptExecutor jse = (JavascriptExecutor) androidDriver;
		jse.executeScript("document.getElementById('" + id + "').value='" + str
				+ "'");

	}

	/**
	 * �л���webView�У��˷������д��Ľ�,û�о�������
	 */
	public void SwitchtoWebViewModel() throws Exception {
		try {
			Set<String> contextNames = androidDriver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println(contextName);
				// ���ڷ��ر���app��NATIVE_APP����WEBVIEW��������߶��о��ǻ����App
				if (contextName.contains("WEBVIEW")) {
					androidDriver.context(contextName);
					// appDriver.switchTo().window("WEBVIEW");
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * ���ڶ�λ������Ԫ�أ���ʱ����ʹ��ģ����x��y������ķ�ʽʵ��click�¼� ������-������ѡ��-ָ��λ������ȡԪ�ص�x��y������
	 * 
	 * @param x
	 *            �����Ԫ�ص�x������
	 * @param y
	 *            �����Ԫ�ص�y������
	 */
	public void clickPoint(int x, int y) {
//		androidDriver.swipe(x, y, x, y, 1);// ���һ������Ϊ�ƶ��ٶȣ�Խ���ƶ��ٶ�Խ��
		
		TouchAction ta = new TouchAction(androidDriver);
		androidDriver.performTouchAction(ta.tap(x, y));
	}

	/**
	 * ��ȡָ��Ԫ�صĸ�
	 * 
	 * @param webElement
	 * @return
	 */
	public int getHeighByElement(WebElement webElement) {
		return webElement.getSize().getHeight();
	}

	/**
	 * ��ȡָ��Ԫ�صĿ�
	 * 
	 * @param webElement
	 * @return
	 */
	public int getWidthByElement(WebElement webElement) {
		return webElement.getSize().getWidth();
	}

	/**
	 * ��ȡָ��Ԫ�ص�x�����ʼ����
	 * 
	 * @param webElement
	 * @return
	 */
	public int getStartXByElement(WebElement webElement) {
		return webElement.getLocation().getX();
	}

	/**
	 * ��ȡָ��Ԫ�ص�y�����ʼ����
	 * 
	 * @param webElement
	 * @return
	 */
	public int getStartYByElement(WebElement webElement) {
		return webElement.getLocation().getY();
	}

	/**
	 * ��ȡָ��Ԫ�ص�x��Ľ�������
	 * @param webElement
	 * @return
	 */
	public int getEndXByElement(WebElement webElement) {
		return this.getStartXByElement(webElement) + this.getHeighByElement(webElement);
	}
	
	/**
	 * ��ȡָ��Ԫ�ص�y��Ľ�������
	 * @param webElement
	 * @return
	 */
	public int getEndYByElement(WebElement webElement) {
		return this.getStartYByElement(webElement) + this.getWidthByElement(webElement);
	}

}
