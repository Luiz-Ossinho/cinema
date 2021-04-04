package prova3bi.Cinema.Singletons;

import prova3bi.Cinema.Domain.Entidades.Sessao;

public class SessionHolder {
	private Sessao session;
	
	private static SessionHolder holder = new SessionHolder();
	
	public Sessao getSession() {
		return session;
	}
	
	public void setSession(Sessao session) {
		this.session = session;
	}

	public boolean IsEmpty() {
		return this.session == null;
	}
	
	public void ResetSession() {
		this.session = null;
	}
	
	public static SessionHolder getInstance() {
		return holder;
	}
}
