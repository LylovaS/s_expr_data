package ru.nsu.fit.lylova;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.nsu.fit.lylova.data.DataWalker;
import ru.nsu.fit.lylova.data.node.*;

import java.io.IOException;

public class Main {
    static private void showTheData(Node node, String prefix) {
        System.out.print(prefix);
        if (node.isElement()) {
            ElementNode elementNode = (ElementNode) node;
            System.out.print("element " + elementNode.getName());
            for (Attribute attr : elementNode.getAttributes()) {
                System.out.print(" " + attr.getName() + "=" + attr.getValue());
            }
            System.out.print('\n');
            prefix = prefix + "\t";
            for (int i = 0; i < elementNode.getChildNumber(); ++i) {
                showTheData(elementNode.getChild(i), prefix);
            }
        } else {
            ValueNode valueNode = (ValueNode) node;
            System.out.println("value " + Value.toString(valueNode.getValue()));
        }
    }

    public static void main(String[] args) throws IOException {

        DataLexer lexer = new DataLexer(CharStreams.fromFileName("data.txt"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DataParser parser = new DataParser(tokens);
        ParseTree tree = parser.calc();
        ParseTreeWalker walker = new ParseTreeWalker();
        DataWalker expressionWalker = new DataWalker();
        walker.walk(expressionWalker, tree);
        System.out.println(expressionWalker.getDataNode().isElement());
        showTheData(expressionWalker.getDataNode(), "");
    }
}