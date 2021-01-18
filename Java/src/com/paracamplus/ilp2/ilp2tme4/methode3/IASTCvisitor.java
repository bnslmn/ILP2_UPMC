package com.paracamplus.ilp2.ilp2tme4.methode3;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable> 
extends com.paracamplus.ilp2.ilp2tme4.methode3.IASTvisitor<Result, Data, Anomaly>
 {
    Result visit(IASTunless iast, Data data) throws Anomaly;
}