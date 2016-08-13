package selbetti.oop.meta;

import java.lang.annotation.*;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {

	int min() default 1;

	int max() default Integer.MAX_VALUE;
}
