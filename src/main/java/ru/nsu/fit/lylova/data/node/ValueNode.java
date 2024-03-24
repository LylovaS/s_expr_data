package ru.nsu.fit.lylova.data.node;

public class ValueNode extends Node {
    Value value = null;

    public Value getValue() {
        return value;
    }

    public ValueNode setValue(Value value) {
        this.value = value;
        return this;
    }


    public boolean isValue() {
        return true;
    }


    public boolean isElement() {
        return false;
    }
}
