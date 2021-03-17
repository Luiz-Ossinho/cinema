package prova3bi.Cinema.Services;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Repositories.ChairRepository;
import prova3bi.Cinema.Data.Repositories.MovieRepository;
import prova3bi.Cinema.Data.Repositories.RoomRepository;
import prova3bi.Cinema.Data.Repositories.SessaoRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;

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
	private static ISessionService sessionService;

	public static DBContext getContext() {
		if (context == null)
			context = new DBContext();
		return context;
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
			sessionService = new SessionService(getSessionRepo(), getRoomRepo(), getMovieRepo(), getChairRepo());
		return sessionService;
	}
}
