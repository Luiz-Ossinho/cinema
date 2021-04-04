package prova3bi.Cinema.Services;

import java.util.ArrayList;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Nest;
import prova3bi.Cinema.Domain.Entidades.Poltrona;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.Sessao;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;
import prova3bi.Cinema.Domain.Interfaces.Services.IRoomService;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;

public class SessionService implements ISessionService {
	private IRoomService roomService;
	private IMovieService movieService;
	private ISessaoRepository sessionRepo;
	private IChairRepository chairRepo;

	public SessionService(ISessaoRepository sessionRepo, IRoomService roomService, IMovieService movieService,
			IChairRepository chairRepo) {
		this.sessionRepo = sessionRepo;
		this.roomService = roomService;
		this.movieService = movieService;
		this.chairRepo = chairRepo;
	}

	@Override
	public Sessao Add(Sessao session) {
		if (Nest.Action(session.filme) == Nest.New)
			session.filme = movieService.Add(session.filme);
		if (Nest.Action(session.sala) == Nest.New)
			session.sala = roomService.Add(session.sala);

		var generatedKey = sessionRepo.Add(session);
		session.setId(generatedKey);

		addAnyChairs(session);

		return session;
	}

	private void addAnyChairs(Sessao session) {
		var NearestSmallerSquare = getSmallerNearestSquare(session.sala.numPoltronas);
		var squareRoot = (int) Math.sqrt(NearestSmallerSquare);
		for (int i = 0; i < squareRoot; i++) {
			for (int j = 0; j < squareRoot; j++) {
				var poltrona = new Poltrona(session, getColumnLetters(j), i + 1);
				chairRepo.Add(poltrona);
				session.poltronas.add(poltrona);
			}
		}
		var excesso = session.sala.numPoltronas - NearestSmallerSquare;
		int columnsExtra = 1;
		for (int i = 1; i <= excesso; i++) {
			var letraExtra = getColumnLetters((squareRoot - 1) + columnsExtra);
			if ((i % squareRoot) == 0)
				columnsExtra++;

			int rowExtra = i;
			if (i > squareRoot) {
				rowExtra = (i % squareRoot);
				if (rowExtra == 0)
					rowExtra = squareRoot;
			}

			var poltrona = new Poltrona(session, letraExtra, rowExtra);
			chairRepo.Add(poltrona);
			session.poltronas.add(poltrona);
		}
	}

	private static int getSmallerNearestSquare(int integer) {
		int NearestSmallerSquare = -1;
		for (int i = integer; i > -1; i--) {
			var sqrt = Math.sqrt(i);
			if ((sqrt - Math.floor(sqrt)) == 0) {
				NearestSmallerSquare = i;
				break;
			}
		}
		return NearestSmallerSquare;
	}

	private static String[] lettersArray = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private static String getColumnLetters(int columnNumber) {
		var letters = "";
		if (columnNumber <= 26) {
			letters += lettersArray[columnNumber];
		} else {
			letters += getColumnLetters((int) columnNumber / 26);
			letters += getColumnLetters(columnNumber % 26);
		}
		return letters;
	}

	@Override
	public List<Sessao> GetNext() {
		var allSessions = sessionRepo.GetAll();
		var nextSessions = new ArrayList<Sessao>();
		for (var sessao : allSessions) {
			if (sessao.verEstado() != Sessao.Estado.JaTerminou) {
				sessao.filme = movieService.Get(sessao.filme.getId());
				sessao.sala = roomService.Get(sessao.sala.getId());
				var poltronas = chairRepo.GetAllFromSession(sessao.getId());
				for (var poltrona : poltronas) {
					poltrona.sessao = sessao;
					sessao.poltronas.add(poltrona);
				}
				nextSessions.add(sessao);
			}
		}
		return nextSessions;
	}

}
