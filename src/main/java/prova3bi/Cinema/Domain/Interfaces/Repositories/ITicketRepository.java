package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entidades.Ticket;

public interface ITicketRepository {
	public Ticket Get(int TicketsID);
	public int Put(Ticket ticket);
}
