package ru.nsu.fit.lylova.data.node;

public class ValueNode implements Node {
    Value value = null;

    public Value getValue() {
        return value;
    }

    public ValueNode setValue(Value value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean isValue() {
        return true;
    }

    @Override
    public boolean isElement() {
        return false;
    }
}
