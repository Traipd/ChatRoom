package Threadserver1Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyThreadServer extends Thread{
	private Socket sk;
	private int i;
	public MyThreadServer(Socket sk)
	{this.sk=sk;}
	public MyThreadServer(Socket sk,int i)
	{this.sk=sk;this.i=i;}
	
	public void run()
	   {
	  	try {
		
			OutputStream out=sk.getOutputStream();
			InputStream inp=sk.getInputStream();
			
			BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));//输出时的编码方式
			
			String s="请登陆"+i+"\r\n";
			byte[] data=s.getBytes();
			bwrite.write(s,0,s.length());
			bwrite.flush();
			
			EnsureUser eu=new EnsureUser(out,inp);	
			while(!eu.enSure()){
				s="登陆失败，请重试"+i+"\r\n";
				data=s.getBytes();
				bwrite.write(s,0,s.length());
				bwrite.flush();
			}
			
			
				
			s="hello,my computer,邹俊"+i+"\r\n";
			data=s.getBytes();
			byte[] indata=new byte[64];
			bwrite.write(s,0,s.length());
			bwrite.flush();
			this.ioPutstream(out,inp);
			
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
			BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));
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
	    	InputStreamReader ipsrd=new InputStreamReader(inp,"UTF-8");//影响输入时的显示
	    	BufferedReader br=new BufferedReader(ipsrd);
	    	
	    	String s=new String();

	    	s=br.readLine();
	        return s;
	    }
		
}
