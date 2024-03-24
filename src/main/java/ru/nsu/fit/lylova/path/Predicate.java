package ru.nsu.fit.lylova.path;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.data.node.ValueNode;

public class Predicate {
    private String attributeName;
    private String attributeValue;
    private PredicateType type;

    Predicate(String attributeName, String attributeValue, PredicateType type) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.type = type;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public PredicateType getPredicateType() {
        return type;
    }

    public Predicate setAttributeName(String attributeName) {
        this.attributeName = attributeName;
        return this;
    }

    public Predicate setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }

    public Predicate setPredicateType(PredicateType type) {
        this.type = type;
        return this;
    }

    public boolean validateNode(Node node) {
        boolean result = false;
        if (node.isValue()) {
            if (!attributeName.equals("type") && !attributeName.equals("*")) {
                return false;
            }
            ValueNode valueNode = (ValueNode) node;
            switch (valueNode.getValue().getValueType()) {
                case STRING -> {
                    result = attributeValue.equals("string");
                }
                case INT -> {
                    result = attributeValue.equals("integer");
                }
                case DOUBLE -> {
                    result = attributeValue.equals("float");
                }
            }
        }
        if (node.isElement()) {
            ElementNode elementNode = (ElementNode) node;
            if (attributeName.equals("*")) {
                for (Attribute attribute : elementNode.getAttributes()) {
                    result = attribute.getValue().equals(attributeValue);
                    if (type == PredicateType.INEQUALITY) {
                        result = !result;
                    }
                    if (result) {
                        return true;
                    }
                }
                return false;
            }
            for (Attribute attribute : elementNode.getAttributes()) {
                if (attribute.getName().equals(attributeName)) {
                    result = attribute.getValue().equals(attributeValue);
                    break;
                }
            }
        }
        if (type == PredicateType.INEQUALITY) {
            result = !result;
        }
        return result;
    }
}