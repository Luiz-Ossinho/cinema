package prova3bi.Cinema.Application.Singletons;

import prova3bi.Cinema.Domain.Entities.Session;

public final class SessionHolder {
	private Session session;
	
	private static SessionHolder holder = new SessionHolder();
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	public boolean IsEmpty() {
		return this.session == null;
	}
	
	public void ResetSession() {
		this.session = null;
	}
	
	public void Reset() {
		ResetSession();
		RoomHolder.getInstance().Reset();
		MovieHolder.getInstance().Reset();
	}
	
	public static SessionHolder getInstance() {
		return holder;
	}
}
