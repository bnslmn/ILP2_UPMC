package com.paracamplus.ilp2.ilp2tme6;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.tools.Input;
import com.paracamplus.ilp1.tools.InputFromFile;
import com.paracamplus.ilp2.interfaces.IASTprogram;
import com.paracamplus.ilp1.parser.ParseException;

import com.paracamplus.ilp2.ast.ASTfactory;
import com.paracamplus.ilp2.interfaces.IASTfactory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CallAnalysisTest {

    protected CallAnalysis callAnalysis;
    protected String path = "SamplesTME6/CallAnalysis";
    protected IASTfactory factory;
    protected ILPMLOptimizingParser ilpmlParser;

    @Before
    public void init() {
        factory = new ASTfactory();
        ilpmlParser = new ILPMLOptimizingParser(factory);
    }

    @Test
    public void testRecursif() throws ParseException, CompilationException {
        File file = new File(path+"/test.ilpml");
        Input input = new InputFromFile(file);
        System.err.println("Testing " + file.getAbsolutePath() + " ...");
        ilpmlParser.setInput(input);
        IASTprogram program = ilpmlParser.getProgram();

        callAnalysis = new CallAnalysis(program);

        assertTrue(callAnalysis.isRecursive(factory.newVariable("fib")));
    }

    @Test
    public void testMutuelleRecursif() throws ParseException, CompilationException {
    	
        File file = new File(path+"/test2.ilpml");
        Input input = new InputFromFile(file);
        System.err.println("Testing " + file.getAbsolutePath() + " ...");
        ilpmlParser.setInput(input);
        IASTprogram program = ilpmlParser.getProgram();

        callAnalysis = new CallAnalysis(program);

        assertTrue(callAnalysis.isRecursive(factory.newVariable("impair")));
        assertTrue(callAnalysis.isRecursive(factory.newVariable("pair")));
        assertFalse(callAnalysis.isRecursive(factory.newVariable("moins")));
    }







}