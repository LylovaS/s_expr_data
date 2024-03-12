package ru.nsu.fit.lylova.data;

import ru.nsu.fit.lylova.DataBaseListener;
import ru.nsu.fit.lylova.DataParser;
import ru.nsu.fit.lylova.data.node.*;

import java.util.Stack;

public class DataWalker extends DataBaseListener {
    private Node parentNode;
    private Stack<Node> nodes = new Stack<>();
    private Attribute attribute;

    private void addNodeInTree(Node node) {
        if (nodes.empty()) {
            nodes.add(node);
            parentNode = node;
        } else {
            ElementNode last = (ElementNode) nodes.lastElement();
            last.addChildNode(node);
            nodes.add(node);
        }
    }

    @Override
    public void enterCalc(DataParser.CalcContext ctx) {
    }

    @Override
    public void exitCalc(DataParser.CalcContext ctx) {
    }

    @Override
    public void enterExpr(DataParser.ExprContext ctx) {

    }

    @Override
    public void exitExpr(DataParser.ExprContext ctx) {
        nodes.pop();
    }

    @Override
    public void enterExpr_name(DataParser.Expr_nameContext ctx) {

    }

    @Override
    public void exitExpr_name(DataParser.Expr_nameContext ctx) {
        this.addNodeInTree(new ElementNode().setName(ctx.getText()));
    }

    @Override
    public void enterAttrs(DataParser.AttrsContext ctx) {
    }

    @Override
    public void exitAttrs(DataParser.AttrsContext ctx) {
    }

    @Override
    public void enterAttr(DataParser.AttrContext ctx) {
        this.attribute = new Attribute();
    }

    @Override
    public void exitAttr(DataParser.AttrContext ctx) {
        this.attribute.setValue(ctx.STRING().getText());
        ((ElementNode) this.nodes.lastElement()).addAttribute(attribute);
        this.attribute = null;
    }

    @Override
    public void enterAttr_name(DataParser.Attr_nameContext ctx) {
    }

    @Override
    public void exitAttr_name(DataParser.Attr_nameContext ctx) {
        this.attribute.setName(ctx.NAME().getText());
    }

    @Override public void enterValue(DataParser.ValueContext ctx) { }

    @Override public void exitValue(DataParser.ValueContext ctx) {
        this.addNodeInTree(new ValueNode().setValue(new Value().setValue(ctx.getText())));
    }
    public Node getDataNode() {
        return parentNode;
    }
}
