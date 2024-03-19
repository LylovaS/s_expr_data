package ru.nsu.fit.lylova;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.nsu.fit.lylova.data.DataWalker;
import ru.nsu.fit.lylova.data.DataWriter;
import ru.nsu.fit.lylova.data.node.*;
import ru.nsu.fit.lylova.schema.*;

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
            for (int i = 0; i < elementNode.getChildrenNumber(); ++i) {
                showTheData(elementNode.getChild(i), prefix);
            }
        } else {
            ValueNode valueNode = (ValueNode) node;
            System.out.println("value " + Value.toString(valueNode.getValue()));
        }
    }

    static private void showTheData(SchemaNode node, String prefix) {
        System.out.print(prefix);
        if (node.isElement()) {
            SchemaElementNode schemaElementNode = (SchemaElementNode) node;
            System.out.print("element " + schemaElementNode.getName());
            System.out.print(" minOccurs=" + schemaElementNode.getMinOccurs());
            System.out.print(" maxOccurs=" + schemaElementNode.getMaxOccurs());
            for (SchemaAttribute attr : schemaElementNode.getAttributes()) {
                System.out.print(" [attr=" + attr.getName());
                System.out.print(" use=" + attr.getUse().name() + "] ");
            }
            System.out.print('\n');
            prefix = prefix + "\t";
            for (int i = 0; i < schemaElementNode.getChildrenNumber(); ++i) {
                showTheData(schemaElementNode.getChild(i), prefix);
            }
        } else {
            SchemaValueNode schemaValueNode = (SchemaValueNode) node;
            System.out.print("value " + schemaValueNode.getType().name());
            System.out.print(" minOccurs=" + schemaValueNode.getMinOccurs());
            System.out.print(" maxOccurs=" + schemaValueNode.getMaxOccurs());
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

        /*
        DataToSchemeTranslator translator = new DataToSchemeTranslator();
        SchemaElementNode schema = new SchemaElementNode();
        translator.translateNode((ElementNode) expressionWalker.getDataNode(), schema);
        showTheData(translator.getSchema(), "");
         */

        DataWriter.writeToFile("dataWrite.txt", expressionWalker.getDataNode());
    }
}