package selbetti.oop.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@RequiredArgsConstructor
public class BertucciValidator {

	final List<BertucciField> fields;

	public boolean isValid( Object object ) throws IllegalAccessException {

		for ( final BertucciField field : fields ) {
			field.apply(object);
		}

		return true;
	}

	public static BertucciValidator create( Class<?> clazz ) {
		final List<BertucciField> fields = new ArrayList<>();

		while( !Object.class.equals( clazz ) ) {
			for ( Field field : clazz.getDeclaredFields()) {

				BertucciField bf = new BertucciField(field);

				for (Annotation annotation : field.getAnnotations())
					checkAndAddAnnotationType(bf, annotation);

				if(!bf.validations.isEmpty())
					fields.add(bf);
			}
			clazz = clazz.getSuperclass();
		}
		return new BertucciValidator( fields );
	}

	private static void checkAndAddAnnotationType(BertucciField bf, Annotation annotation) {
		if ( annotation.equals( ValidRegExp.class ) ) {
			bf.validations.add(value -> {
					if(!Validations.isValidRegExp( (String)value, ((ValidRegExp)annotation).value() ) )
						throw new ValidationException( "Field value( " + bf.field + ") does not matches " + ((ValidRegExp)annotation).value() );
				});
		}
		if ( annotation.equals(Size.class ) ) {
			bf.validations.add(value -> {
				if ( value == null || ((List)value).size() < ((Size)annotation).min() )
					throw new ValidationException( "Field value( " + bf.field + ") is not " + ((Size)annotation).min() + " size longer (or is null)" );
			});
		}
		if ( annotation.equals( Range.class ) ) {
			bf.validations.add(value -> {
				if ( !Validations.isAtLeastLonger( (String)value, ((Range)annotation).min() )
						||   !Validations.isAtMostLonger( (String)value, ((Range)annotation).max() ) )
					throw new ValidationException( "Field value( " + bf.field + ") does meet range criteria [min=" + ((Range)annotation).min() + ", max="+((Range)annotation).max()+"]" );
			});
		}
		if ( annotation.equals( DoesNotContains.class ) ) {
			bf.validations.add(value-> {
				if (!Validations.doesNotContains((String) value, ((DoesNotContains)annotation).value()))
					throw new ValidationException("Field value( " + bf.field + ") is null or contains " + ((DoesNotContains)annotation).value());
			});
		}
	}


}

class BertucciField{
	Field field;
	List<Consumer<Object>> validations = new ArrayList<>();

	public BertucciField(Field field) {
		this.field = field;
	}

	void apply(Object object) throws IllegalAccessException {
		final Object value = field.get( object );
		for ( Consumer<Object> validation : validations)
			validation.accept( value );
	}
}
