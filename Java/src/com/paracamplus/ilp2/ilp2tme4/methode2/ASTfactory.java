package com.paracamplus.ilp2.ilp2tme4.methode2;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory{

	@Override
	public IASTexpression newUnless(IASTexpression body, IASTexpression condition) {
		return new ASTunless(body,condition);
	}

}
