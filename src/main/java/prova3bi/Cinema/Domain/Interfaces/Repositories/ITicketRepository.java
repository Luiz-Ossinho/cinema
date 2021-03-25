package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entidades.Ticket;

public interface ITicketRepository {
	public Ticket Obter(int Id);
	public void Editar(Ticket ticket);
}
