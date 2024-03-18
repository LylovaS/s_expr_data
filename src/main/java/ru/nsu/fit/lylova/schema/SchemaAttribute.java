package ru.nsu.fit.lylova.schema;

import java.security.InvalidParameterException;

public class SchemaAttribute {
    private String name = null;
    private AttributeUse use = null;

    public SchemaAttribute(String name) {
        if (name == null) {
            throw new InvalidParameterException("name must be not null");
        }
        this.name = name;
        this.use = AttributeUse.OPTIONAL;
    }
    public SchemaAttribute(String name, AttributeUse use) {
        if (name == null) {
            throw new InvalidParameterException("name must be not null");
        }
        this.name = name;
        if (use == null) {
            throw new InvalidParameterException("use must be not null");
        }
        this.use = use;
    }
    public void setName(String name) {
        if (name == null) {
            throw new InvalidParameterException("name must be not null");
        }
        this.name = name;
    }

    public void setUse(AttributeUse use) {
        if (use == null) {
            throw new InvalidParameterException("use must be not null");
        }
        this.use = use;
    }

    public String getName() {
        return name;
    }

    public AttributeUse getUse() {
        return use;
    }
}
