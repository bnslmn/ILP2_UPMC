package com.paracamplus.ilp2.ilp2tme4.methode2;

public interface IASTvisitable {
    <Result, Data, Anomaly extends Throwable> 
    Result accept(com.paracamplus.ilp2.ilp2tme4.methode2.IASTvisitor<Result, Data, Anomaly> visitor,
                  Data data) throws Anomaly;
}