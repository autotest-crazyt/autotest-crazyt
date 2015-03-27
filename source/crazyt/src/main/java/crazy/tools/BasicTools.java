package crazy.tools;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.internal.Constants;
import org.uncommons.reportng.HTMLReporter;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30�� ����10:18:16
 * @see
 */
public class BasicTools {

	private Dbtool dbtool;
	private WebDriver dr;
	private LoggerManager log=LoggerManager.getLogger(this.getClass());

	public BasicTools() {

	}

	public BasicTools(WebDriver driver, Dbtool dbtool) {
		this.dbtool = dbtool;
		this.dr = driver;

	}

	/**
	 * ��element�ж�ȡelement��Xpath�ַ���
	 * 
	 * @param tablenameindb
	 * @param elementnameindb
	 * @param type
	 * @return
	 */
	public String getXpathOfElement(WebElement el) {

		String elstr = el.toString();
		String x = "-> xpath: ";
		int i = elstr.indexOf(x);
		
		String xpath = elstr.substring(i + x.length(), elstr.length() - 1);
		// this.pt("getXpathOfElement:" + xpath);
		return xpath;

	}

	/**
	 * 
	 * @param uiSelector
	 * @param waitTime
	 *            ��
	 * @param bool  true  ��ʾ�����ڵ�ǰ��ͼ�ɼ�����ֵ>0��  false�ҵ�����һ���ɼ�
	 */
	public WebElement waitThenGetElement(String strSelector, int waitTime, SelectorType type,boolean bool) {
		WebElement element = null;
		int i = 0;

			boolean t1 = true;

			while (t1) {
				try {
					switch (type) {
					case UiSelector:
					element = ((AndroidDriver) dr).findElementByAndroidUIAutomator(strSelector);
					break;
					case xPath:
						element = dr.findElements(By.xpath(strSelector)).get(0);
					
						break;
					case CSS:
						element = dr.findElements(By.cssSelector(strSelector)).get(0);
						break;
					default:
						this.pt("waitThenGetElementappear���ʹ���");
					}
			
					TryForWaitReturn ret=tryForWait(element, t1,bool,i,waitTime);;
					t1=ret.getT();
					element=ret.getEl();
		
					
				} catch (Throwable e) {
					if (catchForWait(i, waitTime,false)) {
						break;
					}
				} finally {
					i++;
				}
			}

		// Assert.assertNotNull(element);

		return element;

	}
	/**
	 * tryForWait ������������
	 * @author xian_crazy QQ��330126160
	 * @version 2014��11��25��  ����10:07:10
	 * @see
	 */
	class TryForWaitReturn{
	 private	boolean t;
	 private	WebElement el;
	public boolean getT() {
		return t;
	}
	public void setT(boolean t) {
		this.t = t;
	}
	public WebElement getEl() {
		return el;
	}
	public void setEl(WebElement el) {
		this.el = el;
	}
	}
	
	

	/**
	 * �ж�ҳ��Ԫ���Ƿ��ڵ�ǰ��ͼ��ʾ
	 * @see
	 * @param element
	 * @param t
	 * @param bool
	 * @param i
	 * @param waitTime
	 * @return
	 */
	private TryForWaitReturn tryForWait(WebElement element, boolean t,boolean bool,int i,int waitTime) {
		int w = element.getSize().getWidth();
		int h=element.getSize().getHeight();

		TryForWaitReturn ret=new TryForWaitReturn();
		

 
		//Ԫ�ؿ�ȴ����� ���� bool=true ��Ԫ����Ҫ�ڵ�ǰ��ͼ��ʾ
		if (w > 0 && bool) {
			t = false;
			// this.pt(" get it��");
		}
		//Ԫ�ؿ�ȴ��ڻ��ߵ����� ���� bool=false ��Ԫ�ز�һ���ڵ�ǰ��ͼ��ʾ
		if (w >= 0 && !bool) {
			t = false;
			
			// this.pt(" get it��");
			if (w == 0) {
				this.pt("�ҵ�Ԫ�ص�δ�ڵ�ǰ��ͼ��ʾ");
			}
		}

		//t=falseʱ��˵��Ԫ�ض�λ�������ӡ������Ϣ
		if(!t){
			this.pt(" get its H&&W :" +h  + "&&" + w);
			this.pt(" get its point :" + element.getLocation().getX() + "&&" + element.getLocation().getY());
			ret.setT(t);
			ret.setEl(element);
			return ret;
		//t=trueʱ��˵��Ԫ���ҵ���w=0���� bool=true��Ҫ�ȴ����ڵ�ǰ��ͼ��ʾ����,�����ʱ����Ϊδ�ҵ�
		}else{
			
			if (catchForWait(i, waitTime,true)) {
				t = false;	
				element=null;
				ret.setT(t);
				ret.setEl(element);
			}
		
			return ret;
		}
		
	}

