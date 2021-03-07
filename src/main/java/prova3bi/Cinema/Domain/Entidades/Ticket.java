package prova3bi.Cinema.Domain.Entidades;

public class Ticket extends Entidade {

	public Ticket(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	public TicketStatus status;
	public Poltrona poltrona;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
