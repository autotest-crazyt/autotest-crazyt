package crazy.seleiumTools;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import crazy.tools.BasicTools;
import crazy.tools.BasicTools.SelectorType;
import crazy.tools.Dbtool;

public class WebBasicPage  {
	
	public BasicTools btool;
	public WebTools wtool;
	public Sikuli s=new Sikuli();
	private WebDriver driver;
	private String tablenameindb = this.getClass().getSimpleName();

	public WebBasicPage(WebDriver wdriver, Dbtool dbtool) {
		this.btool=new BasicTools(wdriver, dbtool);
		this.wtool=new WebTools(wdriver,dbtool);
		this.driver=wdriver;
		//this.btool.pt("WebBasicPage:  "+tablenameindb);
	}
	
	/**
	 * �ҵ�����һ���ɼ�
	 * @see
	 * @param elementnameindb
	 * @return
	 */
	public WebElement findWebElement(String elementnameindb) {

		return this.btool.findElement(tablenameindb, elementnameindb,
				SelectorType.xPath,30);
	}
	
	public WebElement getWebElementInTime(String elementnameindb,int time) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.xPath,time);
	}

	public WebElement getWebElement(String elementnameindb) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.xPath,30);
	}
	
	public WebBasicPage setTablenameindb(String tablenameindb) {
		this.tablenameindb = tablenameindb;
		return this;
	}
	

	public String getTablenameindb() {
		return tablenameindb;
	}

	public WebElement getWebElementByupdateXpath(String elementnameindb,String valueOfvar) {
		return
this.wtool.getWebElementByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar);
		 
	}
	
	
	public List<WebElement> getWebElementsByupdateXpath(String elementnameindb,String valueOfvar) {
		return
this.wtool.getWebElementsByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar,10);
		 
	}
	
	public WebElement getWebElementByCSS(String elementnameindb) {

		return this.wtool.getWebElement(tablenameindb, elementnameindb,
				SelectorType.CSS,30);
	}
	/**
	 * 
	 * @see
	 * @param element
	 * @return �����Ƿ�ɼ���������text(�������ӽڵ�);
	 */
	public String getWebElementTextBy160SpaceSplit(String elmentNameInDB) {
		
		return this.getWebElementTextBy160SpaceSplit(this.findWebElement(elmentNameInDB)).get(0);
	}
	/**
	 * Ԫ�ؼ���Ԫ����160�ո��βʱ����
	 * @see
	 * @param element
	 * @return �����Ƿ�ɼ���������textList ��ȥ���ո�  �±��Ԫ�ظ��ڵ㿪ʼ����;
	 */
	public List<String> getWebElementTextBy160SpaceSplit(WebElement elment) {
		String str=((String) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].textContent;", elment))+" ";
		
		List<String> list=Arrays.asList(str.split((char)160+""));
		for(String str1:list){
			list.set(list.indexOf(str1), str1.trim());
		}
		return list;
	}
	
	
	/**
	 *
	 * @see
	 * @param element
	 * @return �����Ƿ�ɼ���������textList ��ȥ���ո�  �±��Ԫ�ظ��ڵ㿪ʼ����;
	 */
	public List<String> getWebElementText(WebElement elment) {
		String str=elment.getText();
		str.replace((char)160+"", "");
		List<String> list=Arrays.asList(str.split("\n"));
		for(String str1:list){
			list.set(list.indexOf(str1), str1.trim());
		}
		return list;
	}

	/**
	 * 
	 * @see
	 * @param elementname  �Զ����Ԫ������ֻ��Ϊlog������ʹ�ã���ʵ������
	 * @param type
	 * @param newselctor  �����б�д�µ�selector
	 * @param timeout
	 * @return
	 */
	public WebElement getWebElementBynewselctor(String elementname,
			SelectorType type, String newselctor) {
		return this.wtool.getElementBynewselctor(elementname, type,
				newselctor,30);
	}
	
	/**
	 * 
	 * @see
	 * @param elementname  �Զ����Ԫ������ֻ��Ϊlog������ʹ�ã���ʵ������
	 * @param type
	 * @param newselctor  �����б�д�µ�selector
	 * @return
	 */
	public WebElement getWebElementBynewselctorInTime(String elementname,
			SelectorType type, String newselctor,int waitOutTime) {
		return this.wtool.getElementBynewselctor(elementname, type,
				newselctor,waitOutTime);
	}
	
	public List<WebElement> getWebElementsBynewselctorInTime(String elementname,
			SelectorType type, String newselctor,int waitOutTime) {
		return this.wtool.getElementsBynewselctor(elementname, type,
				newselctor,waitOutTime);
	}

	public List<WebElement> getWebElements(String elementnameindb) {
		return this.wtool
				.getWebElements(tablenameindb, elementnameindb, SelectorType.xPath);
	}
	
	

	/**
	 * ��ָ��ʱ���ڷ���List �Ҳ������߳�ʱ�򷵻�NULL
	 * @see
	 * @param elementnameindb
	 * @param waitTimeOut
	 * @return
	 */
	public List<WebElement> getWebElementsInTime(String elementnameindb,int waitTimeOut) {
		return this.wtool
				.getElements(tablenameindb, elementnameindb, SelectorType.xPath,waitTimeOut);
	}
	
	/**
	 * ��ȡList�е����Webelement start��ʾlist�������Χ��ʼ�±�ֵ,end ��ʾ�����Χ�����±�
	 * �����end����list����±꣬ȡ����±�
	 * @see
	 * @param list
	 * @param i
	 * @return
	 */
	public WebElement  getRandomWebElementFromList(List<WebElement> list, int start,int end){
		WebElement el=this.wtool.getRandomWebElementFromList(list, start,end);
		this.btool.pt("point:="+el.getLocation().getX()+ "  "+el.getLocation().getY());
		
		return el;
	}
	
	/**
	 * 
	 * @see
	 * @param list
	 * @return
	 */
	public WebElement  getLastWebElementFromList(List<WebElement> list){
		WebElement el=this.wtool.getLastWebElementFromList(list);
		this.btool.pt("point:="+el.getLocation().getX()+ "  "+el.getLocation().getY());
		
		return el;
	}

	public void pt(String str) {
		this.btool.pt(str);
	}

	public Select getSelectElement(String selectElementnameindb) {
		return this.wtool.getDrop_DownList(tablenameindb, selectElementnameindb,
				SelectorType.xPath);
	}
	
	/**
	 * select �����˵�ѡ������
	 */
	public void setSelectVaule(String selectElementnameindb,String selectByVisibleText){
		this.getSelectElement(selectElementnameindb).selectByVisibleText(selectByVisibleText);
		
		WebElement el=this.getWebElement(selectElementnameindb);
		String jsstr="var el=arguments[0];"
				+ "var index = el.selectedIndex;"
				+ "var text = el.options[index].text;"
				+ "return text;";
		
		this.btool.rpt("�������"+selectElementnameindb+"��������Ϊ����"+this.wtool.runjs(jsstr,el)+"��");
	}
	

	/**
	 * ͨ��js��ȡҳ��Ԫ�ص�title
	 * Ԫ�ر������ID �ſ���ʹ�ñ�����;
	 */
		public String getEltitle(WebElement el){
			String value = null;
			String jsstr="var el=arguments[0];"
					+ "var newel = document.getElementById(el.id);"
					+"var value=newel.title;"
					+ "return value;";	
			value=this.wtool.runjs(jsstr, el);	
			//FileAccess.saveAsFileWriter(this.driver.getPageSource(),new File("E:\\123.HTML"));
			return value;
		}
		
		

	/**
	 * 
	 * @param elementnameindb  �����˵���ѡ����Ԫ����
	 * @param i   list�±����ѡ��Χ��ʼֵ
	 * @return
	 */
	public WebElement getRandomWebElementFromDrop_DownList(String elementnameindb,int i){
		List<WebElement> list=this.getSelectElement(elementnameindb).getOptions();
			return this.wtool.getRandomWebElementFromList(list, i,list.size()-1);
		}
	
	/**
	 * ��ת���µĴ���
	 * @see
	 * @param newWindowsTitle
	 */
	public boolean toNewWindws(String newWindowsTitle){
		this.btool.rpt("��ת����ҳ�棺"+newWindowsTitle);
		return this.wtool.toNewWindows(newWindowsTitle);
	}
	
	/**
	 * ��תiframe(������ҳ��ֻ��Ψһ��iframe���)  
	 * By.xpath("//iframe")
	 * @see
	 * @return
	 */
	public void toiFrame(){
		this.wtool.toNewFrame(this.driver.findElement(By.xpath("//iframe")));
	}
	
	/**
	 * this.dr.switchTo().defaultContent();
	 * @see
	 */
	public void toDefaultFrame() {
		this.wtool.toDefaultFrame();
	}
	


	public WebBasicPage scrollertoWebElementAndClick(String elementnameiindb){
		this.wtool.scrollerToElement(this.getWebElement(elementnameiindb)).click();
		return this;
	}
	
	
	
	public WebBasicPage editInputElement(String elementnameindb,String inputstr){
		this.btool.rpt("�����"+elementnameindb+"; ������: "+inputstr);
		WebElement el=this.getWebElement(elementnameindb);
		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	/**
	 * "���"+elDescription+"; ������: "+inputstr
	 * @see
	 * @param el
	 * @param elDescription
	 * @param inputstr
	 * @return
	 */
	public WebBasicPage editInputElement(WebElement el,String elDescription,String inputstr){
		this.btool.rpt("���"+elDescription+"; ������: "+inputstr);

		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	public WebBasicPage editInputSecret(String elementnameindb,String inputstr){
		this.btool.rpt("�����"+elementnameindb+"; ������:********** ");
		WebElement el=this.getWebElement(elementnameindb);
		el.clear();
		el.sendKeys(inputstr);
		return this;
	}
	
	/**
	 * 
	 * @param elementnameindb  Listѡ����Ԫ����
	 * @param i   list�±����ѡ��Χ��ʼֵ
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromList(String listElementnameindb,int i){
		this.btool.rpt("���Ԫ�ؼ���"+listElementnameindb+"  �дӵ�"+(i+1)+"����ʼ�����Ԫ��");
		List<WebElement> list=this.getWebElements(listElementnameindb);
		try{
		WebElement el=this.getRandomWebElementFromList(list, i,list.size()-1);
		while(!el.isDisplayed()){
			this.threadSleep(1);
		}
		el.click();
		}catch(TimeoutException e){
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		return  this;
	}
	
	/**
	 * �Ҽ����ҳ��Ԫ�ط����Ҽ��������Ҫ�ҵ���Ԫ��
	 */
	public WebElement rightClick2anotherEl(String elementNameInDB, String anotherElNameInDb){
		this.btool.rpt("�Ҽ����["+elementNameInDB+"] �ҵ�["+anotherElNameInDb+"]");
		WebElement nel=null;
		for(int i=0;i<5 && nel==null;i++ ){
		this.wtool.a.contextClick(this.getWebElement(elementNameInDB)).perform();
		nel=this.getWebElementInTime(anotherElNameInDb,2);
		}
		return nel;
	}
	
	/**
	 * ��ȡList�е����Webelement start��ʾlist�������Χ��ʼ�±�ֵ,end ��ʾ�����Χ�����±�
	 * �����end����list����±꣬ȡ����±�
	 * @see
	 * @param listElementnameindb
	 * @param start
	 * @param end
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromPartOfList(String listElementnameindb,int start,int end){
		this.btool.rpt("���Ԫ�ؼ�:"+listElementnameindb+"�еĵ�"+(start+1)+"������"+(end+1)+"���������һ��");
		try{
		this.getRandomWebElementFromList(this.getWebElements(listElementnameindb), start,end).click();
		}catch(TimeoutException e){
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		return  this;
	}
	
	/**
	 * ��ȡList�е����Webelement start��ʾlist�������Χ��ʼ�±�ֵ,end ��ʾ�����Χ�����±�
	 * �����end����list����±꣬ȡ����±�
	 * @see
	 * @param listElementnameindb
	 * @param start
	 * @param end
	 * @return
	 */
	public WebBasicPage clickRandomWebElementFromPartOfList(List<WebElement> list,int start,int end,String listDescription){
		this.btool.rpt("���Ԫ�ؼ�:"+listDescription+"�еĵ�"+(start+1)+"������"+(end+1)+"���������һ��");
		try{
		this.getRandomWebElementFromList(list, start,end).click();
		}catch(TimeoutException e){
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		return  this;
	}
	
	public WebBasicPage  clickWebElement(String elementnameindb){
		this.btool.rpt("�����"+elementnameindb);
		try{
		this.getWebElement(elementnameindb).click();
		}catch(TimeoutException e){
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		return  this;
	}
	/**
	 * ����el�����el��2���ڵȴ�newel���֣����������ѭ������5�Σ���Ȼ���Ҳ�������newl=null;
	 * @see
	 * @param elementnameindb
	 * @param newelNameindb
	 * @return
	 */
	public WebElement  clickEl4GetNewEL(String elementnameindb,String newelNameindb){
		this.btool.rpt("�����"+elementnameindb+"  �ȴ����֣�"+newelNameindb);
		boolean t=true;
		WebElement el=null;
		for(int i=0;i<5&&t;i++){
		try{
		this.getWebElement(elementnameindb).click();
		}catch(TimeoutException e){
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		el=this.getWebElementInTime(newelNameindb, 2);
		if(el!=null){
			this.btool.rpt(newelNameindb+"-----�Ѿ�����");
			t=false;
			this.wtool.a.moveToElement(el).perform();
		};
		}
		return  el;
	}
	/**
	 * ���el���������������¼��������־
	 * @see
	 * @param element
	 * @param elDescription
	 * @return
	 */
	public WebBasicPage  clickWebElement(WebElement element,String elDescription){
		this.btool.rpt("�����"+elDescription);
		try{
			element.click();
			//this.wtool.clickTheLeftMouseButton(element);
		}catch(TimeoutException e){			
			 btool.rpt(" ҳ����س�ʱ"+BrowsersDriverPro.getPageLoadTimeout()+"�룬  ���ȴ�������ϣ�����ִ��");
		}
		return  this;
	}
	
	/**
	 * 
	 * @param elementnameindb  �����˵���ѡ����Ԫ����
	 * @param i   list�±�
	 * @return
	 */
	public WebBasicPage clickoneOfWebElementFromList(String listElementnameindb,int i){
		this.btool.rpt("���Ԫ�ؼ���"+listElementnameindb+"  �еĵ�"+(i+1)+"��");
		this.getWebElements(listElementnameindb).get(i).click();
		return  this;
	}
	
	

	public  WebBasicPage clickfileUploadElementThenUploadByAutoItforfireFox_64(String uploadElementNameIndb,String fileallURL) {
		 btool.rpt("����ļ��ϴ���ť�ϴ��ļ�");
		
			this.wtool.clickfileUploadElementThenUploadByAutoItforfireFox_64(this.getWebElement(uploadElementNameIndb),fileallURL);
	
		return this;
	}
	
	public WebBasicPage clickWebElementOfReplaceXpath(String elementnameindb, String valueOfvar){
		this.btool.rpt("�����"+elementnameindb);
		this.wtool.getWebElementByReplaceSelectorOfindb(tablenameindb, elementnameindb, SelectorType.xPath, valueOfvar).click();
		return this;
	}
	/**
	 * 
	 * @see
	 * @param elementnameOfMy97DatePickerindb
	 * @param i 0 Ĭ��ʱ�� 
	 * @return
	 */
	public WebBasicPage setDate_use_My97DatePicker(String toBeSetELNameInDB, int i){
		this.clickWebElement(toBeSetELNameInDB);
		this.wtool.toNewFrame(this.driver.findElement(By.xpath("//iframe[contains(@src,'My97DatePicker')]")));
		switch(i){
		case 0:
			this.driver.findElement(By.xpath("//input[@id='dpOkInput']")).click();
			break;
		default:
			 btool.rpt("���δ���");
		}
		this.wtool.toDefaultFrame();				
		return this;		
	}
	
	/**
	 * 
	 * @see
	 * @param elementnameOfMy97DatePickerindb
	 * @param i 0 Ĭ��ʱ�� 
	 * @return
	 */
	public WebBasicPage setDate_use_onlyiframe(String toBeSetELNameInDB, int month,int year,int day){
		WebElement frame=null;
		for(int i=0;i<3&&frame==null;i++){
		this.clickWebElement(toBeSetELNameInDB);
		frame=this.getWebElementBynewselctorInTime("ʱ��ؼ�frame", SelectorType.xPath, "//iframe[not(@id)]",2);
		}
		WebElement month1=null;
		for(int i=0;i<3 && month1==null;i++){
		this.wtool.toNewFrame(frame);
		month1=this.btool.waitThenGetElement("//div[@id='dpTitle']/div[3]/input[@class='yminput']", 2, SelectorType.xPath, true);
		}//������
		
		this.clickWebElement(month1, "�±༭��").editInputElement(month1, "", month+"");

		
		//������
		WebElement year1=this.driver.findElement(By.xpath("//div[@id='dpTitle']/div[4]/input[@class='yminput']"));
		this.clickWebElement(year1, "��༭��").editInputElement(year1, "", year+"");

		
		//������������������˵�
		this.wtool.a.moveToElement(this.driver.findElement(By.xpath("//table/tbody/tr[@class='MTitle']"))
				, 5, 5).click().perform();
		
		
		WebElement day1=null;
		//��������
		this.btool.rpt("��������Ϊ��"+day);
		if(day<=15){
		 day1=this.driver.findElements(By.xpath("//td[text()="+day+"]")).get(0);
		}else{
			day1=this.driver.findElements(By.xpath("//tbody/descendant::td[text()="+day+"][last()]")).get(0);
		}
		
		//�����ǰ����δ��ѡ������ѡ��
		if(0>day1.getAttribute("onmouseout").indexOf("Wselday"))
			day1.click();
		
		//�жϵ�����ں��Ƿ�رտؼ����������յ��ȷ��
		WebElement ok=this.btool.waitThenGetElement("//input[contains(@id,'OkInput')]", 2, SelectorType.xPath, true);
		if(ok!=null){
			ok.click();
		}
		
			
		this.wtool.toDefaultFrame();				
		return this;		
	}
	
	public String getWebElementValue(String elementnameindb){
		
		return this.getWebElement(elementnameindb).getAttribute("value");
	}
	
	/**
	 * ͨ�����ҳ��Ԫ�أ���ȡ�µ�ҳ��Ԫ�� ��ʱʱ��3��
	 * @see
	 * @param oldWebElnameindb
	 * @param newWebElNameInDB
	 * @param i �����Ԫ��û�ж�λ�����ظ����oldel����
	 * @return
	 */
	public WebElement getNewWebElByClickOldWebEl(String oldWebElnameindb,String newWebElNameInDB,int i){
		WebElement newEL=null;
		for(int j=0;j<i&&newEL==null;j++){
			this.clickWebElement(oldWebElnameindb);
			newEL=this.getWebElementInTime(newWebElNameInDB, 3);
		}
		
		return newEL;
	}

	/**
	 * @see
	 * @param i
	 */
	public WebBasicPage threadSleep(int i) {
		this.btool.threadSleep(i);
		return this;
		
	}
	
	/**
	 * ���element��id��ҳ����Ψһ������ʹ�ø÷������й�����ƫ������ʹelement��������ͼ��;<br/>
	 * "����ƶ���"+whereElNameIndb+"������ֱ��"+elDescription+"�����ڵ�ǰ��ͼ"
	 * 
	 */
	public WebElement rollElToCurrentPage(WebElement el,String whereElNameIndb,String elDescription){
		this.btool.rpt("����ƶ���"+whereElNameIndb+"������ֱ��"+elDescription+"�����ڵ�ǰ��ͼ");
		WebElement whereEl=this.getWebElement(whereElNameIndb);
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * ���element��id��ҳ����Ψһ������ʹ�ø÷������й�����ƫ������ʹelement��������ͼ��;<br/>
	 * "����ƶ���"+whereEl+"������ֱ��"+el+"�����ڵ�ǰ��ͼ"
	 * 
	 */
	public WebElement rollElToCurrentPage(WebElement el,WebElement whereEl){
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * ���element��id��ҳ����Ψһ������ʹ�ø÷������й�����ƫ������ʹelement��������ͼ��;<br/>
	 * "����ƶ���"+whereEl+"������ֱ��"+el+"�����ڵ�ǰ��ͼ"
	 * 
	 */
	public WebElement rollElToCurrentPage(String elID,WebElement whereEl){
		this.wtool.a.moveToElement(whereEl).sendKeys(Keys.ENTER).build().perform();
		WebElement el=this.driver.findElement(By.id(elID));
		this.wtool.webscroll.scrollToElement(el);
		return el;
	}
	
	/**
	 * �ƶ���굽��Ԫ�����ĺ����������ͣ������ҵ���Ԫ�ص��
	 * @see
	 */
	public WebBasicPage hovermouseFornewElementThenClick(String elNameIndb, String subElementNameIndb){
		this.btool.rpt("�ƶ���굽["+elNameIndb+"]���ĺ����������ͣ������ҵ�["+subElementNameIndb+"]���");
	 WebElement el=this.getWebElement(elNameIndb);
	 boolean bool=true;
	 for(int i=0;i<3 && bool;i++){
	 try{
		this.wtool.a.moveToElement(el)
		.click().perform();
		this.wtool.a.moveToElement(this.getWebElementInTime(subElementNameIndb,3))
		.click().perform();	
		bool=false;
	 }catch(Exception e){
		 this.btool.rpt(subElementNameIndb+"δ�ҵ����ٴε��"+elNameIndb);
	 }
	 }
		return this;
	}
	
	/**
	 * �ƶ���굽Ԫ�����Ͻ�xƫ�ưٷֱȣ�yƫ��ΪԪ�ظ߶ȵ�һ�룩λ�ú���
	 * @see
	 * @param elNameIndb
	 * @param percentoffsetx  eg:  50%   percentoffsetx=50;
	 * @return
	 */
	public WebBasicPage moveMouse2ElOffsetXY_ThenClick(String elNameIndb, int percentoffsetx){
		WebElement el=this.getWebElement(elNameIndb);
		Dimension size=el.getSize();
		this.wtool.a.moveToElement(el,size.getWidth()*(percentoffsetx/100),size.getHeight()/2)
		.click().perform();
		
		return this;
	}
	
	public WebBasicPage sendKey(CharSequence key){
		this.wtool.a.sendKeys(key).perform();
		return this;
	}
	
	
	public WebTools getWtool() {
		return wtool;
	}
	

	public WebDriver getDriver() {
		return driver;
	}
	
}
