package prova3bi.Cinema.Data.Repositories;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Ticket;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ITicketRepository;

public class TicketRepository implements ITicketRepository {

	private DBContext context;

	public TicketRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public Ticket Get(int TicketsID) {
		var query = new Query<Ticket>(Query.Comand.Select, Query.Modifiers.Limit1, Ticket.class)
				.PKEquals(TicketsID + "");

		return context.get(query);
	}

	@Override
	public int Put(Ticket ticket) {
		var query = new Query<Ticket>(Query.Comand.Update, Ticket.class)
				.value(ticket.status.valor() + "", "status")
				.value(ticket.poltrona.getId() + "", "poltrona")
				.PKEquals(ticket.getId() + "");

		return context.execute(query);
	}

	@Override
	public int Add(Ticket ticket) {
		var query = new Query<Ticket>(Query.Comand.Insert, Ticket.class)
				.value(ticket.status.valor() + "", "status")
				.value(ticket.poltrona.getId() + "", "poltrona");

		return context.execute(query);
	}

}
