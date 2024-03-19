package ru.nsu.fit.lylova.path;

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
}