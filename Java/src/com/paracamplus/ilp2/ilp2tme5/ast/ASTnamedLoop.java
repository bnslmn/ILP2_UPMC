package com.paracamplus.ilp2.ilp2tme5.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedLoop;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;

public class ASTnamedLoop extends ASTexpression 
implements IASTnamedLoop {

    public ASTnamedLoop (String name, IASTexpression condition, IASTexpression body) {
        this.name = name;
        this.condition = condition;
        this.body = body;
    }
    private final String name;
    private final IASTexpression condition;
    private final IASTexpression body;
    
    @Override
	public String getName() {
		return name;
	}

	@Override
	public IASTexpression getCondition() {
		return condition;
	}

	@Override
	public IASTexpression getBody() {
		return body;
	}
    
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}