package ru.nsu.fit.lylova;

import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.DataWriter;
import ru.nsu.fit.lylova.data.node.*;
import ru.nsu.fit.lylova.path.Context;
import ru.nsu.fit.lylova.path.Path;
import ru.nsu.fit.lylova.schema.SchemaReader;
import ru.nsu.fit.lylova.schema.SchemaValidator;
import ru.nsu.fit.lylova.schema.node.SchemaAttribute;
import ru.nsu.fit.lylova.schema.node.SchemaElementNode;
import ru.nsu.fit.lylova.schema.node.SchemaNode;
import ru.nsu.fit.lylova.schema.node.SchemaValueNode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;

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
            System.out.print(" count=[" + node.getMinOccurs() + "," + node.getMaxOccurs() + "]");
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
            System.out.print(" count=[" + node.getMinOccurs() + "," + node.getMaxOccurs() + "]");
        }
    }

    public static void main(String[] args) throws Exception {

        Node dataNode = DataReader.parseDataWithSchema(new FileReader("data.txt"), new FileReader("schema.txt"));
        showTheData(dataNode, "");
        System.out.println();

        SchemaNode schemaNode = SchemaReader.parseSchema(new FileReader("schema.txt"));
        if (SchemaValidator.validate(dataNode, schemaNode)) {
            System.out.println("Data is ok");
        } else {
            System.out.println("Data ne ok");
        }

        Path path = Path.compile("//school[contains(@name, \"Gymnasium\")]//@value");
        Context context = new Context(dataNode).addPredicate("contains", String::contains);
        Collection<Node> res = path.evaluate(context);
        int n = 0;
        for (Node i : res) {
            ++n;
            System.out.println(n + ": ");
            showTheData(i, "   ");
        }

        dataNode = ((ElementNode) dataNode).setName("location");
        try (Writer writer = new FileWriter("changed_data.txt")) {
            DataWriter.writeToWriter(writer, dataNode);
        }
    }
}