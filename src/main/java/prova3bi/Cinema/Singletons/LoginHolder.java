package prova3bi.Cinema.Singletons;

import prova3bi.Cinema.Domain.Entidades.Login;

public final class LoginHolder {
	private Login login;
	
	private static LoginHolder holder = new LoginHolder();
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public void ResetLogin() {
		this.login = null;
	}
	
	public static LoginHolder getInstance() {
		return holder;
	}

}
