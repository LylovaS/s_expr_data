package ru.nsu.fit.lylova;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.schema.*;

import java.util.HashMap;

public class DataToSchemeTranslator {
    private SchemaElementNode schema;
    private final HashMap<String, SchemaElementNode> types = new HashMap<>();

    public void translateNode(Node node, SchemaElementNode root) {
        ElementNode elementNode = (ElementNode) node;
        switch (elementNode.getName()) {
            case "schema": {
                schema = new SchemaElementNode().setName("schema");
                for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
                    Node child = elementNode.getChild(i);
                    translateNode((ElementNode) child, schema);
                }
                break;
            }
            case "element": {
                SchemaElementNode schemaElementNode = new SchemaElementNode();
                Attribute attribute = elementNode.getAttributeByName("name");
                if (attribute == null) {
                    throw new RuntimeException("An element must have a \"name\" argument.");
                }
                schemaElementNode.setName(attribute.getValue());

                attribute = elementNode.getAttributeByName("type");
                if (attribute != null) {
                    SchemaElementNode type = types.get(attribute.getValue());
                    if (type == null) {
                        throw new RuntimeException("Type " + attribute.getValue() + " hasn't been declared.");
                    }
                    schemaElementNode = type;
                }

                attribute = elementNode.getAttributeByName("minOccurs");
                if (attribute != null) {
                    int minOccurs = Integer.parseInt(attribute.getValue().replaceAll("\"", ""));
                    attribute = elementNode.getAttributeByName("maxOccurs");
                    if (attribute != null) {
                        if (attribute.getValue().equals("\"unbounded\"")) {
                            schemaElementNode.setMinOccursWithMaxOccursUnbounded(minOccurs);
                        } else {
                            schemaElementNode.setOccurs(
                                    minOccurs,
                                    Integer.parseInt(attribute.getValue().replaceAll("\"", "")));
                        }
                    } else {
                        throw new RuntimeException("Both minOccurs and maxOccurs must be specified."); //??
                    }
                }

                for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
                    Node child = elementNode.getChild(i);
                    translateNode(child, schemaElementNode);
                }
                root.addChildNode(schemaElementNode);
                break;
            }
            case "attribute": {
                Attribute attribute = elementNode.getAttributeByName("name");
                if (attribute != null) {
                    SchemaAttribute schemaAttribute = new SchemaAttribute(attribute.getValue());
                    attribute = elementNode.getAttributeByName("use");
                    if (attribute != null) {
                        switch (attribute.getValue().replaceAll("\"", "")) {
                            case "required": {
                                schemaAttribute.setUse(AttributeUse.REQUIRED);
                                break;
                            }
                            case "optional": {
                                schemaAttribute.setUse(AttributeUse.OPTIONAL);
                                break;
                            }
                            case "prohibited": {
                                schemaAttribute.setUse(AttributeUse.PROHIBITED);
                                break;
                            }
                        }
                    }
                    root.addAttribute(schemaAttribute);
                } else {
                    throw new RuntimeException("Attribute must have a name.");
                }
                if (elementNode.getChildrenNumber() > 0) {
                    throw new RuntimeException("Attribute cannot have any nested elements.");
                }
                break;
            }
            case "value": {
                Attribute attribute = elementNode.getAttributeByName("type");
                if (attribute != null) {
                    SchemaValueNode schemaValueNode = new SchemaValueNode();
                    switch (attribute.getValue().replaceAll("\"", "")) {
                        case "string": {
                            schemaValueNode.setValueType(ValueType.STRING);
                            break;
                        }
                        case "int": {
                            schemaValueNode.setValueType(ValueType.INT);
                            break;
                        }
                        case "double": {
                            schemaValueNode.setValueType(ValueType.DOUBLE);
                            break;
                        }
                    }
                    attribute = elementNode.getAttributeByName("minOccurs");
                    if (attribute != null) {
                        int minOccurs = Integer.parseInt(attribute.getValue().replaceAll("\"", ""));
                        attribute = elementNode.getAttributeByName("maxOccurs");
                        if (attribute != null) {
                            if (attribute.getValue().equals("\"unbounded\"")) {
                                schemaValueNode.setMinOccursWithMaxOccursUnbounded(minOccurs);
                            } else {
                                schemaValueNode.setOccurs(
                                        minOccurs,
                                        Integer.parseInt(attribute.getValue().replaceAll("\"", "")));
                            }
                        } else {
                            throw new RuntimeException("Both minOccurs and maxOccurs must be specified.");
                        }
                    }
                    root.addChildNode(schemaValueNode);
                } else {
                    throw new RuntimeException("Value must have a type.");
                }
                if (elementNode.getChildrenNumber() > 0) {
                    throw new RuntimeException("Value cannot have any nested elements.");
                }
                break;
            }
            case "type": {
                Attribute attribute = elementNode.getAttributeByName("type_name");
                if (attribute == null) {
                    throw new RuntimeException("Type must have a \"type_name\" attribute.");
                }
                String typeName = attribute.getValue();
                attribute = elementNode.getAttributeByName("element_name");
                if (attribute == null) {
                    throw new RuntimeException("Type must have an \"element_name\" attribute.");
                }
                SchemaElementNode type = new SchemaElementNode().setName(attribute.getValue());
                types.put(typeName, type);

                for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
                    Node child = elementNode.getChild(i);
                    translateNode((ElementNode) child, type);
                }
                break;
            }
        }
    }

    public SchemaElementNode getSchema() {
        return schema;
    }
}
