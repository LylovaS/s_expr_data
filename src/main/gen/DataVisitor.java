// Generated from C:/1/S_expr_data/src/main/antlr/Data.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DataParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DataVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DataParser#calc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc(DataParser.CalcContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(DataParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#expr_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_name(DataParser.Expr_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#attrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrs(DataParser.AttrsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#attr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(DataParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#attr_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_name(DataParser.Attr_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link DataParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(DataParser.ValueContext ctx);
}