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
	public Ticket QuitarTicket(int TicketsID) {
		var ticket = ticketRepo.Get(TicketsID);
		ticket.Quitar();
		ticketRepo.Put(ticket);
		return ticket;
	}
}
