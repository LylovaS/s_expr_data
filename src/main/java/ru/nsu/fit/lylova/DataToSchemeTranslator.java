package ru.nsu.fit.lylova;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.schema.*;

import java.util.HashMap;

public class DataToSchemeTranslator {
    private static final HashMap<String, SchemaElementNode> types = new HashMap<>();

    public static SchemaNode translate(Node data) {
        if (data.isValue()) {
            throw new IllegalArgumentException("Cannot translate value node into schema.");
        }
        ElementNode elementNode = (ElementNode) data;
        SchemaElementNode schema = new SchemaElementNode().setName("schema");
        for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
            Node child = elementNode.getChild(i);
            translateNode((ElementNode) child, schema);
        }
        return schema;
    }

    private static void translateNode(ElementNode elementNode, SchemaElementNode root) {
        if (elementNode == null) {
            throw new NullPointerException("Data cannot be null.");
        }
        switch (elementNode.getName()) {
            case "element": {
                SchemaElementNode schemaElementNode = new SchemaElementNode();

                Attribute attribute = elementNode.getAttributeByName("type");
                if (attribute != null) {
                    String typeName = attribute.getValue();
                    SchemaElementNode type = types.get(typeName);
                    if (type == null) {
                        throw new RuntimeException("Type " + attribute.getValue() + " hasn't been declared.");
                    }
                    schemaElementNode = type;
                    attribute = elementNode.getAttributeByName("name");
                    if (attribute != null && !attribute.getValue().equals(type.getName())) {
                        throw new RuntimeException("Name of the element is already defined in type " + typeName);
                    }
                } else {
                    attribute = elementNode.getAttributeByName("name");
                    if (attribute == null) {
                        throw new RuntimeException("An element must have \"name\" or \"type\" argument.");
                    }
                    schemaElementNode.setName(attribute.getValue());
                }


                attribute = elementNode.getAttributeByName("minOccurs");
                if (attribute != null) {
                    int minOccurs = Integer.parseInt(attribute.getValue().replaceAll("\"", ""));
                    attribute = elementNode.getAttributeByName("maxOccurs");
                    if (attribute != null) {
                        String value = attribute.getValue().replaceAll("\"", "");
                        if (value.equals("unbounded")) {
                            schemaElementNode.setMinOccursWithMaxOccursUnbounded(minOccurs);
                        } else {
                            schemaElementNode.setOccurs(
                                    minOccurs,
                                    Integer.parseInt(value));
                        }
                    } else {
                        throw new RuntimeException("Both minOccurs and maxOccurs must be specified."); //??
                    }
                }

                for (int i = 0; i < elementNode.getChildrenNumber(); i++) {
                    Node child = elementNode.getChild(i);
                    if (child.isValue()) {
                        throw new RuntimeException("Cannot translate value node into schema.");
                    }
                    translateNode((ElementNode) child, schemaElementNode);
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
                            String value = attribute.getValue().replaceAll("\"", "");
                            if (value.equals("unbounded")) {
                                schemaValueNode.setMinOccursWithMaxOccursUnbounded(minOccurs);
                            } else {
                                schemaValueNode.setOccurs(
                                        minOccurs,
                                        Integer.parseInt(value));
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
}
