package ru.nsu.fit.lylova.schema.node;

public class SchemaValueNode extends SchemaNode {
    ValueType type = null;

    @Override
    public boolean isValue() {
        return true;
    }

    @Override
    public boolean isElement() {
        return false;
    }

    public SchemaValueNode setValueType(ValueType type) {
        this.type = type;
        return this;
    }

    public ValueType getType() {
        return type;
    }
}

