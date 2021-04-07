package prova3bi.Cinema.Services;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Repositories.ChairRepository;
import prova3bi.Cinema.Data.Repositories.LoginRepository;
import prova3bi.Cinema.Data.Repositories.MovieRepository;
import prova3bi.Cinema.Data.Repositories.RoomRepository;
import prova3bi.Cinema.Data.Repositories.SessaoRepository;
import prova3bi.Cinema.Data.Repositories.TicketRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ITicketRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IChairService;
import prova3bi.Cinema.Domain.Interfaces.Services.ILoginService;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;
import prova3bi.Cinema.Domain.Interfaces.Services.IRoomService;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;
import prova3bi.Cinema.Domain.Interfaces.Services.ITicketService;

// USE ESSES METODOS PARA OBTER INSTANCIAS
// ISSO EVITA ERROS DE :
// - PERSISTENCIA
// - CONCORRENCIA
// - MULTIPLA REFERENCIA
// TUDO ISSO ENQUANTO MANTEM O PRINCIPIO DE INVERSAO DE CONTROLE
// A ALTERNATIVA SERIA A CRIACAO DE UM CONTAINER DE INJECAO DE DEPENDENCIA
public class UnitFactory {
	private static DBContext context;
	private static IChairRepository chairRepo;
	private static IMovieRepository movieRepo;
	private static IRoomRepository roomRepo;
	private static ISessaoRepository sessionRepo;
	private static ILoginRepository loginRepo;
	private static ILoginService loginService;
	private static IRoomService roomService;
	private static IMovieService movieService;
	private static IChairService chairService;
	private static ISessionService sessionService;
	private static ITicketRepository ticketRepo;
	private static ITicketService ticketService;

	public static DBContext getContext() {
		if (context == null)
			context = new DBContext();
		return context;
	}

	public static ILoginRepository getLoginRepo() {
		if (loginRepo == null)
			loginRepo = new LoginRepository(getContext());
		return loginRepo;
	}

	public static IChairRepository getChairRepo() {
		if (chairRepo == null)
			chairRepo = new ChairRepository(getContext());
		return chairRepo;
	}

	public static IMovieRepository getMovieRepo() {
		if (movieRepo == null)
			movieRepo = new MovieRepository(getContext());
		return movieRepo;
	}

	public static ISessaoRepository getSessionRepo() {
		if (sessionRepo == null)
			sessionRepo = new SessaoRepository(getContext());
		return sessionRepo;
	}

	public static IRoomRepository getRoomRepo() {
		if (roomRepo == null)
			roomRepo = new RoomRepository(getContext());
		return roomRepo;
	}

	public static ISessionService getSessionService() {
		if (sessionService == null)
			sessionService = new SessionService(getSessionRepo(), getRoomService(), getMovieService(), getChairRepo());
		return sessionService;
	}

	public static ILoginService getLoginService() {
		if (loginService == null)
			loginService = new LoginService(getLoginRepo());
		return loginService;
	}

	public static IRoomService getRoomService() {
		if (roomService == null)
			roomService = new RoomService(getRoomRepo());
		return roomService;
	}

	public static IMovieService getMovieService() {
		if (movieService == null)
			movieService = new MovieService(getMovieRepo());
		return movieService;
	}

	public static ITicketRepository getTicketRepository() {
		if (ticketRepo == null)
			ticketRepo = new TicketRepository(getContext());
		return ticketRepo;
	}

	public static ITicketService getTicketService() {
		if (ticketService == null)
			ticketService = new TicketService(getTicketRepository(), getChairService());
		return ticketService;
	}

	public static IChairService getChairService() {
		if (chairService == null)
			chairService = new ChairService(getChairRepo(), getSessionService());
		return chairService;
	}
}
