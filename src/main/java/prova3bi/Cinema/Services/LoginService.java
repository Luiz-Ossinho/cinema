package prova3bi.Cinema.Services;

import prova3bi.Cinema.Domain.Entities.Login;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.ILoginService;

public class LoginService implements ILoginService {

	private ILoginRepository loginRepo;
	
	@Override
	public Login VerificarUsuario(String nomeUsuario, String senha) {
		var login = loginRepo.Get(nomeUsuario);
		var hash = Login.Hash(senha);
		if(login.hash.equals(hash)) {
			return login;
		}
		return null;
	}

	public LoginService(ILoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}

	@Override
	public Login Add(Login login) {
		login.setId(loginRepo.Add(login));
		return login;
	}

	@Override
	public boolean IsFirstLogin() {
		if(loginRepo.GetAll().size()>0)
			return false;
		return true;
	}
}
