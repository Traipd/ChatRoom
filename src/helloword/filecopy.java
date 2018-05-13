package helloword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class filecopy {

	public static void main(String[] args) {
		try {
			FileInputStream fis=new FileInputStream("D:/Filetest/001.txt");
			FileOutputStream fos=new FileOutputStream("D:/Filetest/f001.txt");
			int i=1;
			byte[] b=new byte[1024];
			int readlen=4;
			while(i!=-1)
			{
				i=fis.read(b,0,readlen);
				if(i!=-1)
				{
					fos.write(b, 0, readlen);
				}
			}
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
		System.out.print("file open fill");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("file close fill");
			e.printStackTrace();
		}

	}

}
