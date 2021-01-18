package com.paracamplus.ilp2.ilp2tme4.methode3;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.compiler.normalizer.INormalizationFactory;

public class Normalizer extends com.paracamplus.ilp2.compiler.normalizer.Normalizer implements 
IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {

   public Normalizer (INormalizationFactory factory) {
   	super(factory);
   }
   
   
   	// ******************** Normalisation de l'ASTunless
   
	public IASTexpression visit(IASTunless iast, INormalizationEnvironment env)
           throws CompilationException {
       IASTexpression newcondition = iast.getCondition().accept(this, env);
       IASTexpression newbody = iast.getBody().accept(this, env);
       return new ASTunless(newcondition, newbody);
   }
   
}
