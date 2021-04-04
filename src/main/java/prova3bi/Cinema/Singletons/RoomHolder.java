package prova3bi.Cinema.Singletons;

import prova3bi.Cinema.Domain.Entidades.Sala;

public final class RoomHolder {
	private static RoomHolder holder = new RoomHolder();
	private Sala room;
	public Sala getRoom() {
		return room;
	}
	public void setRoom(Sala room) {
		this.room = room;
	}
	public void ResetRoom() {
		this.room = null;
	}
	public boolean IsEmpty() {
		return this.room == null;
	}
	public static RoomHolder getInstance() {
		return holder;
	}
}
