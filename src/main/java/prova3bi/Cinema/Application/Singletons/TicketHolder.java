package prova3bi.Cinema.Application.Singletons;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Ticket;

public final class TicketHolder {
	private static TicketHolder holder = new TicketHolder();
	public static TicketHolder getInstance() {
		return holder;
	}
	private List<Ticket> ticketsGerados;
	
	public void SetTickets(List<Ticket> tickets) {
		this.ticketsGerados = tickets;
	}
	
	public List<Ticket> GetTickets(){
		return this.ticketsGerados;
	}
	
}
