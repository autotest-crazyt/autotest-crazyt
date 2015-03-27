package crazy.tools;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import crazy.seleiumTools.KeyTools;
import crazy.seleiumTools.WebTools;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30��  ����10:18:36
 * @see
 */
public class ExcelByjxlTool {
	private File classpathRoot;
	public File exlFile;
	private WritableWorkbook writablebook;
	private String sheetname;
	private Workbook wb;
	private WritableSheet writablesheet;
	public Sheet sheet;

	/**
	 * 
	 * @param newfilename
	 *            ���·��<br/>
	 *            ������ļ������ڽ��������ļ���������һ����Ϊcrazysheet��Sheet
	 */
	public ExcelByjxlTool(String newfilename) {
		classpathRoot = new File(System.getProperty("user.dir"));
		this.exlFile = new File(classpathRoot, newfilename);
		System.out.println("ExcelByjxlTool(String newfilename) ��Ŀ���ļ�Ϊ"+this.exlFile.toString());
		if (!this.exlFile.exists()) {
			System.out.println("�ļ������ڣ����ڴ�������");
			this.creatExcel("crazysheet");
		}
	}

	/**
	 * 
	 * @param file
	 *            �Ѿ����ڵ�excel
	 */
	public ExcelByjxlTool(File file) {
		// classpathRoot = new File(System.getProperty("user.dir"));
		// this.exlFile = new File(classpathRoot, "\\testData\\test.xls");
		this.exlFile = file;
	}

	public ExcelByjxlTool(File file, String sheetname) {
		// classpathRoot = new File(System.getProperty("user.dir"));
		// this.exlFile = new File(classpathRoot, "\\testData\\test.xls");
		this.exlFile = file;
		this.sheetname = sheetname;
	}

