package br.com.neki.exception;

public class EmailOrPasswordNotValidException extends Exception {


	private static final long serialVersionUID = 4190994537534330965L;

	public EmailOrPasswordNotValidException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailOrPasswordNotValidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmailOrPasswordNotValidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmailOrPasswordNotValidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmailOrPasswordNotValidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
