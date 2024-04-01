package ru.nsu.fit.lylova.path.step.requirement;

import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.data.node.ValueNode;
import ru.nsu.fit.lylova.path.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Requirement {
    private String leftPart;
    private String rightPart;
    private RequirementPartType leftPartType;
    private RequirementPartType rightPartType;
    private PredicateType type;
    private String predicateName;

    public Requirement(PredicateType type, RequirementPartType leftPartType, String leftPart, RequirementPartType rightPartType, String rightPart) {
        this.leftPartType = leftPartType;
        this.leftPart = leftPart;
        this.rightPartType = rightPartType;
        this.rightPart = rightPart;
        this.type = type;
        this.predicateName = null;
    }

    public Requirement(String predicateName, RequirementPartType leftPartType, String leftPart, RequirementPartType rightPartType, String rightPart) {
        this.leftPartType = leftPartType;
        this.leftPart = leftPart;
        this.rightPartType = rightPartType;
        this.rightPart = rightPart;
        this.type = null;
        this.predicateName = predicateName;
    }


    public PredicateType getPredicateType() {
        return type;
    }

    public Requirement setPredicateType(PredicateType type) {
        this.type = type;
        return this;
    }


    public String getPredicateName() {
        return predicateName;
    }

    public void setPredicateName(String predicateName) {
        this.predicateName = predicateName;
    }

    public RequirementPartType getRightPartType() {
        return rightPartType;
    }

    public void setRightPartType(RequirementPartType rightPartType) {
        this.rightPartType = rightPartType;
    }

    public RequirementPartType getLeftPartType() {
        return leftPartType;
    }

    public void setLeftPartType(RequirementPartType leftPartType) {
        this.leftPartType = leftPartType;
    }

    public String getRightPart() {
        return rightPart;
    }

    public void setRightPart(String rightPart) {
        this.rightPart = rightPart;
    }

    public String getLeftPart() {
        return leftPart;
    }

    public void setLeftPart(String leftPart) {
        this.leftPart = leftPart;
    }

    public boolean validateNode(Node node, Context context) {
        Map<String, String> attributes = getAttributes(node);

        BiFunction<String, String, Boolean> predicate;
        if (type == PredicateType.EQUALITY) {
            predicate = String::equals;
        } else if (type == PredicateType.INEQUALITY) {
            predicate = (s1, s2) -> !s1.equals(s2);
        } else {
            predicate = context.getPredicateByName(predicateName);
        }

        if (leftPartType == RequirementPartType.VALUE) {
            if (rightPartType == RequirementPartType.VALUE) {
                return predicate.apply(leftPart, rightPart);
            }
            if (rightPartType == RequirementPartType.ATTR_NAME) {
                if (!rightPart.equals("*")) {
                    return predicate.apply(leftPart, attributes.get(rightPart));
                }
                boolean result = false;
                for (String i: attributes.values()) {
                    result |= predicate.apply(leftPart, i);
                }
                return result;
            }
        }
        if (leftPartType == RequirementPartType.ATTR_NAME) {
            if (rightPartType == RequirementPartType.VALUE) {
                if (!leftPart.equals("*")) {
                    return predicate.apply(attributes.get(leftPart), rightPart);
                }
                boolean result = false;
                for (String i: attributes.values()) {
                    result |= predicate.apply(i, rightPart);
                }
                return result;
            }
            if (rightPartType == RequirementPartType.ATTR_NAME) {
                if (!leftPart.equals("*")) {
                    if (!rightPart.equals("*")) {
                        return predicate.apply(attributes.get(leftPart), attributes.get(rightPart));
                    } else {
                        boolean result = false;
                        for (String i: attributes.values()) {
                            result |= predicate.apply(attributes.get(leftPart), i);
                        }
                        return result;
                    }
                } else {
                    if (!rightPart.equals("*")) {
                        boolean result = false;
                        for (String i: attributes.values()) {
                            result |= predicate.apply(i, attributes.get(rightPart));
                        }
                        return result;
                    } else {
                        boolean result = false;
                        for (String i: attributes.values()) {
                            for (String j : attributes.values()) {
                                result |= predicate.apply(i, j);
                            }
                        }
                        return result;
                    }
                }
            }
        }
        return false;
    }

    private static Map<String, String> getAttributes(Node node) {
        Map<String, String> attributes = new HashMap<>();
        if (node.isValue()) {
            ValueNode valueNode = (ValueNode) node;
            switch (valueNode.getValue().getValueType()) {
                case STRING -> attributes.put("type", "string");
                case INT -> attributes.put("type", "int");
                case DOUBLE -> attributes.put("type", "double");
            }
        }
        if (node.isElement()) {
            assert node instanceof ElementNode;
            ElementNode elementNode = (ElementNode) node;
            for (Attribute attribute : elementNode.getAttributes()) {
                attributes.put(attribute.getName(), attribute.getValue());
            }
        }
        return attributes;
    }
}