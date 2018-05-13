package helloword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadInjava {
   public static void main(String args[])
   {
	   try {
		InputStreamReader in=new InputStreamReader(new FileInputStream("D:/Filetest/002.txt"),"GBK");//编码方式设置为一致
		BufferedReader br=new BufferedReader(in);
		String brs;
		while((brs=br.readLine())!=null)
		{
			System.out.println(brs);
		}
		in.close();
		br.close();
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("readline fail");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
