package crazy.tools;

import java.util.Collections;
import java.util.List;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.collections.Lists;

import crazy.seleiumTools.WebTestPro;

/**
 * @author xian_crazy QQ：330126160
 * @version 2015年1月19日  下午2:15:18
 */
public class ScreenshotListener extends TestListenerAdapter{
	 private List<ITestNGMethod> m_allTestMethods =
		      Collections.synchronizedList(Lists.<ITestNGMethod>newArrayList());
		 	  private List<ITestResult> m_failedTests = Collections.synchronizedList(Lists.<ITestResult>newArrayList());
	

	  public void onTestFailure(ITestResult tr) {
	    m_allTestMethods.add(tr.getMethod());
	    m_failedTests.add(tr);
	    new BasicTools().screenShot(WebTestPro.getDriver());
	  }

}
