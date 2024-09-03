// Generated from QuasarGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Stack;
	
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
	

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuasarGrammarParser}.
 */
public interface QuasarGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(QuasarGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(QuasarGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(QuasarGrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(QuasarGrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#while_command}.
	 * @param ctx the parse tree
	 */
	void enterWhile_command(QuasarGrammarParser.While_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#while_command}.
	 * @param ctx the parse tree
	 */
	void exitWhile_command(QuasarGrammarParser.While_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#if_command}.
	 * @param ctx the parse tree
	 */
	void enterIf_command(QuasarGrammarParser.If_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#if_command}.
	 * @param ctx the parse tree
	 */
	void exitIf_command(QuasarGrammarParser.If_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#declaration_command}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_command(QuasarGrammarParser.Declaration_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#declaration_command}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_command(QuasarGrammarParser.Declaration_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#atribuition_command}.
	 * @param ctx the parse tree
	 */
	void enterAtribuition_command(QuasarGrammarParser.Atribuition_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#atribuition_command}.
	 * @param ctx the parse tree
	 */
	void exitAtribuition_command(QuasarGrammarParser.Atribuition_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#read_command}.
	 * @param ctx the parse tree
	 */
	void enterRead_command(QuasarGrammarParser.Read_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#read_command}.
	 * @param ctx the parse tree
	 */
	void exitRead_command(QuasarGrammarParser.Read_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#write_command}.
	 * @param ctx the parse tree
	 */
	void enterWrite_command(QuasarGrammarParser.Write_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#write_command}.
	 * @param ctx the parse tree
	 */
	void exitWrite_command(QuasarGrammarParser.Write_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QuasarGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QuasarGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#expression_line}.
	 * @param ctx the parse tree
	 */
	void enterExpression_line(QuasarGrammarParser.Expression_lineContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#expression_line}.
	 * @param ctx the parse tree
	 */
	void exitExpression_line(QuasarGrammarParser.Expression_lineContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuasarGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(QuasarGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuasarGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(QuasarGrammarParser.TermContext ctx);
}