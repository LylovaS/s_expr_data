package ru.nsu.fit.lylova.schema;

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

    public void setValueType(ValueType type) {
        this.type = type;
    }
}

