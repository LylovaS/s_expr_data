package ru.nsu.fit.lylova.data.node;

public class Value {
    private String value;
    public String getValueAsString() {
        return value;
    }
    public Value setValue(String s) {
        value = s;
        return this;
    }
}
