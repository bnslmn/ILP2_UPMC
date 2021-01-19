package com.paracamplus.ilp2.ilp2tme5.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTbreak;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTnamedBreak;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTnamedContinue;
import com.paracamplus.ilp2.ilp2tme5.ast.ASTnamedLoop;

public class NormalizationFactory
extends com.paracamplus.ilp2.compiler.normalizer.NormalizationFactory
implements INormalizationFactory{

	@Override
	public IASTexpression newBreak() {
		return new ASTbreak();
	}

	@Override
	public IASTexpression newContinue() {
		return new ASTcontinue();
	}

	@Override
	public IASTexpression newNamedLoop(String name, IASTexpression condition, IASTexpression body) {
		return new ASTnamedLoop(name, condition, body);
	}

	@Override
	public IASTexpression newNamedBreak(String name) {
		return new ASTnamedBreak(name);
	}

	@Override
	public IASTexpression newNamedContinue(String name) {
		return new ASTnamedContinue(name);
	}
	
}