package com.alodiga.hsm.exception;

public class NameByIdentificationNumberNotFoundException extends Exception {

	/**
	 * Esta exception se es lanzada cuando por cualquier motivo no se puede acceder a la pagina del CNE
	 */
	private static final long serialVersionUID = 1L;

	public NameByIdentificationNumberNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NameByIdentificationNumberNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NameByIdentificationNumberNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NameByIdentificationNumberNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NameByIdentificationNumberNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
