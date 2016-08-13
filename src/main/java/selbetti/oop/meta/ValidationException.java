package selbetti.oop.meta;

/**
 *
 */
public class ValidationException extends RuntimeException {

	public ValidationException( String failureFeedback ){
		super( failureFeedback );
	}
}
