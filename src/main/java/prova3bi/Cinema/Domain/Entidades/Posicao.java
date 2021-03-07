package prova3bi.Cinema.Domain.Entidades;

public class Posicao {
	private int coluna;
	private char linha;
	public Posicao(int coluna, char linha) {
		this.coluna = coluna;
		this.linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	public char getLinha() {
		return linha;
	}
	public void setLinha(char linha) {
		this.linha = linha;
	}
}
