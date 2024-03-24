package ru.nsu.fit.lylova;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Value;
import ru.nsu.fit.lylova.data.node.ValueNode;
import ru.nsu.fit.lylova.schema.SchemaElementNode;
import ru.nsu.fit.lylova.schema.SchemaValueNode;
import ru.nsu.fit.lylova.schema.ValueType;

import static org.junit.jupiter.api.Assertions.*;

class SchemaValidatorTest {

    @Test
    void validate() {
        ValueNode v1 = new ValueNode().setValue(new Value().setValueAsInteger(107));
        ValueNode v2 = new ValueNode().setValue(new Value().setValueAsDouble(1.30));
        ElementNode elementNode = new ElementNode("kek").addChildNode(v1).addChildNode(v2);

        SchemaValueNode v3 = new SchemaValueNode().setValueType(ValueType.INT);
        SchemaValueNode v4 = new SchemaValueNode().setValueType(ValueType.DOUBLE);
        SchemaElementNode schema = new SchemaElementNode().setName("kek").addChildNode(v3).addChildNode(v4);

        assertTrue(SchemaValidator.validate(elementNode, schema));
        schema = schema.setName("foo");
        assertFalse(SchemaValidator.validate(elementNode, schema));
        schema = schema.removeChildNode(v4).setName("kek");
        assertFalse(SchemaValidator.validate(elementNode, schema));
    }
}