package ru.nsu.fit.lylova.data.node;

public class Attribute {
    private String value = null;
    private String name = null;

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
