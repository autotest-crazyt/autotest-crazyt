package crazy.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 
 * @author xian_crazy QQ��330126160
 * @version 2014��10��30�� ����10:18:24
 *   ���ݿⴴ������:<br/>
	 * access���ݿ�����ʱ��<br/>
	 * 1.���÷���setDBconnPar �������ݿ����ͺ�access���ݿ��ļ�������������չ����<br/>
	 * mysql���ݿ�����ʱ��<br/>
	 * 1.���÷���setDBconnPar �������ݿ����Ӳ��� �û��������<br/>
 */
public class Dbtool {
	// ��ȡ��ǰ��Ŀ·��
	private static String accessdbfilepath ;
	
	

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static String host;
	private static String port;
	private static String username;
	private static String password;
	private static DBType dbtype;
	private static String dbname;

	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public boolean getConnisClosed() {
		boolean bool = true;
		try {
			bool = this.conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * ö�����������firefox, ie, chrome;
	 * 
	 * @param browserstype
	 */
	public enum DBType {
		access, mysql;
	}

	/**
	 * ���ݿⴴ������:<br/>
	 * access���ݿ�����ʱ��<br/>
	 * 1.���÷���setDBconnPar �������ݿ����ͺ�access���ݿ��ļ�������������չ����<br/>
	 * mysql���ݿ�����ʱ��<br/>
	 * 1.���÷���setDBconnPar �������ݿ����Ӳ��� �û��������<br/>

	 * @return Connection
	 */
	public Dbtool getConnection() {
		// ���������ݿ������
		switch (dbtype) {
		case access:
			// db�����ַ���
			 String strConnectAccess = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb, *.accdb);DBQ="
					+ accessdbfilepath;
			// ��������
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException ex) {
				System.out.println("��������ʧ�ܣ�" + ex);
			}
			try {
				this.conn = DriverManager.getConnection(strConnectAccess + dbname + ".accdb");
				System.out.println("�ɹ����ӵ����ݿ⣺" + conn);
			} catch (Exception ex) {
				System.out.println("����ʧ�ܣ�" + ex);
			}
			break;
		case mysql:
			// ��������

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (ClassNotFoundException ex) {
				System.out.println("��������ʧ�ܣ�" + ex);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			String strConnectMysql = "jdbc:mysql://" + host + ":" + port + "/dbname?" + "user="
					+ username + "&password=" + password
					+ "&useUnicode=true&characterEncoding=UTF8";
			strConnectMysql = strConnectMysql.replace("dbname", dbname);
			try {

				this.conn = DriverManager.getConnection(strConnectMysql);
				System.out.println("�ɹ����ӵ����ݿ⣺" + conn);
			} catch (Exception ex) {
				System.out.println("����ʧ�ܣ�" + ex + "\n" + strConnectMysql);
			}
			break;
		}

		return this;
	}

	/**
	 * �ر����ݿ�����
	 * 
	 * @param conn
	 */
	public void close() {
		try {
			if (this.conn != null) {
				// �ر����ӣ��ͷ���Դ
				this.conn.close();
				System.out.println("���ݿ������Ѿ��ر�");
			}
		} catch (SQLException ex) {
			//
			System.out.println("���ݿ����ӹر��쳣��" + ex);
		}
	}

	/**
	 * ��ListתΪ�ַ��� aaa,bbb,ccc
	 * 
	 * @see
	 * @param valuename
	 * @return
	 */
	private String listTosqlstr(List<String> valuename) {
		// ����data��ά����
		String value = "";
		for (String str : valuename) {
			value += str + ",";
		}
		return value.substring(0, value.length() - 1);
	}

	/**
	 * ��sql��ѯ�Ľ���� ��ŵ���ά������
	 * 
	 * @see
	 * @param rs
	 * @return
	 */
	private String[][] sqlResultSet2Array(ResultSet rs) {
		// ����data��ά����
		String[][] data = null;
		try {
			// ��ȡ��ѯ���������
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// ʵ����data��ά����
			data = new String[rows][columnCount];
			for (int j = 0; rs.next(); j++) {

				for (int i = 0; i < columnCount; i++) {
					data[j][i] = rs.getString(i + 1);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 * @param valuename
	 *            List��ѯ����������
	 * @param fromtablename
	 * 
	 * @return select List<String> valuename from fromtablename
	 * 
	 */
	public String[][] getData(List<String> valuename, String fromtablename) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, "", "", "", 3);
	}

	/**
	 * 
	 * @param valuename
	 *            ��ѯ��������
	 * @param fromtablename
	 * 
	 * @return select valuename from fromtablename
	 * 
	 */
	public String[][] getData(String valuename, String fromtablename) {
		List<String> vlis = Arrays.asList(valuename);
		return this.getValuesbyKeyORDERBY(vlis, fromtablename, "", "", "", 3);
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @return select valuename from fromtablename
	 * 
	 */
	public List<String> getValues(String valuename, String fromtablename) {
		List<String> list = new ArrayList<String>();
		List<String> vlis = new ArrayList<String>();
		vlis.add(valuename);
		String[][] rs = this.getData(vlis, fromtablename);
		for (String[] str : rs) {
			list.add(str[0]);
		}
		return list;
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @return select valuename from fromtablename where key = keyvalue
	 * 
	 */
	public String[][] getValuesbyKey(List<String> valuename, String fromtablename, String key,
			String keyvalue) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, key, keyvalue, "", 2);
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param orderby
	 * @return select valuename from fromtablename where key = keyvalue
	 *         �����ѯ������ж��ֵ����ѯ�������orderby �������һ��
	 */
	public String getValuebyKeyOrderby(String valuename, String fromtablename, String key,
			String keyvalue, String orderby) {
		List<String> vlis = Arrays.asList(valuename);
		String[][] astr = this
				.getValuesbyKeyORDERBY(vlis, fromtablename, key, keyvalue, orderby, 1);
		int rows = astr.length;
		return astr[rows - 1][0];
	}

	/**
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @return select valuename from fromtablename where key = keyvalue
	 *         �����ѯ������ж��ֵ����ѯ����������һ��
	 */
	public String getValuebyKey(String valuename, String fromtablename, String key, String keyvalue) {
		List<String> vlis = Arrays.asList(valuename);
		String[][] astr = this.getValuesbyKeyORDERBY(vlis, fromtablename, key, keyvalue, "", 2);
		int rows = astr.length;
		return astr[rows - 1][0];
	}

	public void updateValueBykey(String updatetablenameindb, String key, String keyvalue,
			String valuename, String newvalue) {
		PreparedStatement state = null;

		try {
			String sql = "UPDATE " + updatetablenameindb + " SET " + valuename + " = ? WHERE "
					+ key + " = ?";
			state = this.conn.prepareStatement(sql);
			state.setString(1, newvalue);
			state.setString(2, keyvalue);
			state.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void insertkeyValue(String totablenameindb, String key, String keyvalue) {
		PreparedStatement state = null;

		try {
			String sql = "insert into " + totablenameindb + "(" + key + ") values (?)";
			state = this.conn.prepareStatement(sql);
			state.setString(1, keyvalue);
			state.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public List<String> getTableColumnName(String tablenameindb) {
		List<String> list = new ArrayList<String>();
		PreparedStatement state = null;
		ResultSet rs = null;

		try {
			String sql = "select *  from  " + tablenameindb;
			state = this.conn.prepareStatement(sql);

			rs = state.executeQuery();
			ResultSetMetaData data = rs.getMetaData();
			rs.next();
			// ��ʾ��ѯ���
			for (int i = 1; i <= data.getColumnCount(); i++) {
				// System.out.println(data.getColumnName(1));
				list.add(data.getColumnName(i));
				// System.out.println(" rs.getString(1)="+str);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;

	}

	/**
	 * ��ѯ OR BY KEY BY ORDERBY
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param orderbyname
	 * @return select valuename from fromtablename where key = keyvalue order by
	 *         orderby
	 * 
	 */
	public String[][] getValuesbyKeyORDERBY(List<String> valuename, String fromtablename,
			String key, String keyvalue, String orderbyname) {
		return this.getValuesbyKeyORDERBY(valuename, fromtablename, key, keyvalue, orderbyname, 1);
	}

	/**
	 * ��ѯ OR BY KEY BY ORDERBY
	 * 
	 * @param valuename
	 * @param fromtablename
	 * @param key
	 * @param keyvalue
	 * @param n
	 *            �� 1��key and oderby�� �� 2��key ����3��null��
	 * @return select valuename from fromtablename where key = keyvalue
	 * 
	 */
	private String[][] getValuesbyKeyORDERBY(List<String> valuename, String fromtablename,
			String key, String keyvalue, String orderby, int n) {

		PreparedStatement state = null;
		ResultSet rs = null;

		// ����data��ά����
		String[][] data = null;
		// ����sql��ѯĿ���ַ���
		String value = this.listTosqlstr(valuename);
		String sql = null;
		switch (n) {
		case 1:
			sql = "select " + value + " from " + fromtablename + " where " + key + " = ? ORDER BY "
					+ orderby;
			break;
		case 2:
			sql = "select " + value + " from " + fromtablename + " where " + key + " = ? ";
			break;
		case 3:
			sql = "select " + value + " from " + fromtablename;
			break;
		}
		try {

			// System.out.println(sql+"  "+ keyvalue);
			state = this.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			switch (n) {
			case 1:
				//to  do
			case 2:
				state.setString(1, keyvalue);
				break;
			default:
				break;
			}
			rs = state.executeQuery();

			data = this.sqlResultSet2Array(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (state != null) {
					state.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return data;
	}



	public void setDBconnPar(DBType dbtype,String dbname,String host, String port, String username, String password) {
		Dbtool.host = host;
		Dbtool.port = port;
		Dbtool.username = username;
		Dbtool.password = password;
		Dbtool.dbtype=dbtype;
		Dbtool.dbname=dbname;
	}
	
	public void setDBconnPar(DBType dbtype,String accessdbfilepath,String dbname) {
		Dbtool.accessdbfilepath = accessdbfilepath;
		Dbtool.dbtype=dbtype;
		Dbtool.dbname=dbname;
	}

	@Test
	public void testgetValuebyKey() throws SQLException {
		this.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		Dbtool dbtool = this.getConnection();
		// select UiSelector as t from StartPage where elementname = ?
		// start1Layout
		String str = dbtool.getValuebyKey("xpath", "lichengbaobiaopage", "elementname", "��̱�������ѡ��");
		System.out.println(str);
		dbtool.close();

	}

	@Test
	public void testinsertkeyValue() throws SQLException {
		this.setDBconnPar(DBType.access, "","testData");
		Dbtool dbtool = this.getConnection();
		dbtool.insertkeyValue("testAppUserDataInfo", "�ֻ���", "13325465002");
		String str = dbtool.getValuebyKeyOrderby("�ֻ���", "testAppUserDataInfo", "�ֻ���",
				"13325465002", "���");
		System.out.println(str);
		dbtool.close();

	}

	@Test
	public void testupdateValueBykey() throws SQLException {
		this.setDBconnPar(DBType.access, "","testData");
		Dbtool dbtool = this.getConnection();
		dbtool.updateValueBykey("testAppUserDataInfo", "�ֻ���", "13325465002", "˾������", "Ҷ��");
		String str = dbtool.getValuebyKeyOrderby("˾������", "testAppUserDataInfo", "�ֻ���",
				"13325465002", "���");
		System.out.println(str);
		dbtool.close();
	}

	@Test
	public void testgetTableColumnName() {
		this.setDBconnPar(DBType.access,"", "testData");
		Dbtool dbtool = this.getConnection();
		List<String> list = dbtool.getTableColumnName("testAppUserDataInfo");
		list.iterator();
		int i = 0;
		while (i < 6) {
			System.out.println(list.get(i + 2));
			i++;
		}
		dbtool.close();
	}

	@Test
	public void testconn() {

this.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		Dbtool dbtool = this.getConnection();
		List<String>  account =new ArrayList<String>();
		account.add("�û���");
		account.add("����");
		
		
		
		String [][] accounts1=dbtool.getData(account, "userData");
		String [][] accounts2;
		
		List<String[]>  accounts =new ArrayList<String[]>();
		//����ѯ����ǿս���Ž�list
		for(String[] strs:accounts1){
			if (strs[0].length()>0){
				accounts.add(strs);
			}
		}
		//��list���ݷŽ���ά����
		accounts2 = new String[accounts.size()][];//ͨ��list����ʵ������ά����
		int i=0;
		
		for(String[] str:accounts){
			accounts2[i]=str;
			i++;
		}
		
		for(String[] s:accounts2){
			for(String s1: s){
				System.out.println("ֵ��"+  s1);
			}
		}
		

//		String str = dbtool.getValuebyKey("�û���", "userdata", "���", "1");
//		System.out.println(str);
//
//		Dbtool dbtool2 = this.getConnection();
//
//		String str2 = dbtool2.getValuebyKey("����", "userData", "���", "1");
//		System.out.println(str2);

		dbtool.close();
		//dbtool2.close();

	}

	@Test
	public void testgetData() throws SQLException {
		this.setDBconnPar(DBType.mysql, "TestData", "192.168.0.56", "3306", "root", "root");
		Dbtool dbtool = this.getConnection();
		// select UiSelector as t from StartPage where elementname = ?
		// start1Layout
		List<String> valuename = Arrays.asList("����Χ", "���");
		String[][] astr = dbtool.getData(valuename, "areadatapro");
		for (int j = 0; j < astr.length; j++) {
			for (int i = 0; i < astr[j].length; i++) {
				System.out.print(astr[j][i] + "  ");
			}
			System.out.println();
		}

		dbtool.close();

	}

}
