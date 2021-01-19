package com.paracamplus.ilp2.ilp2tme5.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp2.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp2.ilp2tme5.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedBreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedContinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedLoop;

public class FreeVariableCollector
extends com.paracamplus.ilp2.compiler.FreeVariableCollector
implements IASTCvisitor<Void, Set<IASTClocalVariable>, CompilationException>{

	public FreeVariableCollector(IASTCprogram program) {
		super(program);
	}

	@Override
	public Void visit(IASTbreak iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}

	@Override
	public Void visit(IASTcontinue iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}

	@Override
	public Void visit(IASTnamedLoop iast, Set<IASTClocalVariable> data) throws CompilationException {
		iast.getCondition().accept(this, data);
		iast.getBody().accept(this, data);
		return null;
	}

	@Override
	public Void visit(IASTnamedBreak iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}

	@Override
	public Void visit(IASTnamedContinue iast, Set<IASTClocalVariable> data) throws CompilationException {
		return null;
	}


}