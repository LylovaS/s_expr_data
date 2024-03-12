package ru.nsu.fit.lylova.data.node;

import java.util.ArrayList;
import java.util.Collection;

public class ElementNode implements Node {
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private String name;
    private ArrayList<Node> childNodes = new ArrayList<>();

    @Override
    public boolean isValue() {
        return false;
    }

    @Override
    public boolean isElement() {
        return true;
    }

    public int getChildNumber() {
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
        childNodes.add(node);
        return this;
    }

    public ElementNode removeChildNode(Node node) {
        if (node == null) {
            throw new NullPointerException("parameter node must not be null");
        }
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
