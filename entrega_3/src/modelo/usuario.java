package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class usuario {
	
	private String Login;
	private String Password;
	
	
	public usuario(String log, String pword) {
		this.Login = log;
		this.Password = pword;
	}
	
	public String getLogin() {
		return this.Login;
		
	}
	
	public String getPassword() {
		return this.Password;
	}

	public static Boolean entrar(String Login, String Password, ArrayList<usuario> lista) {
		boolean enter = false; 
		boolean centinela = true;
		int a =0;
		while(centinela & a<lista.size()) {
			usuario user = lista.get(a);
			String logUser = user.getLogin();
			
			if(Login.equals(logUser)) {
				centinela=false;
				if (Password.equals(user.getPassword())) {
					enter = true;
				}else {
					enter = false;
				}
			}
			a+=1;
		}
		return enter;
	}
	public void setPassword(String pword) {
		this.Password = pword;
	}
	public void setLogin(String login) {
		Login = login;
	}

}
