package prova3bi.Cinema.Domain.Interfaces.Services;

import prova3bi.Cinema.Domain.Entities.Login;

public interface ILoginService {
	public Login VerificarUsuario(String nomeUsuario, String senha);
	public Login Add(Login login);
	public boolean IsFirstLogin();
}
