package com.paracamplus.ilp2.ilp2tme4.methode2;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTunless extends IASTexpression{
	
	IASTexpression getBody() ; 
	IASTexpression getCondition() ;

}
