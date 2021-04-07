package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Ticket;

public interface ITicketService {
	public Ticket QuitarTicket(int TicketsID);
	
	public Ticket Get(int ticketId);

	public Ticket Create(Chair chair);

	public List<Ticket> CreateAll(List<Chair> chairs);
}
