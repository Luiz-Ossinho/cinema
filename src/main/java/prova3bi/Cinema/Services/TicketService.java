package prova3bi.Cinema.Services;

import prova3bi.Cinema.Domain.Entidades.Ticket;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ITicketRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.ITicketService;

public class TicketService implements ITicketService {
	private ITicketRepository ticketRepo;
	
	public TicketService(ITicketRepository ticketRepo) {
		this.ticketRepo = ticketRepo;
	}

	@Override
	public int QuitarTicket(int TicketsID) {
		var ticket = ticketRepo.Get(TicketsID);
		ticket.Quitar();
		return ticketRepo.Put(ticket);
	}
}
