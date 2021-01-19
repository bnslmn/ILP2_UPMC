package com.paracamplus.ilp2.ilp2tme5.ast;

import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedContinue;

public class ASTnamedContinue
implements IASTnamedContinue {
	
	public ASTnamedContinue (String name) {
        this.name = name;
    }
    private final String name;

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

}