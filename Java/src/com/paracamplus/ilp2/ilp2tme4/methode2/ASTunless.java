package com.paracamplus.ilp2.ilp2tme4.methode2;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvisitor;

public class ASTunless extends ASTexpression implements IASTunless, IASTvisitable{
	
	private IASTexpression body;
	private IASTexpression condition;
	
	public ASTunless(IASTexpression body , IASTexpression condition) {
		this.body = body;
		this.condition=condition;
	}
	

	@Override
	public IASTexpression getBody() {
		return this.body;
	}

	@Override
	public IASTexpression getCondition() {
		return this.condition;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(com.paracamplus.ilp2.ilp2tme4.methode2.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) {
		try {
			return ( (com.paracamplus.ilp2.ilp2tme4.methode2.IASTvisitor<Result, Data, Throwable> ) visitor).visit(this, data);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		try {
			return ( (com.paracamplus.ilp2.ilp2tme4.methode2.IASTvisitor<Result, Data, Throwable> ) visitor).visit(this, data);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}




	

	

}