	/**
	 * 
	 * @see
	 * @param i
	 * @param waitTime
	 * @param isfindel   true ��ʾ�Ѿ��ҵ�Ԫ��  false ��ʾû���ҵ�Ԫ��
	 * @return
	 */
	private boolean catchForWait(int i, int waitTime, boolean isfindel) {
		if (i == 0) {
			this.pt("�ȴ�" + waitTime + "�롭��" );
			System.out.print(i);
		} else if (i == waitTime) {
			System.out.print(" " + i);
			if(!isfindel){
				this.pt(i+"��󣬶�λʧ��");
			}else{
				this.pt(i+"����Ѿ��ҵ���������Ϊ0");
			}
			
			return true;
		} else {
			System.out.print(" " + i);
		}
		
		this.threadSleep(1);
		return false;
	}

	/**
	 * 
	 * @param uiSelector
	 *            ѡ�������ΪList
	 * @param waitTime
	 *            ��
	 * @waitElement_S_appear appear˵����ǰԪ���ڵ�ǰҳ��ͨ��ѡ���������ҵ�
	 * @param bool  true ��ʾԪ�صĿ����0  false ��ʾ����Ԫ�ص��ǲ�һ���ڵ�ǰ��ͼ
	 */
	
	
	
	public List<WebElement> waitThenGetElement_S(String strSelector, int waitTime, SelectorType type,boolean bool) {
		List<WebElement> list = null;
		int i = 0;

			boolean t1 = true;
			while (t1) {
				try {
					
					switch (type) {
					case UiSelector:
					list = ((AndroidDriver) dr).findElementsByAndroidUIAutomator(strSelector);
					break;
					case xPath:
						
						list = dr.findElements(By.xpath(strSelector));
						
						break;
					default:
						this.pt("waitThenElement_S_appear���ʹ���");
					}
				
					WebElement element = list.get(0);
					TryForWaitReturn ret=tryForWait(element, t1,bool,i,waitTime);;
					t1=ret.getT();
					
					
				} catch (Throwable e) {
					if (catchForWait(i, waitTime,false)) {
						break;
					}
				} finally {
					i++;
				}
			}	
	
		return list;
	}

	private WebElement getElBySelectorInDB(String tablenameindb, String elementnameindb, SelectorType type, int timeout,boolean bool){
		
		
		WebElement webElement = null;
			this.pt("========"+ tablenameindb+ "  "+elementnameindb + "========��ʼ��λ===============================");
			String selector = this.getselectorFromdb(tablenameindb, elementnameindb, type).trim();
			this.pt("��λ��Ԫ��"+type+"Ϊ��" + selector);
			webElement = this.waitThenGetElement(selector, timeout, type,bool);
			this.pt("========== "+elementnameindb + "============��λ���===============================");
		return webElement;
	}
	
	
	
	
	
	public WebElement getElement(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getElBySelectorInDB(tablenameindb, elementnameindb, type, timeout, true);
	}

	public WebElement findElement(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getElBySelectorInDB(tablenameindb, elementnameindb, type, timeout, false);
	}

	public WebElement getWebElementByReplaceSelectorOfindb(String tablenameindb, String elementnameindb, SelectorType type, String valueOfvar, int timeout) {
		String newselector = this.getselectorFromdb(tablenameindb, elementnameindb, type).replace("%var%", valueOfvar);
		return this.getElementBynewselctor(elementnameindb, type, newselector, timeout);
	}
	public List<WebElement> getWebElementsByReplaceSelectorOfindb(String tablenameindb, String elementnameindb, SelectorType type, String valueOfvar, int timeout) {
		String newselector = this.getselectorFromdb(tablenameindb, elementnameindb, type).replace("%var%", valueOfvar);
		return this.getElementsBynewselctor(elementnameindb, type, newselector, timeout);
	}

