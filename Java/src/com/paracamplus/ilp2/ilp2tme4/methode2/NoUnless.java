package com.paracamplus.ilp2.ilp2tme4.methode2;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASToperator;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class NoUnless implements IASTvisitor<IASTexpression, Void, CompilationException> {
	
	
	protected IASTfactory factory ; 
	
	public NoUnless(IASTfactory factory) {
		this.factory = factory ; 
	}
	
	
	
	 public IASTprogram visitProgram(IASTprogram iast, Void data) throws Throwable {
		 
	        IASTfunctionDefinition[] functions = iast.getFunctionDefinitions();
	        IASTfunctionDefinition[] funs = new IASTfunctionDefinition[functions.length];
	        
	        for ( int i=0 ; i<functions.length ; i++ ) {  
	        	funs[i] =  iast.getFunctionDefinitions()[i] ; 
	        }
		 return factory.newProgram(funs, iast.getBody().accept(this, data)) ; 
	 }
	

	public IASTfunctionDefinition visit(IASTfunctionDefinition iast , Void data) throws CompilationException {
		
		return factory.newFunctionDefinition(
									iast.getFunctionVariable(),
									iast.getVariables(),
									iast.getBody().accept(this, data));
	}
	
	
	@Override
	public IASTexpression visit(IASTassignment iast, Void data) throws CompilationException { 
	        return factory.newAssignment(
	        						visit(iast.getVariable(), data) , 
	        						iast.getExpression().accept(this, data));
	}

	@Override
	public IASTexpression visit(IASTloop iast, Void data) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IASTexpression visit(IASTalternative iast, Void data) throws CompilationException {
		
		   IASTexpression c = iast.getCondition().accept(this, data);
		   IASTexpression cs = iast.getConsequence().accept(this, data) ; 
		   IASTexpression alter = null ; 
		   
		   if(iast.isTernary()) {
			   alter = iast.getAlternant().accept(this, data) ; 
		   }else {
			   alter = factory.newBooleanConstant("false") ; 
		   }
		   
		return factory.newAlternative(c, cs, alter);
	}

	@Override
	public IASTexpression visit(IASTbinaryOperation iast, Void data) throws CompilationException {
		  
		  IASTexpression leftOperand = iast.getLeftOperand().accept(this, data);
		  IASToperator operator = iast.getOperator();
		  IASTexpression rightOperand = iast.getRightOperand().accept(this, data);

		  return factory.newBinaryOperation(operator, leftOperand, rightOperand) ; 
	}

	@Override
	public IASTexpression visit(IASTblock iast, Void data) throws CompilationException {
		IASTbinding[] bindings = iast.getBindings() ; 
		IASTbinding[] newBindings = new IASTbinding[bindings.length] ; 
		
		for (int i=0 ; i<bindings.length ; i++) {
				IASTbinding bind = bindings[i];
				IASTexpression expr = bind.getInitialisation();
	            IASTexpression newexpr = expr.accept(this, data);
	            IASTvariable variable = bind.getVariable();
	            newBindings[i] = factory.newBinding(variable, newexpr);
        }
				IASTexpression newBody = iast.getBody().accept(this, data) ; 
		return factory.newBlock(newBindings, newBody) ;  
		
	}
	//###### CONSTANTS
	@Override
	public IASTexpression visit(IASTboolean iast, Void data) {
		return iast;
	}

	@Override
	public IASTexpression visit(IASTfloat iast, Void data) {
		return iast;
	}

	@Override
	public IASTexpression visit(IASTinteger iast, Void data) {
		return iast ; 
	}
	
	@Override
	public IASTexpression visit(IASTstring iast, Void data) {
		return iast;
	}
	//###### END
	
	@Override
	public IASTexpression visit(IASTinvocation iast, Void data) throws CompilationException {
		   IASTexpression newFunction = iast.getFunction().accept(this, data); 
	       IASTexpression[] args = iast.getArguments() ; 
	       IASTexpression[] newArgs = new IASTexpression[args.length] ; 
	       
	       for (int i=0 ; i<args.length ; i++ ) {
	    	   IASTexpression arg = args[i] ;
             IASTexpression newA = arg.accept(this, data);
             newArgs[i] = newA ; 
         }
		return factory.newInvocation(newFunction, newArgs);
	}

	@Override
	public IASTexpression visit(IASTsequence iast, Void data) throws CompilationException {
		 IASTexpression[] expressions = iast.getExpressions();
	     IASTexpression[] exprs = new IASTexpression[expressions.length];
		     for ( int i=0 ; i< expressions.length ; i++ ) {
		            exprs[i] = expressions[i].accept(this, data);
		     }
	     if ( iast.getExpressions().length == 1 ) {
	            return exprs[0];
	     } else {
	            return factory.newSequence(exprs);
	     }
	}


	@Override
	public IASTexpression visit(IASTunaryOperation iast, Void data) throws CompilationException  {
	    
			IASTexpression operand = iast.getOperand().accept(this, data);
	        IASToperator operator = iast.getOperator();

		return factory.newUnaryOperation(operator, operand);
	}
	@Override
	public IASTvariable visit(IASTvariable iast, Void data) {
		return iast;
	}

	@Override
	public IASTexpression visit(IASTunless iast, Void data)  throws Throwable{

		   IASTexpression condi = iast.getCondition().accept(this, data);
		   IASTexpression consequence = iast.getBody().accept(this, data) ; 
		   IASTexpression alter = factory.newBooleanConstant("false") ; 

		   return factory.newAlternative(
				   				factory.newUnaryOperation(factory.newOperator("!"), condi), 
				   				consequence,
				   				alter) ; 
	
	}


	
}
