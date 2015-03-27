package crazy.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

/**
 * @author xian_crazy QQ��330126160
 * @version 2014��11��5��  ����1:59:09
 * @see
 */
public class FileAccess
{

 public static boolean Move(File srcFile, String destPath)
 {
        // Destination directory
        File dir = new File(destPath);
       
        // Move file to new directory
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
       
        return success;
    }
 
 public static boolean Move(String srcFile, String destPath)
 {
        // File (or directory) to be moved
        File file = new File(srcFile);
       
        // Destination directory
        File dir = new File(destPath);
       
        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));
       
        return success;
    }
 
 
 public  static   String     Copy(InputStream     inStream,     String     newPath)    
 {    
        try     {    
                int     byteread     =     0;    
   
                        FileOutputStream     fs     =     new     FileOutputStream(newPath);    
                        byte[]     buffer     =     new     byte[1444];    
                        //int     length;    
                        while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
                                //System.out.println(bytesum);    
                                fs.write(buffer,     0,     byteread);    
                        }    
                        inStream.close(); 
                        fs.close();
                
        }    
        catch     (Exception     e)     {    
                System.out.println( "error  ");    
                e.printStackTrace();    
        }
		return newPath;    
  }     
 
 
 public  static   void     Copy(String     oldPath,     String     newPath)    
   {    
          try     {    
                  int     byteread     =     0;    
                  File     oldfile     =     new     File(oldPath);    
                  if     (oldfile.exists())     {      
                          InputStream     inStream     =     new     FileInputStream(oldPath);     
                          FileOutputStream     fs     =     new     FileOutputStream(newPath);    
                          byte[]     buffer     =     new     byte[1444];    
                          //int     length;    
                          while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
                                  //System.out.println(bytesum);    
                                  fs.write(buffer,     0,     byteread);    
                          }    
                          inStream.close(); 
                          fs.close();
                  }    
          }    
          catch     (Exception     e)     {    
                  System.out.println( "error  ");    
                  e.printStackTrace();    
          }    
    }     
   public   static  void     Copy(File     oldfile,     String     newPath)    
   {    
          try     {    
                  int     bytesum     =     0;    
                  int     byteread     =     0;    
                  //File     oldfile     =     new     File(oldPath);    
                  if     (oldfile.exists())     {      
                          InputStream     inStream     =     new     FileInputStream(oldfile);     
                          FileOutputStream     fs     =     new     FileOutputStream(newPath);    
                          byte[]     buffer     =     new     byte[1444];    
                          while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
                                  bytesum     +=     byteread;        
                                  System.out.println(bytesum);    
                                  fs.write(buffer,     0,     byteread);    
                          }    
                          fs.close();
                          inStream.close();    
                  }    
          }    
          catch     (Exception     e)     {    
                  System.out.println( "error  ");    
                  e.printStackTrace();    
          }    
    }
   /**
    * ���ַ���������д�뵽�ļ���
    * @see
    * @param content
    * @param file
    */
   public static void saveAsFileWriter(String content,File file) {

	   FileWriter fwriter = null;
	   try {
	    fwriter = new FileWriter(file);
	    fwriter.write(content);
	   } catch (IOException ex) {
	    ex.printStackTrace();
	   } finally {
	    try {
	     fwriter.flush();
	     fwriter.close();
	    } catch (IOException ex) {
	     ex.printStackTrace();
	    }
	   }
	  }

   /**
    * ���ļ��е����ݶ��뵽�ַ�����
    * @see
    */
   public static String readFile(File file){

       InputStreamReader inputReader = null;
       BufferedReader bufferReader = null;
       String line = null;
       StringBuffer strBuffer = null;
      
       try
       {
           InputStream inputStream = new FileInputStream(file);
           inputReader = new InputStreamReader(inputStream);
           bufferReader = new BufferedReader(inputReader);
           
           // ��ȡһ��
          
          strBuffer = new StringBuffer();
               
           while ((line = bufferReader.readLine()) != null)
           {
               strBuffer.append(line);
           } 
           
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
       finally
       {
          try {
			
          bufferReader.close();
          inputReader.close();
          } catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
       }
	return strBuffer.toString();
   }
   
   
   public void mkdirs(File fatherFile,String newdir){
	   File f = new File(fatherFile,newdir);

	   f.mkdirs();

   }

   
   @Test
   public void test(){
	   File f=new File("d:\\111\\222");
	   this.mkdirs(f,"444\\555");
   }
   
   @Test
   public void testcopy(){
	   InputStream inputStream;
	try {
		inputStream = new FileInputStream("E:\\workspace\\crazyt\\src\\main\\resources\\tool\\1.exe");


	   Copy(inputStream,System.getProperty("user.dir")+"\\temp\\3.exe");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}


