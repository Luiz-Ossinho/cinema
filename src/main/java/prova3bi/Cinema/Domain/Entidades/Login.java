package prova3bi.Cinema.Domain.Entidades;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import prova3bi.Cinema.Data.Abstractions.Builder;
import prova3bi.Cinema.Data.Abstractions.Column;
import prova3bi.Cinema.Data.Abstractions.Table;

@Table(nome = "Logins", fks = {})
public class Login extends Entidade {
	
	@Column(nome = "user", tipoSql = "NVARCHAR(32)", tipo = String.class)
	public String user;
	@Column(nome = "hash", tipoSql = "NVARCHAR(32)", tipo = String.class)
	public String hash;
	
	@Builder(Builder.Is.Insert)
	public Login(String user, String senha) {
		super(-1);
		this.user = user;
		this.hash = Hash(senha);
	}
	
	//precisa estar em ordem alfabetica
	@Builder(Builder.Is.Read)
	public Login(int Id, String senhaHash, String usuario) {
		super(Id);
		this.user = usuario;
		this.hash = senhaHash;
	}

	public String getNome() {
		return user;
	}

	public String getHash() {
		return hash;
	}

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
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
