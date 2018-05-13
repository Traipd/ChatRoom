package ThreadServer2chatroom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import UserList.User;
import UserList.UserList;

public class EnsureUser {
    
	private OutputStream out;
	private InputStream inp;
	private String sr=new String();
	private String sc=new String();
	private UserList userl;
	
	
	private String turename;
	private String turepassword=new String();

	public void setUserList(UserList userl)
	{this.userl=userl;}
	
	public EnsureUser(OutputStream out,InputStream inp)
	{
		this.out=out;this.inp=inp;
	}
	
	public User enSure() throws IOException
	{
		boolean flag=false;
		InputStreamReader ipsrd=new InputStreamReader(inp,"UTF-8");//影响输入时的显示
    	BufferedReader br=new BufferedReader(ipsrd);
    	
    	sc=br.readLine();
    	sr=enName();
    	if(sr==null){return null;}
    	sc=br.readLine();
    	turepassword=new String(sr);
		flag=enPassword();
	    	if(flag)
	    	{
	    		User u=new User(turename,turepassword);
	    		flag=userl.setUser(u);
          	return u;
	    	}
		return null;
	}
//	public boolean enSure() throws IOException
//	{
//		boolean flag=false;
//		InputStreamReader ipsrd=new InputStreamReader(inp,"UTF-8");//影响输入时的显示
//    	BufferedReader br=new BufferedReader(ipsrd);
//    	
//    	sc=br.readLine();
//    	sr=enName();
//    	if(sr==null){return flag;}
//    	sc=br.readLine();
//    	turepassword=new String(sr);
//		flag=enPassword();
//	    	if(flag)
//	    	{
//	    		User u=new User(turename,turepassword);
//	    		flag=userl.setUser(u);
//	    	}
//		return flag;
//	}
	
	public String enName() throws IOException//返回对应用户的秘码
	{
		InputStreamReader in=new InputStreamReader(new FileInputStream("D:/Filetest/Username.txt"),"GBK");//编码方式设置为一致
		BufferedReader br=new BufferedReader(in);
		String brs;
		int i=0;
		while((brs=br.readLine())!=null)
		{   
			if(brs.equals(sc))
			{
				turename=new String(sc);
				brs=readPassword(i);
				break;
			}
			i++;
		}
		in.close();
		br.close();
		return brs;
	}
	
	public boolean enPassword()
	{
		boolean flag=false;
		if(sc.equals(sr)){return true;}
		return flag;
	}
	
	public String readPassword(int i) throws IOException
	{
		InputStreamReader in=new InputStreamReader(new FileInputStream("D:/Filetest/Userpassword.txt"),"GBK");//编码方式设置为一致
		BufferedReader br=new BufferedReader(in);
        
		for(int j=0;j<i;j++)
		{br.readLine();}
		
		return br.readLine();
	}
}
