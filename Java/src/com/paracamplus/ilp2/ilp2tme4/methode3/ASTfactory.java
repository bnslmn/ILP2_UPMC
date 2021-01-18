package com.paracamplus.ilp2.ilp2tme4.methode3;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public class ASTfactory extends com.paracamplus.ilp2.ast.ASTfactory implements IASTfactory {
	@Override
	public IASTunless newUnless(IASTexpression condition, IASTexpression body) {
      return new ASTunless(condition, body);
	}
}