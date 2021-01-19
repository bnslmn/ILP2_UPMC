package com.paracamplus.ilp2.ilp2tme5.exceptions;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class ContinueException extends EvaluationException { 
	
	private static final long serialVersionUID = 3488272638088805585L;
	private String name = null;

	public ContinueException(String errorMessage) {
        super(errorMessage);
    }
	
	public ContinueException(String errorMessage, String name) {
        super(errorMessage);
        this.name = name;
    }
	
	public String getName() {
		return name;
	}
}