package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.NormalizationEnvironment;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.parser.ilpml.ILPMLParser;

public class ILPMLOptimizingParser extends ILPMLParser{
	CopyTransform<Void> copy ;
	RenameTransform rename;

	public ILPMLOptimizingParser(IASTfactory factory) {
		super(factory);
		//copy =  new CopyTransform<>(factory);
		rename = new RenameTransform(factory);
		
	}
	@Override
    public IASTprogram getProgram() throws ParseException {
		try {
			//return copy.visit(super.getProgram(), null);
			return rename.visit(super.getProgram(), NormalizationEnvironment.EMPTY);
			
		} catch (CompilationException e) {
			throw new ParseException(e);
		}
		
    }
	

}