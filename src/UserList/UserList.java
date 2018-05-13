package UserList;

/***用户存储类，只能插入用户名不一样的用户，可删除对应用户名的用户***/
public class UserList {
     private User[] ut=new User[0];
     public boolean setUser(User u)
 	{
    	 if(!hasUser(u))
    	 {
	    	 User[] ut2=new User[this.ut.length+1];
	 		if(this.ut.length!=0)
	 		{System.arraycopy(this.ut, 0, ut2, 0, this.ut.length);}
	 		ut2[this.ut.length]=u;
	 		this.ut=ut2;
	 		return true;
    	 }
 		return false;
 	}
     
     public boolean hasUser(User u)
     {
    	 for(int i=0;i<this.ut.length;i++)
    	 {
    		 if(u.getName().equals(ut[i].getName()))
    		 {return true;}
    	 }
    	 return false;
     }
     public void delUser(User u)
     {
    	 User[] ut2=new User[this.ut.length-1];
    	 for(int i=0;i<this.ut.length;i++)
    	 {
    		 if(u.getName().equals(ut[i].getName()))
    		 {
    			 if(i<this.ut.length-1)
    			 {System.arraycopy(ut, 0, ut2, 0, i);
    			 System.arraycopy(ut, i+1, ut2, i, ut2.length-i);
    			 }
    			 else
    			 {System.arraycopy(ut, 0, ut2, 0, ut2.length);}
    			 ut=ut2;
    			 return;
    		 }
    	 }
     }
     public boolean empty()
     {
    	 if(ut.length==0){return true;}
    	 return false;
     }
}
