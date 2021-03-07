package prova3bi.Cinema.Data.Abstractions;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(CONSTRUCTOR)
public @interface Builder {
	enum Is{Read,Insert;}

	Is value();

}
