package ru.nsu.fit.lylova.path;

import ru.nsu.fit.lylova.data.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Context {
    private final Map<String, BiFunction<String, String, Boolean>> predicates = new HashMap<>();
    private Node contextNode;
    Context(Node contextNode) {
        this.contextNode = contextNode;
    }

    BiFunction<String, String, Boolean> getPredicateByName(String name) {
        return predicates.get(name);
    }

    Set<String> getAllPredicateNames() {
        return predicates.keySet();
    }

    Context addPredicate(String name, BiFunction<String, String, Boolean> predicate) {
        predicates.put(name, predicate);
        return this;
    }

    Context removePredicateByName(String name) {
        predicates.remove(name);
        return this;
    }

    Context setContextNode(Node contextNode) {
        this.contextNode = contextNode;
        return this;
    }

    Node getContextNode() {
        return contextNode;
    }
}
