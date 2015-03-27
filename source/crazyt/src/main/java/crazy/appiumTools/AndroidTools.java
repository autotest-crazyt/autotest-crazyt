package crazy.appiumTools;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import crazy.tools.BasicTools;
import crazy.tools.Dbtool;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30�� ����10:16:40
 * @see
 */
public class AndroidTools extends BasicTools {

	private AndroidDriver dr = null;
	private Adbtools adbtool = new Adbtools();

	public AndroidTools(AndroidDriver driver, Dbtool dbtool) {
		super(driver, dbtool);
		this.dr = driver;
	}

	public AndroidElement getAndroidElement(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {
		return (AndroidElement) super.getElement(tablenameindb, elementnameindb, type, timeout);
	}

	public AndroidAction getAndroidAction() {
		return new AndroidAction(this.dr);

	}

	public List<AndroidElement> getAndroidElements(String tablenameindb, String elementnameindb, SelectorType type, int timeout) {

		List<WebElement> list = super.getElements(tablenameindb, elementnameindb, type, timeout);
		List<AndroidElement> alist = new ArrayList<AndroidElement>();
		Iterator<WebElement> iterator = list.iterator();
		while (iterator.hasNext()) {
			alist.add((AndroidElement) iterator.next());
		}
		return alist;

	}

	public void adbinuttext(AndroidElement ael, String str) {

		this.adbtool.adbinputtext(this.dr, ael, str);
	}

	/**
	 * ��ȡList�е����AndroidElement i��ʾlist�������Χ��ʼ�±�ֵ
	 */
	public AndroidElement getRandomAndroidElementFromList(List<AndroidElement> list, int i) {
		System.out.println("list.size = " + list.size());
		int j = list.size() - 1;
		int r = super.getRandomInt(i, j);
		return list.get(r);
	}

	/**
	 * ���ݴ����Ԫ�أ���ȡ������� �����ϻ�������������ǰҳ���һ��Ԫ�ػ�����������
	 */
	public void swipeLastChildELTo1st(AndroidElement ael) {

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		int px = ael.getLocation().getX();
//		int py = ael.getLocation().getY();
		String xpath = super.getXpathOfElement(ael) + "/descendant::*";
		AndroidElement childael = (AndroidElement) super.getElementsBynewSelector("��Ԫ�ض�λ   in  swipeLastChildELTo1st", SelectorType.xPath, xpath, 10).get(0);

		Point p = childael.getCenter();
		Dimension d = ael.getSize();
		Dimension ds = childael.getSize();
		int startx = (int) (p.getX());
		int starty = (int) (d.getHeight() + p.getY() - ds.getHeight());
		int endx = startx;
		int endy = (int) (p.getY());
		super.pt("swipeLastChildELTo1st:  startx=" + startx + " starty=" + starty + " endx=" + endx + " endy=" + endy);
		//super.pt("swipeLastChildELTo1st:start");
		try {
			dr.swipe(startx, starty, endx, endy, 3000);
		} catch (Throwable e) {
			super.pt("��ǰԪ���Ѿ���������׶�");
		}
		super.threadSleep(1);
		//super.pt("swipeLastChildELTo1st:end");
	}

	/**
	 * �������ϻ���listElement���ҵ���Element
	 */
	public AndroidElement getELByitsTextfromParentELInSwip(AndroidElement ael, String TextOfchildEL) {
		super.threadSleep(1);
		this.getAndroidAction().swipeToDown(ael, 100);
		AndroidElement cael = null;
		String selector = super.getXpathOfElement(ael) + "/descendant::*";
		while (cael == null) {
			Iterator<WebElement> itr = super.getElementsBynewSelector("��Ԫ�ض�λ", SelectorType.xPath, selector, 10).iterator();

			while (itr.hasNext()) {
				cael = (AndroidElement) itr.next();
				// super.pt("cael.getText()="+cael.getText());
				if (cael.getText().equals(TextOfchildEL)) {
					super.pt("�Ѷ�λ��" + cael.getText());
					break;
				}
				cael = null;
			}
			if (cael == null) {
				this.swipeLastChildELTo1st(ael);
			}
		}

		return cael;

	}

	// ==========================================================================================

}
