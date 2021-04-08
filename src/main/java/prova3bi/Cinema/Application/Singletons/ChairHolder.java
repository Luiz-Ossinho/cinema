package prova3bi.Cinema.Application.Singletons;

import javafx.collections.ObservableList;
import prova3bi.Cinema.Domain.Entities.Chair;

public final class ChairHolder {
	private static ChairHolder holder = new ChairHolder();
	public static ChairHolder GetInstance() {
		return holder;
	}
	private ObservableList<Chair> obsList;
	public ObservableList<Chair> getObsList() {
		return obsList;
	}

	public void setObsList(ObservableList<Chair> obsList) {
		this.obsList = obsList;
	}
	public void Reset() {
		this.obsList = null;
	}
}