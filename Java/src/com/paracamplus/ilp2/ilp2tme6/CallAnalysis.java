package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interfaces.*;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp2.interfaces.IASTvisitor;

import java.util.*;

public class CallAnalysis implements IASTvisitor<Void, Set<String>, CompilationException> {

    protected Set<String> recursive;
    protected IASTprogram program;
    protected Map<String, Set<String>> calls;

    public CallAnalysis(IASTprogram program) throws CompilationException {
        this.program = program;
        recursive = new HashSet<>();
        calls = new HashMap<>();

        for(IASTfunctionDefinition func: program.getFunctionDefinitions()){
            Set<String> set = new HashSet<>();
            visit(func,set);
            calls.put(func.getFunctionVariable().getName(), set);
        }

        
        // Elaboration du graphe d'appel 
        
        
        for(String v: calls.keySet()){
        	
        	
            //les noeuds visités
            Set<String> visited = new HashSet<>();
            
            //Structure de donnée Last-in-First-Out (LIFO)
            Stack<String> stack = new Stack<>();

            stack.push(v);

            //Traitement des noeuds pas encore visités 
            while(!stack.empty()){
                String current = stack.pop();
                if(visited.contains(current)){
                    continue;
                }
                visited.add(current);

                //Suivant
                Set<String> suiv = calls.get(current);
                if(suiv != null){
                    for(String suivant: suiv){
                        stack.push(suivant);
                        if(v.equals(suivant)) {
                            recursive.add(v);       // Variable récursive !
                        }
                    }
                }
            }
        }
    }

    public boolean isRecursive(IASTvariable f){
        return recursive.contains(f.getName());
    }

    private Void visit(IASTfunctionDefinition iast, Set<String> data) throws CompilationException {
        iast.getBody().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTassignment iast, Set<String> data) throws CompilationException {
        iast.getExpression().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTloop iast, Set<String> data) throws CompilationException {
        iast.getBody().accept(this, data);
        iast.getCondition().accept(this,data);
        return null;
    }

    @Override
    public Void visit(IASTalternative iast, Set<String> data) throws CompilationException {
        iast.getCondition().accept(this, data);
        iast.getConsequence().accept(this, data);
        iast.getAlternant().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTbinaryOperation iast, Set<String> data) throws CompilationException {
        iast.getLeftOperand().accept(this, data);
        iast.getRightOperand().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTblock iast, Set<String> data) throws CompilationException {
        for ( IASTblock.IASTbinding binding : iast.getBindings() ) {
            binding.getInitialisation().accept(this, data);
        }
        iast.getBody().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTboolean iast, Set<String> data) throws CompilationException {
        return null;
    }

    @Override
    public Void visit(IASTfloat iast, Set<String> data) throws CompilationException {
        return null;
    }

    @Override
    public Void visit(IASTinteger iast, Set<String> data) throws CompilationException {
        return null;
    }

    @Override
    public Void visit(IASTinvocation iast, Set<String> data) throws CompilationException {
        for(IASTexpression e: iast.getArguments()){
            e.accept(this, data);
        }

        IASTexpression function = iast.getFunction();
        data.add(((IASTvariable)function).getName());
        return null;
    }

    @Override
    public Void visit(IASTsequence iast, Set<String> data) throws CompilationException {
        IASTexpression[] expressions = iast.getExpressions();
        for ( IASTexpression e : expressions ) {
            e.accept(this, data);
        }
        return null;
    }

    @Override
    public Void visit(IASTstring iast, Set<String> data) throws CompilationException {
        return null;
    }

    @Override
    public Void visit(IASTunaryOperation iast, Set<String> data) throws CompilationException {
        iast.getOperand().accept(this, data);
        return null;
    }

    @Override
    public Void visit(IASTvariable iast, Set<String> data) throws CompilationException {
        return null;
    }
}