package prova3bi.Cinema.Services;

import java.util.ArrayList;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Nest;
import prova3bi.Cinema.Domain.Entidades.EstadoSessao;
import prova3bi.Cinema.Domain.Entidades.Poltrona;
import prova3bi.Cinema.Domain.Entidades.Sessao;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;

public class SessionService implements ISessionService {

	private ISessaoRepository sessionRepo;
	private IRoomRepository roomRepo;
	private IMovieRepository movieRepo;
	private IChairRepository chairRepo;

	public SessionService(ISessaoRepository sessionRepo, IRoomRepository roomRepo, IMovieRepository movieRepo,
			IChairRepository chairRepo) {
		this.sessionRepo = sessionRepo;
		this.roomRepo = roomRepo;
		this.movieRepo = movieRepo;
		this.chairRepo = chairRepo;
	}

	@Override
	public int Add(Sessao session) {
		if (Nest.Action(session.filme) == Nest.New)
			session.filme.setId(movieRepo.Add(session.filme));
		if (Nest.Action(session.sala) == Nest.New)
			session.sala.setId(roomRepo.Add(session.sala));

		var generatedKey = sessionRepo.Add(session);
		session.setId(generatedKey);

		addAnyChairs(session);

		return generatedKey;
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
			if ((Math.sqrt(i) - Math.floor(Math.sqrt(i))) == 0) {
				NearestSmallerSquare = i;
				break;
			}
		}
		return NearestSmallerSquare;
	}

	private static String[] lettersArray = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private static String getColumnLetters(int columnNumber) {
		String letters = "";
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
		for (Sessao sessao : allSessions) {
			if (sessao.verEstado() != EstadoSessao.JaTerminou) {
				sessao.filme = movieRepo.Get(sessao.filme.getId());
				sessao.sala = roomRepo.Get(sessao.sala.getId());
				var poltronas = chairRepo.GetAllFromSession(sessao.getId());
				for (Poltrona poltrona : poltronas) {
					poltrona.sessao = sessao;
					sessao.poltronas.add(poltrona);
				}
				nextSessions.add(sessao);
			}
		}
		return nextSessions;
	}

}
