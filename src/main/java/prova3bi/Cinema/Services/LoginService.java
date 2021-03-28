package prova3bi.Cinema.Services;

import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.ILoginService;

public class LoginService implements ILoginService {

	private ILoginRepository loginRepo;
	
	@Override
	public Login VerificarUsuario(String nomeUsuario, String senha) {
		
		var login = loginRepo.Get(nomeUsuario);
		var hash = Login.Hash(senha);
		if(login.hash == hash) {
			return login;
		}
		return null;
	}

	public LoginService(ILoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}
}
