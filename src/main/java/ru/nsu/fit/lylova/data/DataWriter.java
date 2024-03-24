package ru.nsu.fit.lylova.data;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.data.node.ValueNode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class DataWriter {
    public static void writeToFile(String filename, Node data) throws IOException {
        if (filename == null) {
            throw new NullPointerException("Filename cannot be null.");
        }
        if (data == null) {
            throw new NullPointerException("Data cannot be null.");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        write(data, writer, "");
        writer.close();
    }

    private static void write(Node node, BufferedWriter writer, String prefix) throws IOException {
        writer.write(prefix);
        if (node.isValue()) {
            ValueNode valueNode = (ValueNode) node;
            switch (valueNode.getValue().getValueType()) {
                case INT: {
                    writer.write(valueNode.getValue().getValueAsInteger() + " ");
                    break;
                }
                case DOUBLE: {
                    writer.write(valueNode.getValue().getValueAsDouble() + " ");
                    break;
                }
                case STRING: {
                    writer.write(valueNode.getValue().getValueAsString() + " ");
                    break;
                }
            }
            //writer.newLine();
        } else if (node.isElement()) {
            ElementNode elementNode = (ElementNode) node;
            writer.write("(" + elementNode.getName());

            if (!elementNode.getAttributes().isEmpty()) {
                writer.write(" {");
                Iterator<Attribute> iterator = elementNode.getAttributes().iterator();
                Attribute attribute = iterator.next();
                while (iterator.hasNext()) {
                    writer.write(attribute.getName() + " " + attribute.getValue() + ", ");
                    attribute = iterator.next();
                }
                writer.write(attribute.getName() + " " + attribute.getValue() + "}");
            }

            if (elementNode.getChildrenNumber() > 0) {
                for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
                    writer.newLine();
                    Node child = elementNode.getChild(i);
                    write(child, writer, prefix + "\t");
                }
                writer.newLine();
                writer.write(prefix + ")");
            } else {
                writer.write(")");
            }
        }
    }
}
