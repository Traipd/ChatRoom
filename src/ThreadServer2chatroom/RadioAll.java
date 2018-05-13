package ThreadServer2chatroom;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RadioAll {
	private Socket[] sk=new Socket[0];
	public void setSocket(Socket sk)
	{
		Socket[] sk2=new Socket[this.sk.length+1];
		if(this.sk.length!=0)
		{System.arraycopy(this.sk, 0, sk2, 0, this.sk.length);}
		sk2[this.sk.length]=sk;
		this.sk=sk2;
	}
	public void radioString(String s) throws IOException
	{
		for(int i=0;i<sk.length;i++)
		{
			if(!sk[i].isOutputShutdown())
			 {
	          OutputStream out=sk[i].getOutputStream();
			   BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));
			   String s2="服务器收到："+s+"\r\n";
			   bwrite.write(s2,0,s2.length());
				bwrite.flush();
			}
		}
	}
	/***连接断开信息发送，从需广播的Socket队列中删除次Socket***/
	public void sayBye(Socket isk) throws IOException
	{
		int deli=0;
		for(int i=0;i<sk.length;i++)
		{
			
				OutputStream out=sk[i].getOutputStream();
			   BufferedWriter bwrite=new BufferedWriter(new OutputStreamWriter(out,"GBK"));
		       String s2="用户断开连接"+"\r\n";
		       bwrite.write(s2,0,s2.length());
				bwrite.flush();
				if(isk.equals(sk[i])){deli=i;}
		}
		Socket[] sk2=new Socket[sk.length-1];
		 if(deli<this.sk.length-1)
		 {System.arraycopy(sk, 0, sk2, 0, deli);
		 System.arraycopy(sk, deli+1, sk2, deli, sk2.length-deli);
		 }
		 else
		 {System.arraycopy(sk, 0, sk2, 0, sk2.length);}
		 sk=sk2;
	}
	
}
