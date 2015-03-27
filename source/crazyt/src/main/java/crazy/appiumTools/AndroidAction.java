package crazy.appiumTools;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30�� ����10:16:24
 * @see
 */
public class AndroidAction {
	private AppiumDriver dr = null;
	private int h;

	private int w;

	public AndroidAction(AppiumDriver driver) {
		this.dr = driver;
		h = this.dr.manage().window().getSize().height;
		w = this.dr.manage().window().getSize().width;
	

	}

	public int getH() {
		return h;
	}

	/**
	 * ���Ͻ�Ϊ����ԭ��
	 * 
	 * @return ������Ļ���
	 */
	public int getH2() {
		return (int) (h * 0.5);
	}
	
	public int getW2() {
		return (int) (w * 0.5);
	}

	public int getW() {
		return w;
	}

	public void swipeToLeft() {
		try {
		dr.swipe(400, 400, 100, 400, 100);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("��ǰԪ���Ѿ��������Ҷ�");
		}

	}

	/**
	 * ���ݴ����Ԫ�أ���ȡ������������󻬶�
	 */
	public void swipeToLeft(AndroidElement ael) {

		
		Dimension d = ael.getSize();
		int px = ael.getLocation().getX();
		int py = ael.getLocation().getY();
		int startx = (int) (px + d.width * 0.8);
		int starty = (int) (py + d.height * 0.5);
		int endx = (int) (px + d.width * 0.1);
		int endy = (int) (py + d.height * 0.5);
		int duration = 200;
		try {
		dr.swipe(startx, starty, endx, endy, duration);
	
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("��ǰԪ���Ѿ��������Ҷ�");
		}
	}

	/**
	 * ���ݴ����Ԫ�أ���ȡ������������¿��ٻ���
	 */
	public void swipeToDown(AndroidElement ael, int speedByduration) {


		Dimension d = ael.getSize();
		int px = ael.getLocation().getX();
		int py = ael.getLocation().getY();
		int startx = (int) (px + d.width * 0.5);
		int starty = (int) (py + d.height * 0.20);
		int endx = startx;
		int endy = (int) (py + d.height * 0.80);
		
		System.out.println("swipeToDown:startx="+startx+" starty="+ starty+" endx="+ endx+" endy="+ endy);

		try {
		dr.swipe(startx, starty, endx, endy, speedByduration);
		
			Thread.sleep(2000);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println("��ǰԪ���Ѿ��������");
		}

	}
	
	public void open_notification(){
		try{
		this.dr.swipe(getW2(), 10, getW2(), getH2(), 100);
		}catch(Throwable e){
			System.out.println("��֪ͨ�� API17��������");
		}
	}

	

	/**
	 * ���ݴ����Ԫ�أ�touchԪ������
	 */
	public void tapOneTime(AndroidElement ael) {



		TouchAction ta = new TouchAction(this.dr);
	
		Point aelp = ael.getCenter();

		dr.performTouchAction(ta.tap(aelp.getX(), aelp.getY()));
		
	}

	/**
	 * �������һ���
	 * 
	 * @param driver
	 */
	public void swipeToRight() {

		dr.swipe(100, 400, 400, 400, 500);
	}

	/**
	 * �������ϻ���
	 * 
	 * @param driver
	 */
	public void swipeToUp() {

		dr.swipe(200, 500, 400, 400, 500);
	}
}
