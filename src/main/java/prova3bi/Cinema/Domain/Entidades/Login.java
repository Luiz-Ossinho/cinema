package prova3bi.Cinema.Domain.Entidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Builder.Is;
import prova3bi.Cinema.Domain.Validations.ErrorList;
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

	@Builder(Is.Read)
	public Login(int LoginsId, String hash, NivelPermissao nivelPermissao, String usuario) {
		super(LoginsId);
		this.user = usuario;
		this.hash = hash;
		this.nivelPermissao = nivelPermissao;
	}

	// Retorna uma hash MD5 equivalente a string inserida
	// Usuado na criptografia de senhas
	public static String Hash(String str) {
		MessageDigest m = null;
        String md5hash = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
                 e.printStackTrace();
        }        
        if(m != null) {
              m.update(str.getBytes(),0,str.length());
              BigInteger i = new BigInteger(1, m.digest());
              md5hash = String.format("%1$032x", i);
          }
       return md5hash;
	}

	@Override
	public ErrorList isValid() {
		// TODO Auto-generated method stub
		return null;
	}

}