	/**
	 * 
	 * @see
	 * @param elementname  �Զ����Ԫ������ֻ��Ϊlog������ʹ�ã���ʵ������
	 * @param type
	 * @param newselctor  �����б�д�µ�selector
	 * @param timeout
	 * @return
	 */
	public WebElement getElementBynewselctor(String elementname, SelectorType type, String newselctor, int timeout) {
		WebElement webElement = null;
		// int timeout = 50;
		String selector = newselctor;
		this.pt("========" + elementname + "========��ʼ��λ===============================");
		this.pt("��λ��Ԫ��new  "+type+"Ϊ��" + selector);
		webElement = this.waitThenGetElement(selector, timeout, type,true);
		this.pt("============" + elementname + "==========��λ���===============================");
		return webElement;
	}
	
	/**
	 * 
	 * @see
	 * @param elementname  �Զ����Ԫ������ֻ��Ϊlog������ʹ�ã���ʵ������
	 * @param type
	 * @param newselctor  �����б�д�µ�selector
	 * @param timeout
	 * @return
	 */
	public List<WebElement> getElementsBynewselctor(String elementname, SelectorType type, String newselctor, int timeout) {
		List<WebElement> list = null;
		// int timeout = 50;
		String selector = newselctor;
		this.pt("========" + elementname + "========��ʼ��λ===============================");
		this.pt("��λ��Ԫ�ؼ�new  "+type+"Ϊ��" + selector);
		list = this.waitThenGetElement_S(selector, timeout, type,true);
		this.pt("============" + elementname + "==========��λ���===============================");
		return list;
	}
	
	
	
