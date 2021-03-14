package prova3bi.Cinema.Data.Abstractions;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String nome();

	String tipoSql();
	
	boolean isFK() default false;
}
