package ru.nsu.fit.lylova;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Value;
import ru.nsu.fit.lylova.data.node.ValueNode;
import ru.nsu.fit.lylova.schema.SchemaValidator;
import ru.nsu.fit.lylova.schema.node.*;

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

    @Test
    public void simlpeValidateTest() {
        ValueNode valueNode = new ValueNode().setValue(new Value("String Value"));
        ElementNode elementNode = new ElementNode("Element Node").addChildNode(valueNode);
        SchemaValueNode schemaValueNode = new SchemaValueNode().setValueType(ValueType.STRING);
        SchemaElementNode schemaElementNode = new SchemaElementNode().setName("Element Node").addChildNode(schemaValueNode);

        assertFalse(SchemaValidator.validate(valueNode, schemaElementNode));
        assertFalse(SchemaValidator.validate(elementNode, schemaValueNode));
        assertTrue(SchemaValidator.validate(valueNode, schemaValueNode));
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));

        schemaValueNode.setValueType(ValueType.DOUBLE);
        assertFalse(SchemaValidator.validate(elementNode, schemaElementNode));
        schemaValueNode.setValueType(ValueType.STRING);
        schemaValueNode.setOccurs(0, 0);
        assertFalse(SchemaValidator.validate(elementNode, schemaElementNode));
        schemaValueNode.setOccurs(0, 10);
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));
    }

    @Test
    public void AttributeValidateTest() {
        ValueNode valueNode = new ValueNode().setValue(new Value("String Value"));
        ElementNode elementNode = new ElementNode("Element Node").addChildNode(valueNode);
        SchemaValueNode schemaValueNode = new SchemaValueNode().setValueType(ValueType.STRING);
        SchemaElementNode schemaElementNode = new SchemaElementNode().setName("Element Node").addChildNode(schemaValueNode);

        Attribute attribute = new Attribute();
        attribute.setName("attribute_name");
        attribute.setValue("attribute_value");
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));
        SchemaAttribute schemaAttribute = new SchemaAttribute("attribute_name", AttributeUse.OPTIONAL);
        schemaElementNode.addAttribute(schemaAttribute);
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));
        elementNode.addAttribute(attribute);
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));
        schemaAttribute = new SchemaAttribute("attribute_name", AttributeUse.REQUIRED);
        schemaElementNode.addAttribute(schemaAttribute);
        assertTrue(SchemaValidator.validate(elementNode, schemaElementNode));
        schemaAttribute = new SchemaAttribute("attribute_name", AttributeUse.PROHIBITED);
        schemaElementNode.addAttribute(schemaAttribute);
        assertFalse(SchemaValidator.validate(elementNode, schemaElementNode));
    }
}