package crazy.seleiumTools;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;

import crazy.tools.Dbtool;

/**
 * @author xian_crazy QQ：330126160
 * @version 2014年12月5日  下午1:43:29
 * @see
 */
public class PageFactory {
	private WebDriver driver;
	private Dbtool dbtool;

	public PageFactory(WebDriver driver, Dbtool dbtool) {
		this.dbtool = dbtool;
		this.driver = driver;
		this.pageFactory();
	}

	public void pageFactory() {
		/**
		 * 通过反射自动循环实例化所有public 页面
		 */
		Field[] f = this.getClass().getFields();  //？ 1
		for (int i = 0; i < f.length; i++) {//？ 2
			try {
				f[i].set(    //？ 3
						this,
						Class.forName(f[i].getType().getName())//？ 4
								.getConstructor(WebDriver.class, Dbtool.class)//？5
								.newInstance(driver, dbtool));		//？	6
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
	}
}
