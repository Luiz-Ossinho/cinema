package prova3bi.Cinema.Services;

import java.util.ArrayList;
import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Ticket;
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

	@Override
	public Ticket Create(Chair chair) {
		var ticket = new Ticket(chair);
		var ticketId = ticketRepo.Add(ticket);
		ticket.setId(ticketId);

		return ticket;
	}

	@Override
	public List<Ticket> CreateAll(List<Chair> chairs) {
		var returnList = new ArrayList<Ticket>();
		for (var chair : chairs) {
			returnList.add(Create(chair));
		}
		return returnList;
	}
}
