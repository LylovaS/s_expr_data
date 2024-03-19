// Generated from C:/1/S_expr_data/src/main/antlr/Data.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DataParser}.
 */
public interface DataListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link DataParser#calc}.
     *
     * @param ctx the parse tree
     */
    void enterCalc(DataParser.CalcContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#calc}.
     *
     * @param ctx the parse tree
     */
    void exitCalc(DataParser.CalcContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#expr}.
     *
     * @param ctx the parse tree
     */
    void enterExpr(DataParser.ExprContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#expr}.
     *
     * @param ctx the parse tree
     */
    void exitExpr(DataParser.ExprContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#expr_name}.
     *
     * @param ctx the parse tree
     */
    void enterExpr_name(DataParser.Expr_nameContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#expr_name}.
     *
     * @param ctx the parse tree
     */
    void exitExpr_name(DataParser.Expr_nameContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#attrs}.
     *
     * @param ctx the parse tree
     */
    void enterAttrs(DataParser.AttrsContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#attrs}.
     *
     * @param ctx the parse tree
     */
    void exitAttrs(DataParser.AttrsContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#attr}.
     *
     * @param ctx the parse tree
     */
    void enterAttr(DataParser.AttrContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#attr}.
     *
     * @param ctx the parse tree
     */
    void exitAttr(DataParser.AttrContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#attr_name}.
     *
     * @param ctx the parse tree
     */
    void enterAttr_name(DataParser.Attr_nameContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#attr_name}.
     *
     * @param ctx the parse tree
     */
    void exitAttr_name(DataParser.Attr_nameContext ctx);

    /**
     * Enter a parse tree produced by {@link DataParser#value}.
     *
     * @param ctx the parse tree
     */
    void enterValue(DataParser.ValueContext ctx);

    /**
     * Exit a parse tree produced by {@link DataParser#value}.
     *
     * @param ctx the parse tree
     */
    void exitValue(DataParser.ValueContext ctx);
}