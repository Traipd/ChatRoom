package Threadserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {

	public static void main(String[] args) {
		ServerSocket server;
		int number=0;
		try {
			server = new ServerSocket(9090);	
			while(true)
			{
			Socket sk=server.accept();//在这里停顿,产生新的Socket
			MyThreadServer mts=new MyThreadServer(sk,number);
			mts.start();
			number++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
