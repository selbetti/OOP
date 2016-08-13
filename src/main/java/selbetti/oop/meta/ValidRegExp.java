package selbetti.oop.meta;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.*;

/**
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidRegExp {

	String value();
}
