package prova3bi.Cinema.Domain.Entidades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

@Table(nome = "Logins")
public class Login extends Entidade {

	@Column(nome = "user", tipoSql = "NVARCHAR(32)")
	public String user;
	@Column(nome = "hash", tipoSql = "NVARCHAR(32)")
	public String hash;
	@Column(nome = "nivelPermissao", tipoSql = "INTEGER")
	public NivelPermissao nivelPermissao;

	@Builder(Is.Insert)
	public Login(String user, String senha, NivelPermissao nivelPermissao) {
		super(-1);
		this.user = user;
		this.hash = Hash(senha);
		this.nivelPermissao = nivelPermissao;
	}

	// os parametros devem ter o mesmo nome da coluna e estar em ordem alfabetica
	@Builder(Is.Read)
	public Login(int LoginsId, String hash, NivelPermissao nivelPermissao, String usuario) {
		super(LoginsId);
		this.user = usuario;
		this.hash = hash;
		this.nivelPermissao = nivelPermissao;
	}

	public static String Hash(String senha) {
		String hash = null;
		try {
			byte messageDigest[] = MessageDigest.getInstance("MD5").digest(senha.getBytes("UTF-8"));
			hash = new String(messageDigest, "UTF-8");
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
