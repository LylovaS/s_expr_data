package ru.nsu.fit.lylova;

import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.DataWriter;
import ru.nsu.fit.lylova.data.node.*;
import ru.nsu.fit.lylova.schema.node.SchemaAttribute;
import ru.nsu.fit.lylova.schema.node.SchemaElementNode;
import ru.nsu.fit.lylova.schema.node.SchemaNode;
import ru.nsu.fit.lylova.schema.node.SchemaValueNode;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

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

        showTheData(dataNode, "");
        StringWriter writer = new StringWriter();
        DataWriter.writeToWriter(writer, dataNode);
        System.out.print(writer);

//        DataToSchemeTranslator translator = new DataToSchemeTranslator();
//        SchemaElementNode node = (SchemaElementNode) translator.translate(expressionWalker.getDataNode());
//        showTheData(node, "");
    }
}