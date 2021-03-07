package prova3bi.Cinema.Data.Abstractions;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Comparator;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String nome();
	Class<?> tipo();
	String tipoSql();
	public static Comparator<Column> comp = new Comparator<Column>() {
		@Override
		public int compare(Column o1, Column o2) {
			int i = o1.nome().compareTo(o2.nome());
            return i;
		}
    };
}
