package ru.nsu.fit.lylova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.lylova.data.node.Attribute;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Value;
import ru.nsu.fit.lylova.data.node.ValueNode;
import ru.nsu.fit.lylova.schema.*;
import ru.nsu.fit.lylova.schema.node.AttributeUse;
import ru.nsu.fit.lylova.schema.node.SchemaElementNode;
import ru.nsu.fit.lylova.schema.node.SchemaValueNode;
import ru.nsu.fit.lylova.schema.node.ValueType;

class DataToSchemeTranslatorTest {
    @Test
    public void simpleTranslatorTest() {
        // check ValueNode into schema
        ValueNode valueNode = new ValueNode().setValue(new Value("Value Node"));
        Assertions.assertThrows(Exception.class, () -> DataToSchemeTranslator.translate(valueNode));
        // check empty schema
        ElementNode root = new ElementNode("schema");
        Assertions.assertThrows(Exception.class, () -> DataToSchemeTranslator.translate(root));
        // check schema with ValueNode
        root.addChildNode(valueNode);
        Assertions.assertThrows(RuntimeException.class, () -> DataToSchemeTranslator.translate(root));
    }

    @Test
    public void complexDataTranslatorTest() throws Exception {
        // declare element "district"
        Attribute name1 = new Attribute().setName("name").setValue("district");
        ElementNode district = new ElementNode("element").addAttribute(name1);
        // district attribute
        Attribute name2 = new Attribute().setName("name").setValue("name");
        Attribute use1 = new Attribute().setName("use").setValue("required");
        ElementNode attributeName = new ElementNode("attribute").addAttribute(name2).addAttribute(use1);
        // district attribute
        Attribute name3 = new Attribute().setName("name").setValue("city");
        Attribute use2 = new Attribute().setName("use").setValue("optional");
        ElementNode attributeCity = new ElementNode("attribute").addAttribute(name3).addAttribute(use2);
        // declare element "school" - district nested element
        Attribute name4 = new Attribute().setName("name").setValue("school");
        Attribute minOccurs1 = new Attribute().setName("minOccurs").setValue("0");
        Attribute maxOccurs1 = new Attribute().setName("maxOccurs").setValue("unbounded");
        ElementNode school = new ElementNode("element")
                .addAttribute(name4)
                .addAttribute(minOccurs1)
                .addAttribute(maxOccurs1);
        // school attribute
        Attribute name5 = new Attribute().setName("name").setValue("city");
        Attribute use3 = new Attribute().setName("use").setValue("optional");
        ElementNode attributeSchoolName = new ElementNode("attribute").addAttribute(name5).addAttribute(use3);
        // make schema
        ElementNode root = new ElementNode("schema")
                .addChildNode(district
                        .addChildNode(attributeName)
                        .addChildNode(attributeCity)
                        .addChildNode(school
                                .addChildNode(attributeSchoolName)));
        // check SchemaDistrict
        SchemaElementNode schema = (SchemaElementNode) DataToSchemeTranslator.translate(root);
        Assertions.assertEquals("district", schema.getName());
        Assertions.assertEquals("city", schema.getAttributeByName("city").getName());
        Assertions.assertEquals(AttributeUse.OPTIONAL, schema.getAttributeByName("city").getUse());
        // check schemaSchool
        Assertions.assertEquals(1, schema.getChildrenNumber());
        SchemaElementNode schemaSchool = (SchemaElementNode) schema.getChild(0);
        Assertions.assertEquals("school", schemaSchool.getName());
        Assertions.assertEquals(0, schemaSchool.getChildrenNumber());
    }

    @Test
    public void typeDataTranslatorTest() throws Exception {
        // declare type
        Attribute typeName = new Attribute().setName("type_name").setValue("grade_type");
        Attribute elementName = new Attribute().setName("element_name").setValue("grade");
        ElementNode typeNode = new ElementNode("type").addAttribute(typeName).addAttribute(elementName);
        // type attribute
        Attribute level = new Attribute().setName("name").setValue("level");
        Attribute use = new Attribute().setName("use").setValue("required");
        ElementNode attributeLevel = new ElementNode("attribute").addAttribute(level).addAttribute(use);
        // type value
        Attribute type = new Attribute().setName("type").setValue("string");
        Attribute minOccurs = new Attribute().setName("minOccurs").setValue("1");
        Attribute maxOccurs = new Attribute().setName("maxOccurs").setValue("unbounded");
        ElementNode value = new ElementNode("value")
                .addAttribute(type)
                .addAttribute(minOccurs)
                .addAttribute(maxOccurs);
        // declare element "district"
        Attribute name1 = new Attribute().setName("name").setValue("district");
        ElementNode district = new ElementNode("element").addAttribute(name1);
        // declare element "school" - district nested element
        Attribute name4 = new Attribute().setName("name").setValue("school");
        Attribute minOccurs1 = new Attribute().setName("minOccurs").setValue("0");
        Attribute maxOccurs1 = new Attribute().setName("maxOccurs").setValue("unbounded");
        ElementNode school = new ElementNode("element")
                .addAttribute(name4)
                .addAttribute(minOccurs1)
                .addAttribute(maxOccurs1);
        // declare element with type "grade_type" - school nested element
        Attribute type1 = new Attribute().setName("type").setValue("grade_type");
        Attribute minOccurs2 = new Attribute().setName("minOccurs").setValue("0");
        Attribute maxOccurs2 = new Attribute().setName("maxOccurs").setValue("12");
        ElementNode grade = new ElementNode("element")
                .addAttribute(type1)
                .addAttribute(minOccurs2)
                .addAttribute(maxOccurs2);
        // make schema
        ElementNode root = new ElementNode("schema")
                .addChildNode(typeNode
                        .addChildNode(attributeLevel)
                        .addChildNode(value))
                .addChildNode(district
                        .addChildNode(school
                                .addChildNode(grade)));
        // check SchemaDistrict
        SchemaElementNode schema = (SchemaElementNode) DataToSchemeTranslator.translate(root);
//        Assertions.assertEquals(1, schema.getChildrenNumber());
        Assertions.assertEquals("district", schema.getName());
        // check schemaSchool
        Assertions.assertEquals(1, schema.getChildrenNumber());
        SchemaElementNode schemaSchool = (SchemaElementNode) schema.getChild(0);
        Assertions.assertEquals("school", schemaSchool.getName());
        // check schemaGrade
        Assertions.assertEquals(1, schemaSchool.getChildrenNumber());
        SchemaElementNode schemaGrade = (SchemaElementNode) schemaSchool.getChild(0);
        Assertions.assertEquals("grade", schemaGrade.getName());
        Assertions.assertEquals(0, schemaGrade.getMinOccurs());
        Assertions.assertEquals(12, schemaGrade.getMaxOccurs());
        Assertions.assertEquals("level", schemaGrade.getAttributeByName("level").getName());
        // check schemaValue
        SchemaValueNode schemaValue = (SchemaValueNode) schemaGrade.getChild(0);
        Assertions.assertEquals(ValueType.STRING, schemaValue.getType());
        Assertions.assertEquals(1, schemaValue.getMinOccurs());
        Assertions.assertEquals(Integer.MAX_VALUE, schemaValue.getMaxOccurs());
    }
}