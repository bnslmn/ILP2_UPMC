package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.compiler.normalizer.NoSuchLocalVariableException;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTprogram;

public class RenameTransform extends CopyTransform<INormalizationEnvironment> {
	public static int var = 0;
	public RenameTransform(IASTfactory factory) {
		super(factory);		
	}

	@Override
	public IASTexpression visit(IASTvariable iast, INormalizationEnvironment env) throws CompilationException {
		try {
			IASTexpression ret = env.renaming(iast);
			System.out.println("ret "+ret);
			return ret;
		} catch (NoSuchLocalVariableException e) {
			return iast;
		}
	}

	
	
	@Override
	public IASTexpression visit(IASTblock iast, INormalizationEnvironment env) throws CompilationException {
		IASTbinding[] binding= iast.getBindings();
		IASTbinding[] newBindings = new IASTbinding[binding.length];
		INormalizationEnvironment newEnv = env;
		
		for (int i = 0; i < binding.length; i++) {
			//stockage des variables
			IASTvariable oldvar = binding[i].getVariable();
			
			// Création des variables renommées
			IASTvariable newvar = factory.newVariable(oldvar.getName() + "_" + var++);
			
			// Extension de l'ancien environnement par les variables renommées
			newEnv = newEnv.extend(oldvar, newvar);
			
			// Créer un nouveau binding
			//   /!\ Penser à visiter l'initialisation avec l'ancien environment
			newBindings[i] = factory.newBinding(newvar, binding[i].getInitialisation().accept(this, env));
			
		}
		
		
		IASTexpression body =  iast.getBody().accept(this, newEnv);
		return factory.newBlock(newBindings, body);
	}



	@Override
	public IASTprogram visit(IASTprogram iast, INormalizationEnvironment env) throws CompilationException {
		IASTfunctionDefinition[] funcdef = iast.getFunctionDefinitions();
		IASTfunctionDefinition[] newf = new IASTfunctionDefinition[funcdef.length];	
		INormalizationEnvironment newEnv;
		
		for (int i = 0; i < funcdef.length; i++) {
			// Associer chaque fonction à un seul environnement
			newEnv= env;
			
			
			int len = funcdef[i].getVariables().length;
			IASTvariable[] newvars = new IASTvariable[len];
			// VARIABLE RENAMING
			for(int j=0;j<funcdef[i].getVariables().length;j++) {
				System.out.println("cc");
				IASTvariable oldvar=funcdef[i].getVariables()[j];
				IASTvariable newvar=factory.newVariable(oldvar.getName()+"_"+var++);
				
				newvars[j]=newvar;
				newEnv=newEnv.extend(oldvar, newvar);
			}
			
			IASTvariable functionVariable = funcdef[i].getFunctionVariable();
			IASTexpression body = funcdef[i].getBody().accept(this, newEnv);
			newf[i] = factory.newFunctionDefinition(functionVariable, newvars, body.accept(this, env));
		}
		IASTexpression expression = iast.getBody().accept(this, env);
		System.out.println("cpt :: "+var);
		return factory.newProgram(newf, expression);
	}
	


}