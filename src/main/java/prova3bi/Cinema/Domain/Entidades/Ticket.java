package prova3bi.Cinema.Domain.Entidades;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

@Table(nome = "Tickets", fks = { "poltrona;Poltronas"})
public class Ticket extends Entidade {
	
	@Builder(Is.Read)
	public Ticket(int TicketsID, TicketStatus status, int poltrona) {
		super(TicketsID);
		this.status = status;
		this.poltrona = new Poltrona(poltrona);
	}
	
	@Builder(Is.Insert)
	public Ticket(Poltrona poltrona) {
		super(-1);
		this.poltrona = poltrona;
	}
	
	@Column(nome = "status", tipoSql = "INTEGER")
	public TicketStatus status;
	@Column(nome = "poltrona", tipoSql = "INTEGER")
	public Poltrona poltrona;

	public void Quitar() {
		this.status = TicketStatus.Quitado;
	}
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