	private List<WebElement> getEl_s_BySelectorInDB(String tablenameindb, String elementnameindb, SelectorType type, int timeout,boolean bool){
		
		String selector = this.getselectorFromdb(tablenameindb, elementnameindb, type).trim();
		List<WebElement> list = null;
			this.pt("========" + elementnameindb + "========��ʼ��λ===============================");
			this.pt("��λ��Ԫ�ؼ�"+type+"Ϊ��" + selector);
			list = this.waitThenGetElement_S(selector, timeout, type,bool);
			this.pt("===========" + elementnameindb + "===========��λ���===============================");
		return list;
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
	public List<WebElement> getElements(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return this.getEl_s_BySelectorInDB(tablenameindb, elementnameindb, type, timeout, true);
	}

	/**
	 * 
	 * 
	 * @param uiSelector
	 */
	public List<WebElement> getElementsBynewSelector(String elementname, SelectorType type, String newselector, int timeout) {

		// int timeout = 50;
		String selector = newselector;
		List<WebElement> list = null;
			this.pt("========" + elementname + "========��ʼ��λ===============================");
			this.pt("��λ��Ԫ��new "+type+"Ϊ��" + selector);
			list = this.waitThenGetElement_S(selector, timeout, type,true);
			this.pt("===========" + elementname + "===========��λ���===============================");
		

		return list;

	}

	// =====================================================================================

	/**
	 * ��������ַ���
	 * 
	 * @param length
	 *            ������ֳ���
	 * @param type
	 *            ����1Ϊ ������ ������1 ����+��ĸ
	 * @return �����ַ���
	 */
	public String getRandomString(int length, int type) { // length��ʾ�����ַ����ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		String base2 = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		if (type == 1) {
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base2.length());
				sb.append(base2.charAt(number));
			}
		} else {
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
		}
		this.pt("��ȡ����" + length + "λ�ַ���Ϊ�� " + sb.toString());
		return sb.toString();
	}

	/**
	 * ��ʼ������Ŀ¼table
	 * 
	 * @param filename
	 *            filename<br/>
	 *            ��������򲻴������������򴴽�
	 * 
	 */
	public void setTable(String filename) {

		new ExcelByjxlTool("\\testData\\" + filename);
	}

	/**
	 * �ȴ��̶�ʱ�䣬�����ȴ������ӡ������̨
	 * 
	 * @param waitTime
	 */
	public void waitAndPrintTime(int waitTime) {
		System.out.print("���ȴ�" + waitTime + "��  ��ʼ�ȴ���");
		for (int i = 1; i <= waitTime; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(i + "s  ");
		}
		this.pt("�ȴ�����");
	}

	/**
	 * Ԫ��ѡ��������uiSelector, xPath;
	 * 
	 * @author Administrator
	 * 
	 */
	public enum SelectorType {
		UiSelector, xPath, CSS;
	}

	/**
	 * ����̨��log�ļ���ӡ����
	 * 
	 */
	public void pt(String str) {
		//System.out.println(str);
		log.info(str);
	}
	
	/**
	 * testngReporter��ӡ����
	 * ����̨��log�ļ���ӡ����
	 */
	public void rpt(String str) {
		//System.out.println(str);
		log.info2logAndReporter(str);
	}

	/**
	 * ������������ַ�
	 * 
	 * @param i
	 *            �ַ�����
	 * @return
	 * @throws Exception
	 */
	public String createname(int i) {
		String str = "";
		int hightPos, lowPos; // ����ߵ�λ
		Random random = new Random();
		for (int j = 1; j <= i; j++) {
			hightPos = (176 + Math.abs(random.nextInt(39)));// ��ȡ��λֵ
			lowPos = (161 + Math.abs(random.nextInt(93)));// ��ȡ��λֵ
			byte[] b = new byte[2];
			b[0] = (new Integer(hightPos).byteValue());
			b[1] = (new Integer(lowPos).byteValue());
			try {
				str = str + new String(b, "GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// ת������
		}
		// System.err.println(str);
		return str;
	}

	/**
	 * ����ָ����Χ���������
	 */
	public int getRandomInt(int min, int max) {
		Random random = new Random();
		if (min == max) {
			return min;
		} else {
			return random.nextInt(max) % (max - min + 1) + min;
			// this.pt(s);
		}
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @param i
	 *            1 MM-dd HH:mm:ss <br/>
	 *            2 yy-MM-dd HH:mm <br/>
	 *            3 yy-MM-dd <br/>
	 *            other: yy-MM-dd HH:mm��ss
	 * @return ���ظ�ʽ���õ��ַ���
	 */
	public String nowTime(int i) {
		String result;
		SimpleDateFormat df;
		switch (i) {
		case 1:
			df = new SimpleDateFormat("MM-dd HH:mm:ss");// �������ڸ�ʽ
			result = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			break;
		case 2:
			df = new SimpleDateFormat("yy-MM-dd HH:mm");// �������ڸ�ʽ
			result = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			break;
		case 3:
			df = new SimpleDateFormat("yy-MM-dd");// �������ڸ�ʽ
			result = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			break;
		default:
			df = new SimpleDateFormat("yy-MM-dd HH:mm��ss");// �������ڸ�ʽ
			result = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��

		}

		return result;

	}

	/**
	 * driver sleep secends s��λΪ��
	 */
	public void threadSleep(int s) {
		int sec = s * 1000;
		try {
			Thread.sleep(sec);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			this.pt("Thread.sleep�쳣");
		}
	}

	/**
	 * ��ͼ
	 * 
	 * @param driver
	 */
	public void screenShot(WebDriver driver) {
//		String dir_name = "screenshot";
//		String dir_name=Constants.getDefaultValueFor(Constants.PROP_OUTPUT_DIR)+ File.separator+"screenshot";
//		String dir_name=HTMLReporter.getREPORT_DIRECTORY()+ File.separator+"screenshot";
		
		@SuppressWarnings("deprecation")
		String dir_name=TestNG.getDefault().getOutputDirectory()+ File.separator+"screenshot";
		
		
		if (!(new File(dir_name).isDirectory())) {
			new File(dir_name).mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date());
		try {
			File source_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // ִ����Ļ��ͼ
			
			String imgurl=dir_name + File.separator + time + ".png";
			FileUtils.copyFile(source_file, new File(imgurl));
			
			Reporter.log("<a href=..\\screenshot\\"+time+".png target=_blank>ʧ�ܽ�ͼ</a>");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * cmd����
	 */
	public void cmd( String commands) {

	
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(commands);
			p.waitFor();
		} catch (Exception e) {

			e.printStackTrace();
			// super.pt(p.toString());
		} finally {

		}

	}

	/**
	 * 
	 * @see
	 * @param filename
	 * @return System.getProperty("user.dir") + "\\src\\test\\resources\\" + filename;
	 */
	public String getResourcesURL(String filename) {
		return System.getProperty("user.dir") + "\\src\\test\\resources\\" + filename;
	}

	/**
	 * �����ݿ��ж�ȡelement��ѡ�����ַ���
	 * 
	 * @param tablenameindb
	 * @param elementnameindb
	 * @param type
	 * @return
	 */
	public String getselectorFromdb(String tablenameindb, String elementnameindb, SelectorType type) {		
		return this.dbtool.getValuebyKey(type.toString(), tablenameindb, "elementname", elementnameindb);
	
	}

}
