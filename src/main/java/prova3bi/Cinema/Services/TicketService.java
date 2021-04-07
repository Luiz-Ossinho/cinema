package prova3bi.Cinema.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Ticket;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ITicketRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IChairService;
import prova3bi.Cinema.Domain.Interfaces.Services.ITicketService;

public class TicketService implements ITicketService {
	private ITicketRepository ticketRepo;
	private IChairService chairService;
	
	public TicketService(ITicketRepository ticketRepo, IChairService chairService) {
		this.ticketRepo = ticketRepo;
		this.chairService = chairService;
	}

	@Override
	public Ticket QuitarTicket(int TicketsID) {
		var ticket = ticketRepo.Get(TicketsID);
		chairService.OccupyChair(ticket.poltrona.getId());
		ticket.Quitar();
		ticket.poltrona = chairService.Get(ticket.poltrona.getId());
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

	@Override
	public Ticket Get(int ticketId) {
		var ticket = ticketRepo.Get(ticketId);
		ticket.poltrona = chairService.Get(ticket.poltrona.getId());
		if(ticket.poltrona.state==Chair.State.Occupied)
			ticket = QuitarTicket(ticketId);
		return ticket;
	}
}
