package ru.nsu.fit.lylova.data.node;

//TODO добавить для каждой вершины какая вершина является предком. Понадобится для xpath
public abstract class Node {
    private Node parent = null;
    public abstract boolean isValue();

    public abstract boolean isElement();

    public Node getParent() {
        return parent;
    }
    void setParent(Node parent) {
        this.parent = parent;
    }
}
