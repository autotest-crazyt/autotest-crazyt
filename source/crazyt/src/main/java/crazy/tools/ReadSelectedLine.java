package crazy.tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.testng.annotations.Test;

import crazy.seleiumTools.ProfilesIni;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��6��  ����11:15:04
 * @see
 */
/** 
 * ��ȡ�ļ�ָ���С� 
 */
public class ReadSelectedLine {
//	 ��ȡ�ļ�ָ���С� 
	static void readAppointedLineNumber(File sourceFile, int lineNumber) throws IOException {
		FileReader in = new FileReader(sourceFile);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		
		if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
			System.out.println("�����ļ���������Χ֮�ڡ�");
		}

		{
		while (s != null) {
			System.out.println("��ǰ�к�Ϊ:" + reader.getLineNumber());
			
			System.out.println(s);
			System.exit(0);
			s = reader.readLine();
		}
		}
		reader.close();
		in.close();
	}

	// �ļ����ݵ��������� 
	static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}
	@Test
	public  void test() throws IOException {
		
		


		
		
		System.setProperty("webdriver.firefox.bin", "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	
	
		
		ProfilesIni allProfiles = new ProfilesIni();

		System.out.println(allProfiles.getNameOfpro());
		
        // ��ȡ�ļ� 
		File sourceFile = allProfiles.getProfiles_ini();
        // ��ȡ�ļ������ݵ������� 
		int totalNo = getTotalLines(sourceFile);
		System.out.println("There are "+totalNo+ " lines in the text!");
		
		// ָ����ȡ���к� 
		int lineNumber = 2;
		
		// ��ȡָ������ 
		readAppointedLineNumber(sourceFile, lineNumber);
		
		
		
	}

	
}
