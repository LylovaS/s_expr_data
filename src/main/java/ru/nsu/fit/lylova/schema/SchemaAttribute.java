package ru.nsu.fit.lylova.schema;

import java.security.InvalidParameterException;

public class SchemaAttribute {
    private String name = null;
    private AttributeUse use = null;

    SchemaAttribute(String name) {
        this.name = name;
    }
    SchemaAttribute(String name, AttributeUse use) {
        this.name = name;
        this.use = use;
    }
    public void setName(String name) {
        if (name == null) {
            throw new InvalidParameterException("name must be not null");
        }
        this.name = name;
    }

    public void setUse(AttributeUse use) {
        this.use = use;
    }

    public String getName() {
        return name;
    }

    public AttributeUse getUse() {
        return use;
    }
}
