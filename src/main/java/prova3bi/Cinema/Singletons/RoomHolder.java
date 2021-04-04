package prova3bi.Cinema.Singletons;

import javafx.collections.ObservableList;
import prova3bi.Cinema.Domain.Entidades.Sala;

public final class RoomHolder {
	private static RoomHolder holder = new RoomHolder();
	private ObservableList<Sala> obsList;
	private Sala room;
	public ObservableList<Sala> getObsList() {
		return obsList;
	}
	public void setObsList(ObservableList<Sala> obsList) {
		this.obsList = obsList;
	}
	public Sala getRoom() {
		return room;
	}
	public void setRoom(Sala room) {
		this.room = room;
	}
	public void ResetRoom() {
		this.room = null;
	}
	public void ResetObsList() {
		this.obsList = null;
	}
	public boolean IsEmpty() {
		return this.room == null;
	}
	public static RoomHolder getInstance() {
		return holder;
	}
	public void Reset() {
		ResetRoom();
		ResetObsList();
	}
}
