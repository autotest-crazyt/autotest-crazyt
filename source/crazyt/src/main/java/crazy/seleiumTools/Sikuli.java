package crazy.seleiumTools;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import crazy.tools.BasicTools;





/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��20��  ����3:09:13
 * @see
 */
public class Sikuli {
	Screen s=new Screen(); 
	private BasicTools btool=new BasicTools();
	
	public Sikuli click(String img){
		this.getElement(img).click();
		return this;
	}
	/**
	 * ���ͼƬ,���ͼƬ��ҳ���ҵ��ҵ���ɹ� ����true ���� flase
	 * @see
	 * @param img  ͼƬ�ڱ��ص�·��
	 * @param offsetx_by_topleft  ͼƬ���Ͻ�ƫ��x����
	 * @param offset_by_topleft ͼƬ���Ͻ�ƫ��y����
	 * @return
	 */
	public boolean click(String img,int offsetx_by_topleft,int offset_by_topleft){
		
		Match m=this.getElement(img);
			// btool.rpt((offsetx_by_topleft-m.getW()/2)+"   "+ (offset_by_topleft-m.getH()/2));
			m.setTargetOffset(offsetx_by_topleft-m.getW()/2, offset_by_topleft-m.getH()/2);		
		
			boolean bool=(m!=null&&m.click()==1)?true:false;
			return bool;
	}
	
	//ͨ��ͼƬ��ҳ���ҵ�Ԫ�ز�����1����
	public Match getElement(String img){
		Match m =null;
		try {
			m=s.find(img);
			m.highlight(1);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			 btool.rpt("ͼƬ"+img+"�ڵ�ǰ��ͼδ�ҵ�");
		}	
		return m;
	}
	

}
