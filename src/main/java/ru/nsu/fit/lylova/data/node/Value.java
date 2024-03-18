package ru.nsu.fit.lylova.data.node;

import ru.nsu.fit.lylova.schema.ValueType;

public class Value {
    private String string_value = null;
    private Double double_value = null;
    private Long integer_value = null;
    private ValueType valueType = null;

    public Value() {
    }

    public Value(long value) {
        integer_value = value;
        valueType = ValueType.INT;
    }

    public Value(double value) {
        double_value = value;
        valueType = ValueType.DOUBLE;
    }

    public Value(String value) {
        string_value = value;
        valueType = ValueType.STRING;
    }


    public static java.lang.String toString(Value value) {
        if (value.string_value != null) {
            return value.string_value;
        }
        if (value.double_value != null) {
            return Double.toString(value.double_value);
        }
        if (value.integer_value != null) {
            return Long.toString(value.integer_value);
        }
        return "null";
    }

    private void resetValues() {
        string_value = null;
        double_value = null;
        integer_value = null;
    }

    public String getValueAsString() {
        return string_value;
    }

    public double getValueAsDouble() {
        return double_value;
    }

    public long getValueAsInteger() {
        return integer_value;
    }

    public Value setValueAsString(String value) {
        this.resetValues();
        string_value = value;
        valueType = ValueType.STRING;
        return this;
    }

    public Value setValueAsDouble(double value) {
        this.resetValues();
        double_value = value;
        valueType = ValueType.DOUBLE;
        return this;
    }

    public Value setValueAsInteger(long value) {
        this.resetValues();
        integer_value = value;
        valueType = ValueType.INT;
        return this;
    }

    public ValueType getValueType() {
        return valueType;
    }
}
