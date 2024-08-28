// Generated from TestGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import io.compiler.types.*;
	import io.compiler.core.exception.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestGrammarParser}.
 */
public interface TestGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TestGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TestGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#declarevar}.
	 * @param ctx the parse tree
	 */
	void enterDeclarevar(TestGrammarParser.DeclarevarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#declarevar}.
	 * @param ctx the parse tree
	 */
	void exitDeclarevar(TestGrammarParser.DeclarevarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(TestGrammarParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(TestGrammarParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#cmdAtrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtrib(TestGrammarParser.CmdAtribContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#cmdAtrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtrib(TestGrammarParser.CmdAtribContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(TestGrammarParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(TestGrammarParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(TestGrammarParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(TestGrammarParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TestGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TestGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TestGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TestGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(TestGrammarParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(TestGrammarParser.ExprlContext ctx);
}