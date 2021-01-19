package com.paracamplus.ilp2.ilp2tme5.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory 
implements com.paracamplus.ilp2.ilp2tme5.interfaces.IASTfactory {

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