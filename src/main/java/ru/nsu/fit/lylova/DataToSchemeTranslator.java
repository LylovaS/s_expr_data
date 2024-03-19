package ru.nsu.fit.lylova;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.data.node.ValueNode;
import ru.nsu.fit.lylova.schema.SchemaAttribute;
import ru.nsu.fit.lylova.schema.SchemaElementNode;
import ru.nsu.fit.lylova.schema.SchemaNode;
import ru.nsu.fit.lylova.schema.SchemaValueNode;

public class DataToSchemeTranslator {
    public SchemaNode translate(Node data) {
        if (data.isValue()) {
            return translateNode((ValueNode) data);
        } else if (data.isElement()) {
            return translateNode((ElementNode) data);
        }
        return null;
    }

    private SchemaValueNode translateNode(ValueNode node) {
        return new SchemaValueNode().setValueType(node.getValue().getValueType());
    }

    private SchemaElementNode translateNode(ElementNode node) {
        SchemaElementNode schemaElementNode = new SchemaElementNode().setName(node.getName());
        for (Attribute attribute : node.getAttributes()) {
            schemaElementNode.addAttribute(translateAttribute(attribute));
        }
        for (int i = 0; i < node.getChildrenNumber(); i++) {
            Node child = node.getChild(i);
            if (child.isValue()) {
                schemaElementNode.addChildNode(translateNode((ValueNode) child));
            } else if (child.isElement()) {
                schemaElementNode.addChildNode(translateNode((ElementNode) child));
            }
        }
        return schemaElementNode;
    }

    private SchemaAttribute translateAttribute(Attribute attribute) {
        return new SchemaAttribute(attribute.getName());
    }
}
