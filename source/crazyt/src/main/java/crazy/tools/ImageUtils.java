package crazy.tools;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * ͼƬ���������࣬����Ļ��ͼ���ϲ�ͼƬ��
 * 
 * @author Tom
 */
public class ImageUtils {
	private static final GraphicsDevice graphicsDevice = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private static final String imgType = "png";
	private static Robot awtRobot;
	static {
		try {
			awtRobot = new Robot(graphicsDevice);
		} catch (AWTException e) {

		}
	}

	/**
	 * ��ȡ��������Ľ�ͼ
	 * 
	 * @param destFile
	 *            ͼƬ���浽���ļ���
	 * @return destFile����
	 */
	public static File captureImage(File destFile) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		return captureImage(destFile, rectangle);
	}

	/**
	 * ��ȡָ������Ľ�ͼ
	 * 
	 * @param destFile
	 *            ͼƬ���浽���ļ���
	 * @param x
	 *            ��ͼ��ʼ��λ�ú�����
	 * @param y
	 *            ��ͼ��ʼ��λ��������
	 * @param width
	 *            ͼƬ���
	 * @param height
	 *            ͼƬ�߶�
	 * @return destFile����
	 */
	public static File captureImage(File destFile, int x, int y, int width,
			int height) {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		return captureImage(destFile, rectangle);
	}

	/**
	 * ��ȡָ������Ľ�ͼ
	 * 
	 * @param destFile
	 *            ͼƬ���浽���ļ���
	 * @param rect
	 *            ��ͼ�������
	 * @return destFile����
	 */
	public static File captureImage(File destFile, Rectangle rectangle) {
		try {
			BufferedImage desktopImg = awtRobot.createScreenCapture(rectangle);
			ImageIO.write(desktopImg, imgType, destFile);
			return destFile;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * ������ͼƬ�ϲ���һ��ͼƬ������ϲ�
	 * 
	 * @param imgs
	 *            ͼƬԴ
	 * @param destFile
	 *            ͼƬ���浽���ļ���
	 */
	public static void mergeImages(List<File> imgList, File destFile) {
		if (imgList == null || imgList.size() < 1) {
			throw new RuntimeException("������ļ�����Ϊ��");
		}
		int length = imgList.size();
		BufferedImage[] images = new BufferedImage[length];
		int[][] imgArrays = new int[length][];
		for (int i = 0; i < length; i++) {
			try {
				images[i] = ImageIO.read(imgList.get(i));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			imgArrays[i] = new int[width * height];// ��ͼƬ�ж�ȡRGB
			imgArrays[i] = images[i].getRGB(0, 0, width, height, imgArrays[i],
					0, width);
		}
		// ������ȡ��߶�
		int destHeight = 0;
		int destWidth = images[0].getWidth();
		for (int i = 0; i < images.length; i++) {
			destWidth = destWidth > images[i].getWidth() ? destWidth
					: images[i].getWidth();
			destHeight += images[i].getHeight();
		}

		if (destHeight < 1) {
			throw new RuntimeException("ͼƬ�ܸ߶�С��1���޷�����ͼƬ��");
		}

		// ������ͼƬ
		try {
			BufferedImage imgNew = new BufferedImage(destWidth, destHeight,
					BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			for (int i = 0; i < images.length; i++) {
				imgNew.setRGB(0, height_i, images[i].getWidth(),
						images[i].getHeight(), imgArrays[i], 0,
						images[i].getWidth());
				height_i += images[i].getHeight();
			}
			ImageIO.write(imgNew, imgType, destFile);// дͼƬ
		} catch (Exception ex) {
			throw new RuntimeException("�ϲ�ͼƬ����", ex);
		}
	}

	/**
	 * ������ͼƬ�ϲ���һ��ͼƬ������ϲ�
	 * 
	 * @param imgs
	 *            ͼƬԴ
	 * @param destFile
	 *            ���ɵ���ͼƬ
	 */
	public static void mergeImages(File[] imgArr, File destFile) {
		if (imgArr == null || imgArr.length < 1) {
			throw new RuntimeException("������ļ�����Ϊ��");
		}
		mergeImages(Arrays.asList(imgArr), destFile);
	}

	/**
	 * ʹ��ʾ��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ��һ��ͼ
		File file1 = captureImage(new File("d:\\abc1.png"), 0, 0, 300, 200);
		// �ٽ�һ��ͼ
		File file2 = captureImage(new File("d:\\abc2.png"), 0, 200, 350, 200);
		// �ϲ�ͼƬ
		mergeImages(new File[] { file1, file2 }, new File("d:\\abc3.png"));
	}

}

