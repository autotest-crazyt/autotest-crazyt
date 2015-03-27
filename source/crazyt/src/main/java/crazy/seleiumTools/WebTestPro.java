package crazy.seleiumTools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import crazy.tools.BasicTools;
import crazy.tools.Dbtool;

/**
 * @author Administrator
 * @param <T>
 * 
 * @see ����public�������£�<br/>
 * 
 *      public WebDriver driver;<br/>
 *      public Dbtool dbtool;<br/>
 *      public Tools tool;<br/>
 */
public class WebTestPro {

	private static String homeurl=null;
	
	
	public static BrowsersDriverPro driverPro=new BrowsersDriverPro();

	private static WebDriver driver=null;  //driver
	public static Dbtool dbtool=new Dbtool();//���ݿ�����
	
	
	
	private static Class <?> pageclass=null;
	public static BasicTools btool = new BasicTools();//ͨ�ù���
	
	
	


	private static  Dbtool newDbtool(){
	
		dbtool=dbtool.getConnection();
		return dbtool;
	}
	
	private static WebDriver newDriver(int pageLoadTimeout) {	
		
		WebTestPro.driver = driverPro.getDriver();
		BrowsersDriverPro.setPageLoadTimeout(pageLoadTimeout);
		WebTestPro.driver.manage().timeouts()
				.pageLoadTimeout(BrowsersDriverPro.getPageLoadTimeout(), TimeUnit.SECONDS);
		try {
			WebTestPro.driver.get(homeurl);
		} catch (TimeoutException e) {
			 btool.rpt(WebTestPro.driver.getTitle() + "  ҳ����س���"
					+ BrowsersDriverPro.getPageLoadTimeout() + "��  ���ȴ�������� ����ִ��");
		}
		BrowersSize.maxBrowers(driver);
		return driver;
	}
	
	
	
	private static   <T>T newPf(Class<T> mpageclass){	
		T mypage = null;
		try {
			mypage = mpageclass.getConstructor(WebDriver.class,Dbtool.class)
						.newInstance(WebTestPro.getDriver(),WebTestPro.getDbtool());
		} catch (Exception e) {
	
			e.printStackTrace();
		} 
		return  mypage;
	}
	


	
	public WebTestPro quitdriver(int endWaitTime) {
		WebTestPro.btool.threadSleep(endWaitTime);
		try {
			WebTestPro.btool.cmd("taskkill /F /im plugin-container.exe");
			driver.quit();
			driver=null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// this.tool.cmd("taskkill /F /IM IEDriverServer_64_ie8.exe");
		return this;
	}
	
	public WebTestPro closedbtool() {		
			dbtool.close();
			dbtool=null;
			return this;
	}
	
	

	

	public static void setHomeurl(String homeurl) {
		WebTestPro.homeurl = homeurl;
	}


	public static WebDriver getDriver() {
		if(driver==null){
			driver=newDriver(120);
		}
		return driver;
	}

	public static Dbtool getDbtool() {
		if(dbtool.getConn()==null||dbtool.getConnisClosed()){
			dbtool=newDbtool();
		}

		return dbtool;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T>T getPf() {

		return (T) newPf(WebTestPro.pageclass);
		
	}

	public static void setPfClass(Class <?> pageClass) {
		WebTestPro.pageclass=pageClass;

	}


	
	


}
