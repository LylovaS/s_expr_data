package ru.nsu.fit.lylova.data.node;

//TODO добавить для каждой вершины какая вершина является предком. Понадобится для xpath
public interface Node {

    boolean isValue();

    boolean isElement();
}
