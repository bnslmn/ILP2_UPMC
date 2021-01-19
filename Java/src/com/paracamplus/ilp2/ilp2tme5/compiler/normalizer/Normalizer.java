package com.paracamplus.ilp2.ilp2tme5.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedBreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedContinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedLoop;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;

public class Normalizer
extends com.paracamplus.ilp2.compiler.normalizer.Normalizer 
implements 
 IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException>{

	public Normalizer(INormalizationFactory factory) {
		super(factory);
	}

	@Override
	public IASTexpression visit(IASTbreak iast, INormalizationEnvironment data) throws CompilationException {
		return ((INormalizationFactory)factory).newBreak();
	}

	@Override
	public IASTexpression visit(IASTcontinue iast, INormalizationEnvironment data) throws CompilationException {
		return ((INormalizationFactory)factory).newContinue();
	}

	@Override
	public IASTexpression visit(IASTnamedLoop iast, INormalizationEnvironment data) throws CompilationException {
		String newname = iast.getName();
		IASTexpression newcondition = iast.getCondition().accept(this, data);
        IASTexpression newbody = iast.getBody().accept(this, data);
        return ((INormalizationFactory)factory).newNamedLoop(newname, newcondition, newbody);
	}

	@Override
	public IASTexpression visit(IASTnamedBreak iast, INormalizationEnvironment data) throws CompilationException {
		String name = iast.getName();
		return ((INormalizationFactory)factory).newNamedBreak(name);
	}

	@Override
	public IASTexpression visit(IASTnamedContinue iast, INormalizationEnvironment data) throws CompilationException {
		String name = iast.getName();
		return ((INormalizationFactory)factory).newNamedContinue(name);
	}
	
}