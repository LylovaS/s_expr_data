package ru.nsu.fit.lylova.data.node;

import java.util.ArrayList;
import java.util.Collection;

public class ElementNode extends Node {
    private final ArrayList<Attribute> attributes = new ArrayList<>();
    private String name;
    private final ArrayList<Node> childNodes = new ArrayList<>();


    public boolean isValue() {
        return false;
    }


    public boolean isElement() {
        return true;
    }

    public int getChildrenNumber() {
        return childNodes.size();
    }

    public Node getChild(int index) {
        return childNodes.get(index);
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public Attribute getAttributeByName(String name) {
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        return null;
    }

    public ElementNode addChildNode(Node node) {
        if (node == null) {
            throw new NullPointerException("parameter node must not be null");
        }
        if (node.getParent() != null) {
            throw new IllegalArgumentException("node must not have an parent before it is added");
        }
        childNodes.add(node);
        node.setParent(this);
        return this;
    }

    public ElementNode removeChildNode(Node node) {
        if (node == null) {
            throw new NullPointerException("parameter node must not be null");
        }
        if (node.getParent() != this) {
            return this;
        }
        node.setParent(null);
        childNodes.remove(node);
        return this;
    }

    public ElementNode setName(String name) {
        if (name == null) {
            throw new NullPointerException("parameter name must not be null");
        }
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ElementNode addAttribute(Attribute attribute) {
        if (attribute == null) {
            throw new NullPointerException("parameter attribute must not be null");
        }
        attributes.add(attribute);
        return this;
    }
}
