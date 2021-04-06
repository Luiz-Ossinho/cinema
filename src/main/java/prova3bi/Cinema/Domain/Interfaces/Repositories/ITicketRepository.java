package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entities.Ticket;

public interface ITicketRepository {
	public Ticket Get(int TicketsID);
	public int Put(Ticket ticket);
	public int Add(Ticket ticket);
}
