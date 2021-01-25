


import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Serveur_Ftp  extends Thread {
	private int port = 8015;
	private Socket client= new Socket();
	private String user = "username";
	private String pass = "password";
	DataOutputStream succ = null;
	BufferedReader suc = null ; 
	boolean fonc = false;
	

	public void execute(){ 
	
	try{
		  suc = new BufferedReader (new InputStreamReader(client.getInputStream()));
		// create data output stream
		   succ = new  DataOutputStream (client.getOutputStream());
		   InetAddress host = InetAddress.getLocalHost();
		   ServerSocket ftp = new ServerSocket(port,30,host);
		  while(fonc==false) {
			  
			// write to the output stream from the string
			  //service pret pour un nouvel utilisateur.
			  succ.writeBytes("220 Server REady\r\n");
			  String login = suc.readLine();
			  System.out.println("the login is :"+login);
			  
			  if(login.contentEquals(user)) {
				  // ahthentification reussie
				  succ.writeBytes("331 user ok  ");
				  String n = suc.readLine();
				  if(n.contentEquals(pass)) {
					  // ahthentification reussie
					  succ.writeBytes("230 user logged suc\r\n");
				  }else {
					  //mot de passe incorrecte
					  succ.writeBytes("430 password incorrect\r\n");
				  }
			  }else {
				//identifiant incorrecte
				  succ.writeBytes("430 login incorrect\r\n");
			  }
			  
		  }
		  suc.close();
		  
				  
	  }catch(IOException ex){
	    System.out.println("somethin wrong happend !!!");
	    ex.printStackTrace();
	  }finally {
		  try {
			  while(fonc = true) {
				  String cmp =suc.readLine();
				  if(cmp.equals("QUIT")) {
					  System.out.println(" I close , good bye");
					  suc.close();
					  
				  }
			  }
		  }catch(final IOException ex) {
			  ex.printStackTrace();
	  }
}
	}
	

public static void main(String[] args ) {
	Serveur_Ftp serveur = new Serveur_Ftp();
}
}
	
	
