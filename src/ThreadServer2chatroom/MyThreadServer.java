package ThreadServer2chatroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import UserList.User;
import UserList.UserList;

public class MyThreadServer extends Thread{
	private Socket sk;
	private StartServer sserver;
	private int i;
	private RadioAll rall;
	private UserList userl;
	private User user;
	
	
	public MyThreadServer(Socket sk)
	{this.sk=sk;}
	public MyThreadServer(Socket sk,StartServer sserver)
	{this.sk=sk;this.sserver=sserver;}
	public void setRadioALL(RadioAll rall)
	{this.rall=rall;}
	public void setUserList(UserList userl)
	{this.userl=userl;}
	
	public void run()
	   {
	  	try {
		
			OutputStream out=sk.getOutputStream();
			InputStream inp=sk.getInputStream();
			
			BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));//输出时的编码方式
			
			String s="请登陆"+"\r\n";
			byte[] data=s.getBytes();
			bwrite.write(s,0,s.length());
			bwrite.flush();
			
			EnsureUser eu=new EnsureUser(out,inp);	
			eu.setUserList(userl);
			
			while((user=eu.enSure())==null){
				s="登陆失败，请重试"+"\r\n";
				data=s.getBytes();
				bwrite.write(s,0,s.length());
				bwrite.flush();
			}
			
			i=sserver.getNumber();
				sserver.setNumber(i+1);
			s="hello,my computer,此前有"+i+"人在线\r\n";
			data=s.getBytes();
			byte[] indata=new byte[64];
			bwrite.write(s,0,s.length());
			bwrite.flush();
			
			
			this.ioPutstream(out,inp);
			userl.delUser(user);
				if(userl.empty())
				{
				bwrite.close();
				inp.close();
				out.close();
				}
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
			rall.radioString(s);
	       s=readinput(inp);
	     }
	      
	     s="服务器io终止\r\n";
	     bwrite.write(s,0,s.length());
			bwrite.flush();
			 rall.sayBye(sk);
			sserver.setNumber(sserver.getNumber()-1);
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
