package prova3bi.Cinema.Application.Singletons;

import javafx.collections.ObservableList;
import prova3bi.Cinema.Domain.Entities.Room;

public final class RoomHolder {
	private static RoomHolder holder = new RoomHolder();
	private ObservableList<Room> obsList;
	private Room room;
	public ObservableList<Room> getObsList() {
		return obsList;
	}
	public void setObsList(ObservableList<Room> obsList) {
		this.obsList = obsList;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
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
