package com.paracamplus.ilp2.ilp2tme5.compiler;


import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp2.ilp2tme5.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedBreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedContinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedLoop;

public class GlobalVariableCollector
extends com.paracamplus.ilp2.compiler.GlobalVariableCollector
implements IASTCvisitor<Set<IASTCglobalVariable>, 
                        Set<IASTCglobalVariable>, 
                        CompilationException> {

	@Override
	public Set<IASTCglobalVariable> visit(IASTbreak iast, Set<IASTCglobalVariable> data) throws CompilationException {
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTcontinue iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTnamedLoop iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		data = iast.getCondition().accept(this, data);
		data = iast.getBody().accept(this, data);
        return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTnamedBreak iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTnamedContinue iast, Set<IASTCglobalVariable> data)
			throws CompilationException {
		return data;
	}

	

}
