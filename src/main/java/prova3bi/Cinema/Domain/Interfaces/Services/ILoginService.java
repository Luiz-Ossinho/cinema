package prova3bi.Cinema.Domain.Interfaces.Services;

import prova3bi.Cinema.Domain.Entidades.Login;

public interface ILoginService {
	public Login VerificarUsuario(String nomeUsuario, String senha);
	public int Add(Login login);
	public boolean IsFirstLogin();
}
