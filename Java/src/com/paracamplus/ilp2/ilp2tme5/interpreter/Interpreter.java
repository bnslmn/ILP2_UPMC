/* *****************************************************************
 * ILP9 - Implantation d'un langage de programmation.
 * by Christian.Queinnec@paracamplus.com
 * See http://mooc.paracamplus.com/ilp9
 * GPL version 3
 ***************************************************************** */
package com.paracamplus.ilp2.ilp2tme5.interpreter;

import com.paracamplus.ilp2.ilp2tme5.exceptions.BreakException;
import com.paracamplus.ilp2.ilp2tme5.exceptions.ContinueException;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTbreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTcontinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedBreak;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedContinue;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTnamedLoop;
import com.paracamplus.ilp2.ilp2tme5.interfaces.IASTvisitor;
import com.paracamplus.ilp2.interfaces.IASTloop;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;


public class Interpreter extends com.paracamplus.ilp2.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    
    // 
    
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment);
	}
    
    private int cpt = 0;
    private List<String> names = new ArrayList<String>();
    
    @Override
    public Object visit(IASTbreak iast, ILexicalEnvironment data) throws EvaluationException {
        if (cpt<1) {
            throw new EvaluationException("break sans boucle");
        } else {
            throw new BreakException("break");
        }
    }
    
    @Override
    public Object visit(IASTcontinue iast, ILexicalEnvironment data) throws EvaluationException {
        if (cpt<1) {
            throw new EvaluationException("continue sans boucle");
        } else {
            throw new ContinueException("continue");
        }
    }
    
    @Override
    public Object visit(IASTloop iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        cpt++;
        try {
            while ( true ) {
                try {
                    Object condition = iast.getCondition().accept(this, lexenv);
                    if ( condition instanceof Boolean ) {
                        Boolean c = (Boolean) condition;
                        if ( ! c ) {
                            break;
                        }
                    }
                    iast.getBody().accept(this, lexenv);
                } catch (ContinueException e) {
                	if (e.getName() != null)
                		throw new ContinueException(e.getMessage(), e.getName());
                }
            }
        } catch (BreakException e) { 
        	if (e.getName() != null)
        		throw new BreakException(e.getMessage(), e.getName());
        }
        cpt--;
        return Boolean.FALSE;
    }

	@Override
	public Object visit(IASTnamedLoop iast, ILexicalEnvironment lexenv) throws EvaluationException {
		cpt++;
		names.add(iast.getName());
        try {
            while ( true ) {
                try {
                    Object condition = iast.getCondition().accept(this, lexenv);
                    if ( condition instanceof Boolean ) {
                        Boolean c = (Boolean) condition;
                        if ( ! c ) {
                            break;
                        }
                    }
                    iast.getBody().accept(this, lexenv);
                } catch (ContinueException e) { 
                	if (e.getName() != null && !(e.getName().contentEquals(iast.getName()))) {
                		throw new ContinueException(e.getMessage(), e.getName());
                	}
                }
            }
        } catch (BreakException e) { 
        	if (e.getName() != null && !(e.getName().contentEquals(iast.getName()))) {
        		throw new BreakException(e.getMessage(), e.getName());
        	}
        }
        names.remove(iast.getName());
        cpt--;
        return Boolean.FALSE;
	}

	@Override
	public Object visit(IASTnamedBreak iast, ILexicalEnvironment data) throws EvaluationException {
		if (cpt<1) {
            throw new EvaluationException("break sans boucle");
        } else {
        	if (names.contains(iast.getName())) {
        		 throw new BreakException("break", iast.getName());
        	}
        	else {
        		throw new EvaluationException("cette boucle nommée non existante");
        	}
        }
	}

	@Override
	public Object visit(IASTnamedContinue iast, ILexicalEnvironment data) throws EvaluationException {
		if (cpt<1) {
            throw new EvaluationException("continue sans boucle");
        } else {
        	if (names.contains(iast.getName())) {
        		 throw new ContinueException("continue", iast.getName());
        	}
        	else {
        		throw new EvaluationException("cette boucle nommée non existante");
        	}
        }
	}

}