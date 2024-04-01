package ru.nsu.fit.lylova.data.node;

public class Attribute {
    private String value = null;
    private String name = null;

    public Attribute setName(String name) {
        this.name = name;
        return this;
    }

    public Attribute setValue(String value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
