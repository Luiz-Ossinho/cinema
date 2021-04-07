package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Session;

public interface ISessionService {
	public Session Add(Session session);
	public List<Session> GetNext();
	public Session Get(int id);
}