	/**
	 * 
	 * @param file
	 *            Ҫ���������ļ�ȫ·��<br/>
	 *            ����crazysheet
	 */
	private void creatExcel(String sheetname) {

		try {
			// ���ļ�
			System.out.println(this.exlFile);
			this.writablebook = Workbook.createWorkbook(this.exlFile);
			// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ

			this.writablebook.createSheet(sheetname, 0);

			this.writablebook.write();
			this.writablebook.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @param sheetName
	 *            ��Ҫ��ȡ��sheet��
	 * @param c
	 *            ��ĸ��ʾ���к�
	 * @param r
	 *            ���ֱ�ʾ���кŴ�1��ʼ
	 * @return ��Ԫ������
	 */
	public String readExcelcell(String sheetName, String c, int r) {
		String result = null;
		try {
			wb = Workbook.getWorkbook(this.exlFile);
			// ��õ�һ�����������
			// Sheet sheet = book.getSheet( 0 );
			Sheet sheet = this.wb.getSheet(sheetName);
			// �õ���һ�е�һ�еĵ�Ԫ��
			Cell cell1 = sheet.getCell(
					ExcelColExchangeNum.convertLetterToNum(c), r - 1);
			result = cell1.getContents();
			System.out.println(result);
			wb.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 
	 * @param sheetName
	 *            ��Ҫ��ȡ��sheet��
	 * @param c
	 *            ��ĸ��ʾ���к�
	 * @param r
	 *            ���ֱ�ʾ���кŴ�1��ʼ
	 * @return ��Ԫ������
	 */
	public String readExcelcell(String c, int r) {
		String result = null;
		try {
			this.wb = Workbook.getWorkbook(this.exlFile);
			// ��õ�һ�����������
			// Sheet sheet = book.getSheet( 0 );
			Sheet sheet = this.wb.getSheet(this.sheetname);
			// �õ���һ�е�һ�еĵ�Ԫ��
			Cell cell1 = sheet.getCell(
					ExcelColExchangeNum.convertLetterToNum(c), r - 1);
			result = cell1.getContents();
			System.out.println(result);
			this.wb.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @param sheetName
	 *            ��Ҫ��ȡ��sheet��
	 * @param c
	 *            ���ֱ�ʾ���кŴ�1��ʼ
	 * @param r
	 *            ���ֱ�ʾ���кŴ�1��ʼ
	 * @return ��Ԫ������
	 */
	public String readExcelcell(int c, int r) {
		String result = null;
		try {
			this.wb = Workbook.getWorkbook(this.exlFile);
			// ��õ�һ�����������
			// Sheet sheet = book.getSheet( 0 );
			Sheet sheet = this.wb.getSheet(this.sheetname);
			// �õ���һ�е�һ�еĵ�Ԫ��
		Cell cell1 = sheet.getCell(
					c-1, r - 1);
			result = cell1.getContents();
			//System.out.println(sheet.getColumns());
			this.wb.close();
		} catch (Exception e) {
			System.out.println(e+"\n This cell has no value!"+c+" "+ r);
			return result;
		}
		return result;
	}

	/**
	 * ��ʼ����ȡsheet��������ʼ���ɵ���������ȡsheet�ķ���
	 * 
	 * @param sheetname
	 * @return
	 */
	public ExcelByjxlTool readSheet(String sheetname) {

		try {
			this.wb = Workbook.getWorkbook(this.exlFile);
			System.out.println("��ǰthis.exlFile��Ϊ��"+this.exlFile.getCanonicalPath());
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sheet = this.wb.getSheet(sheetname);
		System.out.println("��ǰSheet��Ϊ��"+this.sheet.getName());
		return this;
	}

	/**
	 * ͨ����Ԫ�����������Ժ������Զ�ȡ��Ԫ���ֵ
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   ȡ��������ĵ�Ԫ������
	 * colAttribute  ȡ��������ĵ�Ԫ������
	 */
	public Cell getcellByRowAndColAttribute(String shname ,String rowAttribute, String colAttribute) {
		//��ȡ���������к�
		//System.out.println("��ǰSheet��Ϊ��"+this.sheet.getName());
		this.readSheet(shname);
//		int rs=this.sheet.getRows();
//		int cs=this.sheet.getColumns();
	
		
		
		int r=this.sheet.findCell(rowAttribute).getRow();
		int c=this.sheet.findCell(colAttribute).getColumn();
		Cell cell= this.sheet.getCell(c, r);
		//System.out.println("�ӵ�ǰSheet��ȡ��Ԫ��"+ExcelColExchangeNum.convertNumToLetter(c)+(r+1)+"������Ϊ"+str);
		return cell;
	}
	
	/**
	 * ͨ����Ԫ�����������Ժ������Զ�ȡ��Ԫ���ֵ
	 * @��Ҫ�ȵ���readsheet������ʼ��sheet;
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   ȡ��������ĵ�Ԫ������
	 * colAttribute  ȡ��������ĵ�Ԫ������
	 */
	public String readcellByRowAndColAttribute(String rowAttribute, String colAttribute) {
		//��ȡ���������к�
		//System.out.println("��ǰSheet��Ϊ��"+this.sheet.getName());
		
//		int rs=this.sheet.getRows();
//		int cs=this.sheet.getColumns();
		int r=this.sheet.findCell(rowAttribute).getRow();
		int c=this.sheet.findCell(colAttribute).getColumn();
		String str= this.sheet.getCell(c, r).getContents();
		//System.out.println("�ӵ�ǰSheet��ȡ��Ԫ��"+ExcelColExchangeNum.convertNumToLetter(c)+(r+1)+"������Ϊ"+str);
		return str;
	}
	
	/**
	 * ͨ����Ԫ�����������Ժ������Ը��µ�Ԫ���ֵ
	 * @param c
	 * @param r
	 * @return
	 * rowAttribute   ȡ��������ĵ�Ԫ������
	 * colAttribute  ȡ��������ĵ�Ԫ������
	 */
	public void updatecellByRowAndColAttribute(String shname,String rowAttribute, String colAttribute, String content) {		
		this.readSheet(shname);
		int r=this.sheet.findCell(rowAttribute).getRow()+1;
		int c=this.sheet.findCell(colAttribute).getColumn();	
		this.UpdateExcel(shname, ExcelColExchangeNum.convertNumToLetter(c), r, content);
	
	}

	/**
	 * �����е�sheet�и���ֵ
	 * 
	 * @param shname
	 *            sheetname Ҫ���µ�sheet��
	 * @param c
	 *            A B C �� ��Ԫ���к�
	 * @param r
	 *            1=��1�� ��Ԫ���к�
	 * @param cellcontent
	 *            Ҫ��ӵ��ַ�����
	 */
	public ExcelByjxlTool UpdateExcel(String shname, String c, int r,
			String cellcontent) {
		try {
			// Excel����ļ�
			this.wb = Workbook.getWorkbook(this.exlFile);
			// ��һ���ļ��ĸ���������ָ������д�ص�ԭ�ļ�
			// System.out.println(ExcelColExchangeNum.convertLetterToNum(c));
			this.writablebook = Workbook.createWorkbook(this.exlFile, this.wb);
			// ��sheet�еĵ�Ԫ��д����
			this.writablesheet = writablebook.getSheet(shname);
			//System.out.println("UpdateExcel��SheetΪ��"+this.writablesheet.getName());
			this.writablesheet.addCell(new Label(ExcelColExchangeNum
					.convertLetterToNum(c), r - 1, cellcontent));
			this.writablebook.write();
			this.wb.close();
			this.writablebook.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}
	/**
	 * 
	 * @param shname ��ǰʵ������ExlFile��sheetname
	 * @return  int ʵ������sheetname��������������
	 */
	public int getThisExlFileTheSheetRows(String shname){
		int rs=this.readSheet(shname).sheet.getRows();
		return rs;
		
	}
	/**
	 * �ڵ�ǰ��ǰʵ������ExlFile��ָ��sheet���һ�У���ָ������д������
	 * @param shname  ָ��sheet
	 * @param cname  Ҫд�����ݵ�����
	 * @param cellcontent  д�������
	 * @return
	 */
	public ExcelByjxlTool updateThisExcelSomeSheetInNewRow(String shname, String cname,String cellcontent){
			this.UpdateExcel(shname, cname, this.getThisExlFileTheSheetRows(shname)+1,cellcontent);	
		return this;	
	}
	
	/**
	 * �ڵ�ǰsheet�����һ��ָ�����������
	 * @param cname  A B C �� ��Ԫ���к�
	 * @param cellcontent
	 * @return
	 */
	public ExcelByjxlTool updateThisExcelThisSheetInNewRow( String cname,String cellcontent){
		this.UpdateExcel(this.sheetname, cname, this.getThisExlFileTheSheetRows(this.sheetname)+1,cellcontent);	
	return this;	
}
	


	/**
	 * 
	 * @param newshname
	 *            ��Ҫ�´�����sheet��<br/>
	 *            ���newshname�����򲻴������������������sheet�󴴽�
	 */
	public void addSheet(String newshname)  {
		Boolean bool = false;
		try {
			System.out.println("��ǰ��ҪaddSheet���ļ�Ϊ��"+this.exlFile.toString());
			this.wb = Workbook.getWorkbook(this.exlFile);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet[] sheets = this.wb.getSheets();
		int sheetsLen = sheets.length;
		for (int i = 0; i < sheetsLen; i++) {
			//System.out.println(sheets[i].getName());
			if (sheets[i].getName().equals(newshname)) {
				bool = true;
				break;
			}
		}

		if (bool) {
			System.out.println("��Ϊ" + newshname + "��Sheet�Ѿ�����");
		} else {
			try {
				this.writablebook = Workbook.createWorkbook(this.exlFile, this.wb);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.writablebook.createSheet(newshname, sheetsLen);
			try {
				this.writablebook.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.writablebook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("��Ϊ" + newshname + "��Sheet�����ɹ�");
			
		}
		this.wb.close();
	}
	
	@Test
	public void testThisTool(){
		File exf=new File("D:\\��Ŀ���\\04.������\\�����ϸ20150326161005.xls");
		
		
		ExcelByjxlTool ex= new ExcelByjxlTool(exf,"�����ϸ");
		//ex.UpdateExcel("�����ϸ", "z", 1, "end");
		try {
			ex.wb = Workbook.getWorkbook(this.exlFile);
			ex.wb.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for(int i=1;i<11;i++){
		
			String end=ex.readExcelcell(i, 1);
			
		if(end==null){
				break;
			}
			System.out.println(end);
			
		}
		
		
	}

}



