package ru.nsu.fit.lylova;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.node.*;
import ru.nsu.fit.lylova.schema.SchemaAttribute;
import ru.nsu.fit.lylova.schema.SchemaElementNode;
import ru.nsu.fit.lylova.schema.SchemaNode;
import ru.nsu.fit.lylova.schema.SchemaValueNode;

import java.io.FileReader;
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
            for (SchemaAttribute attr : schemaElementNode.getAttributes()) {
                System.out.print(" " + "attr" + "=" + attr.getName());
            }
            System.out.print('\n');
            prefix = prefix + "\t";
            for (int i = 0; i < schemaElementNode.getChildrenNumber(); ++i) {
                showTheData(schemaElementNode.getChild(i), prefix);
            }
        } else {
            SchemaValueNode schemaValueNode = (SchemaValueNode) node;
            System.out.print("value " + schemaValueNode.getType().toString());
        }
    }

    public static void main(String[] args) throws IOException {

        Node dataNode = DataReader.parseDataFromReader(new FileReader("data.txt"));

        System.out.println(dataNode.isElement());
        showTheData(dataNode, "");

//        DataToSchemeTranslator translator = new DataToSchemeTranslator();
//        SchemaElementNode node = (SchemaElementNode) translator.translate(expressionWalker.getDataNode());
//        showTheData(node, "");
    }
}