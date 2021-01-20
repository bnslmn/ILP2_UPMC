package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.parser.ilpml.ILPMLParser;

public class ILPMLOptimizingParser extends ILPMLParser{
	CopyTransform<Void> copy ;

	public ILPMLOptimizingParser(IASTfactory factory) {
		super(factory);
		copy =  new CopyTransform<>(factory);
	}
	@Override
    public IASTprogram getProgram() throws ParseException {
		try {
			return copy.visit(super.getProgram(), null);
		} catch (CompilationException e) {
			throw new ParseException(e);
		}
		
    }
	

}