package prova3bi.Cinema.Domain.Entidades;

import java.time.LocalDateTime;

public enum EstadoSessao {
	VaiComecar, EmAndamento, Lotada, JaTerminou;

	public static EstadoSessao obterEstado(Sessao sessao) {
		EstadoSessao estado = null;
		if (sessao.DHInicio.isAfter(LocalDateTime.now()))
			estado = VaiComecar;
		else if (sessao.DHInicio.isBefore(LocalDateTime.now()) && sessao.DHTermino.isAfter(LocalDateTime.now()))
			estado = EmAndamento;
		else if (sessao.numPoltronasVagas()<=0)
			estado = Lotada;
		else if (sessao.DHTermino.isBefore(LocalDateTime.now()))
			estado = JaTerminou;
		return estado;
	}
}
