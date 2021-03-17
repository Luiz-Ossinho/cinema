package prova3bi.Cinema.Data.Abstractions;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// DEFINE O TIPO DO CONSTRUTOR
// USADO NA CRIACAO DE INSTANCIAS VIA REFLECTION DO CONVERSOR GENERICO
// Is.Read é para os constutores de leitura de banco de dados
// CADA PARAMETRO DE UM CONSTRUTOR Is.Read DEVE TER O MESMO NOME DA COLUNA QUE ELE REPRESENTA NO BANCO
// PRECISA EXISTIR UM PARAMETRO int {nomeTablea}ID para ler o ID
// TODOS OS PARAMETROS PRECISAM ESTAR EM ORDEM ALFABETICA
// PARAMETROS ENUM PODEM SER USADOS COM O PROPIO TIPO ENUM
// PARAMETROS LocalDateTime DEVEM RECEBER COMO STRING E CONVERTER APROPIADAMENTE
// PARAMETROS QUE SAO OUTRAS ENTIDADES DEVEM RECEBER APENAS O ID DELAS
// E CHAMAR O CONSTRUTOR TEMPORARIA
// DEPOIS, SE NECESSARIO, NA CAMADA DE SERVICO, RE-ATRIBUIR A ENTIDADE USANDO O ID
// Is.Insert é usado para criar novas instancias a serem inseridas
// Is.Temp armazena apenas o ID da instancia para substituir depois com a instancia correta
// USE-O APENAS SE NECESSARIO
@Retention(RUNTIME)
@Target(CONSTRUCTOR)
public @interface Builder {
	enum Is {
		Read, Insert, Temp;
	}

	Is value();

}
//PS: CODIGO PARA VER ORDEM ALFABETICA DOS PARAMETROS
// PODE USAR NO https://www.onlinegdb.com/online_java_compiler
//import java.util.*;
//public class Main
//{
//   	private static Comparator<String> alphabeticComparator = new Comparator<String>() {
//		@Override
//		public int compare(String o1, String o2) {
//			return o1.compareTo(o2);		
//		}
//	};
//	public static void main(String[] args) {
//	    List<String> params = new ArrayList<String>();
//		params.add("parametro1");
//		params.add("parametro2");
//		for(String param : params){
//		    System.out.print(param+" ");
//		}
//	}
//}