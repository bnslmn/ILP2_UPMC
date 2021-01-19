package com.paracamplus.ilp2.ilp2tme5.exceptions;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public class BreakException extends EvaluationException {
	
	private static final long serialVersionUID = 2254197313166886218L;
	private String name = null;

	public BreakException(String errorMessage) {
        super(errorMessage);
    }
	
	public BreakException(String errorMessage, String name) {
        super(errorMessage);
        this.name = name;
    }
	
	public String getName() {
		return name;
	}
}
