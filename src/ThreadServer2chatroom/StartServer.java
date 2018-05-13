package ThreadServer2chatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import UserList.UserList;

public class StartServer {
	
	private static int number=0;
    private static UserList userl=new UserList();
	public void start(){
		ServerSocket server;
		RadioAll rall=new RadioAll();
		
		try {
			server = new ServerSocket(9090);	
			while(true)
			{
			Socket sk=server.accept();//在这里停顿,产生新的Socket
			MyThreadServer mts=new MyThreadServer(sk,this);
			mts.setRadioALL(rall);
			mts.setUserList(userl);
			rall.setSocket(sk);
			mts.start();
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		StartServer.number = number;
	}

}
