package prova3bi.Cinema.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import prova3bi.Cinema.Data.Abstractions.Nest;
import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
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
	public Session Add(Session session) {
		if (Nest.Action(session.filme) == Nest.New)
			session.filme = movieService.Add(session.filme);
		if (Nest.Action(session.sala) == Nest.New)
			session.sala = roomService.Add(session.sala);

		var generatedKey = sessionRepo.Add(session);
		session.setId(generatedKey);

		addAnyChairs(session);

		return session;
	}

	private void addAnyChairs(Session session) {
		var nearestSmallerSquare = getSmallerNearestSquare(session.sala.numPoltronas);
		var squareRoot = (int) Math.sqrt(nearestSmallerSquare);

		var SquareMatrix = GetSquareMatrix(squareRoot, session);

		var remainder = session.sala.numPoltronas - nearestSmallerSquare;

		var RemainderMatrix = GetRemainderMatrix(remainder, squareRoot, session);
		session.chairs = appendMatrixes(SquareMatrix, RemainderMatrix);
	}

	private Chair[][] GetEmptyMatrix(List<Chair> list) {
		var width = getWidth(list);
		var length = getLenght(list);
		return new Chair[width][length];
	}

	private Chair[][] GetSquareMatrix(int squareRoot, Session session) {
		var SquareMatrix = new Chair[squareRoot][squareRoot];
		for (int i = 0; i < squareRoot; i++) {
			for (int j = 0; j < squareRoot; j++) {
				var poltrona = new Chair(session, getColumnLetters(j), i + 1);
				SquareMatrix[i][j] = poltrona;
				chairRepo.Add(poltrona);
			}
		}
		return SquareMatrix;
	}

	private Chair[][] GetRemainderMatrix(int remainder, int squareRoot, Session session) {
		int sizeRemainingColumns = (int) Math.ceil(((double) remainder / squareRoot));
		var RemainderMatrix = new Chair[sizeRemainingColumns][squareRoot];

		int columnsExtra = 1;

		for (int i = 1; i <= remainder; i++) {
			var letraExtra = getColumnLetters((squareRoot - 1) + columnsExtra);
			if ((i % squareRoot) == 0)
				columnsExtra++;

			int rowExtra = i;
			if (i > squareRoot) {
				rowExtra = (i % squareRoot);
				if (rowExtra == 0)
					rowExtra = squareRoot;
			}

			var poltrona = new Chair(session, letraExtra, rowExtra);
			RemainderMatrix[columnsExtra - 1][rowExtra - 1] = poltrona;
			chairRepo.Add(poltrona);
		}

		return RemainderMatrix;
	}

	private Chair[][] appendMatrixes(Chair[][] A, Chair[][] B) {
		var totalLength = A.length + B.length;
		var appended = new Chair[totalLength][A[0].length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				appended[i][j] = A[i][j];
			}
		}
		for (int i = A.length; i < totalLength; i++) {
			for (int j = 0; j < A[0].length; j++) {
				appended[i][j] = B[i - A.length][j];
			}
		}
		return appended;
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

	private static ArrayList<String> lettersList = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

	private static String getColumnLetters(int columnNumber) {
		var letters = "";
		if (columnNumber < 26) {
			letters += lettersList.get(columnNumber);// [];
		} else {
			letters += getColumnLetters((int) columnNumber / 26);
			letters += getColumnLetters(columnNumber % 26);
		}
		return letters;
	}

	@Override
	public List<Session> GetNext() {
		var allSessions = sessionRepo.GetAll();
		var nextSessions = new ArrayList<Session>();
		for (var sessao : allSessions) {
			if (sessao.verEstado() != Session.State.JaTerminou) {
				sessao.filme = movieService.Get(sessao.filme.getId());
				sessao.sala = roomService.Get(sessao.sala.getId());
				var poltronas = chairRepo.GetAllFromSession(sessao.getId());
				sessao.chairs = GetEmptyMatrix(poltronas);
				for (var poltrona : poltronas) {
					poltrona.sessao = sessao;
					var i = lettersList.indexOf(poltrona.column);
					var j = poltrona.row - 1;
					sessao.chairs[i][j] = poltrona;
				}
				nextSessions.add(sessao);
			}
		}
		return nextSessions;
	}

	public int getWidth(List<Chair> list) {
		int largestLetter = Integer.MIN_VALUE;
		for (var chair : list) {
			var letter = chair.column;
			var numLetter = lettersList.indexOf(letter);
			if (largestLetter < numLetter)
				largestLetter = numLetter + 1;
		}

		return largestLetter;
	}

	public int getLenght(List<Chair> list) {
		int largest = Integer.MIN_VALUE;
		for (var chair : list) {
			if (largest < chair.row)
				largest = chair.row;
		}
		return largest;
	}
}
