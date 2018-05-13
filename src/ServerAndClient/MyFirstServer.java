package ServerAndClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyFirstServer {
    public static void main(String arg[])
   {
  	try {
  		MyFirstServer mfs=new MyFirstServer();
 
		ServerSocket server=new ServerSocket(9090);
		Socket sk=server.accept();//在这里停顿,产生新的Socket
		OutputStream out=sk.getOutputStream();
		InputStream inp=sk.getInputStream();
		
			BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));//输出时的编码方式,"GBK"
		
		String s="hello,my computer,邹俊\r\n";
		byte[] data=s.getBytes();
		byte[] indata=new byte[64];
		bwrite.write(s,0,s.length());
		bwrite.flush();
		mfs.ioPutstream(out,inp);
		
		bwrite.close();
		inp.close();
		out.close();
	     } catch (IOException e) 
  	     {
		e.printStackTrace();
	     }
  	
     }
    
    
	public void ioPutstream(OutputStream out,InputStream inp) throws IOException
    {  
		BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));//,"UTF-8"
     String s=readinput(inp);
     while(!s.equals("bye"))
     {
       System.out.println("客户机说："+s);
       s="服务器收到："+s+"\r\n";
       bwrite.write(s,0,s.length());
		bwrite.flush();
       s=readinput(inp);
     }
     s="服务器io终止";
     bwrite.write(s,0,s.length());
		bwrite.flush();
    }
    
    private String readinput(InputStream inp) throws IOException
    {
    	InputStreamReader ipsrd=new InputStreamReader(inp,"GBK");//影响输入时的显示,"UTF-8"
    	BufferedReader br=new BufferedReader(ipsrd);
    	
    	String s=new String();
//    	StringBuffer stbuf=new StringBuffer();
//    	 char c = 0;
//        while(c!=13)//不为回车
//       {
//     	int datai=inp.read();
//    	c=(char)datai;
//	    stbuf.append(c);
//        }
//        s=stbuf.toString().trim();//减除空格和空白部分
    	
    	s=br.readLine();
        return s;
    }
	
}
