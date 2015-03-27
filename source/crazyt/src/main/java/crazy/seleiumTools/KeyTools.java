package crazy.seleiumTools;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.testng.annotations.Test;
/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30��  ����10:17:50
 * @see
 */
public class KeyTools {

	/**
	 * @param args
	 *            <A href="http://home.51cto.com/index.php?s=/space/2305405"
	 *            target=_blank>@throws</A> AWTException
	 */
	@Test
	public void test() throws Exception {
		// TODO Auto-generated method stub
		Robot robot = new Robot(); // ����һ��robot����
		Runtime.getRuntime().exec("notepad");// ��һ�����±�����
		robot.delay(2000);// �ȴ� 2��
		// �������
		keyPressWithAlt(robot, KeyEvent.VK_SPACE); // ���� alt+ �ո�
		keyPress(robot, KeyEvent.VK_X);// ����x��
		robot.delay(1000);// �ȴ� 1��
		keyPressString(robot, "��Һã�����һ��С�����ˣ����кܶ౾���� ��"); // �����ַ���
		robot.delay(3000);// �ȴ� 3��
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		keyPressString(robot, "���ڣ��Ҿ�����չʾһ��.....����"); // �����ַ���
		robot.delay(3000);// �ȴ� 3��
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		keyPressString(robot, "���ȣ����ܰ��� ���̵��κ�һ����������,�ҵ�������a,b,c,d��"); // �����ַ���
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		robot.delay(3000);// �ȴ� 3��
		keyPress(robot, KeyEvent.VK_A); // ���� a ��
		robot.delay(2000);// �ȴ� 2��
		keyPress(robot, KeyEvent.VK_B); // ���� b ��
		robot.delay(2000);// �ȴ� 2��
		keyPress(robot, KeyEvent.VK_C); // ���� c ��
		robot.delay(2000);// �ȴ� 2��
		keyPress(robot, KeyEvent.VK_D); // ���� d ��
		robot.delay(2000);// �ȴ� 2��
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		keyPressString(robot, "�Ǻǣ�����ˡ�������");
		robot.delay(3000);// �ȴ� 3��
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		keyPressString(robot, "������������ ���ֺܶ��ǲ��� �е� �����أ����������� �������һ�� ");
		robot.delay(2000);// �ȴ� 2��
		keyPressWithCtrl(robot, KeyEvent.VK_A); // ���� ctrl+A ȫѡ
		robot.delay(2000);// �ȴ� 2��
		keyPress(robot, KeyEvent.VK_DELETE); // ���
		robot.delay(3000);// �ȴ� 3��
		keyPressString(robot, "�������� �ǲ��� ���� ��ˬ�������� �һ��ᰴ ��ϼ��� ...");
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		robot.delay(3000);// �ȴ� 3��
		keyPressString(robot, "................�����Ѿ� ��ʾ���� �� ���Ǻ�");
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		robot.delay(3000);// �ȴ� 3��
		keyPressString(robot, "��ʵ���һ��кܶ౾�������ھͲ�����չʾ�� .....");
		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����
		robot.delay(3000);// �ȴ� 3��
		keyPressString(robot, "лл��ң���������");

	}

	// shift+ ����
	public static void keyPressWithShift(Robot r, int key) {
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(key);
		r.keyRelease(key);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.delay(100);
	}

	// ctrl+ ����
	public static void keyPressWithCtrl(Robot r, int key) {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(key);
		r.keyRelease(key);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.delay(100);
	}

	// alt+ ����
	public static void keyPressWithAlt(Robot r, int key) {
		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(key);
		r.keyRelease(key);
		r.keyRelease(KeyEvent.VK_ALT);
		r.delay(100);
	}

	// ��ӡ���ַ���
	public static void keyPressString(Robot r, String str) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();// ��ȡ���а�
		Transferable tText = new StringSelection(str);
		clip.setContents(tText, null); // ���ü��а�����
		keyPressWithCtrl(r, KeyEvent.VK_V);// ճ��
		r.delay(100);
	}

	// ���� ����
	public static void keyPress(Robot r, int key) {
		r.keyPress(key);
		r.keyRelease(key);
		r.delay(100);
	}
	
	public static void mouseMovetoTopCenterAndClickleft(Robot r){
		r.mouseMove(10, 500);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(500);
	}
}