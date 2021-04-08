package prova3bi.Cinema.Application.Singletons;

import prova3bi.Cinema.Domain.Entities.Login;

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
	
	public void Reset() {
		ResetLogin();
	}
	
	public static LoginHolder getInstance() {
		return holder;
	}

}
