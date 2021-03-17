package prova3bi.Cinema.Data.Abstractions;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// DESCREVE A TABELA
// POR CONVENCAO, A TABELA SEMPRE VAI TER UMA CHAVE PRMARIA IGUAL A
// "{nomeTabela}ID"
// CADA CHAVE ESTRANGEIRA DEVE SER DESCRITA DA SEGUINTE MANEIRA
// "{nomeDaColuna};{nomeDaTabelaEstrangeira}"
// POR CONVENCAO, SEMRPE SERA USADO A PK DA TABELA ESTRANGEIRA
@Retention(RUNTIME)
@Target(TYPE)
public @interface Table {
	String nome();

	String[] fks() default {};
}
