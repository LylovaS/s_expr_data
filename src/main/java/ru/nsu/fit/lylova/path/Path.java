package ru.nsu.fit.lylova.path;

import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Path {
    private PathType type;
    private final ArrayList<Step> steps = new ArrayList<>();

    public Path(PathType type) {
        this.type = type;
    }

    public PathType getType() {
        return type;
    }

    public Path setType(PathType type) {
        this.type = type;
        return this;
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void removeStep(int index) {
        steps.remove(index);
    }

    public Collection<Node> evaluate(Node contextNode) {
        if (contextNode == null) {
            throw new NullPointerException("parameter contextNode must not be null");
        }
        if (this.steps.isEmpty()) {
            throw new IllegalStateException("path must contain at least one step");
        }
        Set<Node> result = new HashSet<>();
        switch (type) {
            case RELATIVE -> {
                result.add(contextNode);
            }
            case ABSOLUTE -> {
                result.add(new DocumentNode("documentNode").addChildNode(findRootOfData(contextNode)));
            }
        }
        for (Step step : steps) {
            if (step.getTransition() == StepTransition.ANY_INNER) {
                Set<Node> nodesAfterTransition = new HashSet<>();
                for (Node node : result) {
                    addSubtreeOfNodes(node, nodesAfterTransition);
                }
                result = nodesAfterTransition;
            }

            Set<Node> nodesAfterAxis = new HashSet<>();
            for (Node node : result) {
                addNodesAfterAxis(node, step, nodesAfterAxis);
            }
            result = nodesAfterAxis;

            if (step.getPredicate() != null) {
                result = result.stream()
                        .filter(node -> step.getPredicate()
                                .validateNode(node))
                        .collect(Collectors.toSet());
            }
        }
        return result;
    }


    private static void addNodesAfterAxis(Node node, Step step, Set<Node> result) {
        if (step.getAxisType() == null) {
            if (!node.isElement()) {
                return;
            }
            ElementNode elementNode = (ElementNode) node;
            for (int i = 0; i < elementNode.getChildrenNumber(); ++i) {
                Node child = elementNode.getChild(i);
                if (child.isElement() && ((ElementNode) child).getName().equals(step.getElementName())) {
                    result.add(child);
                }
            }
            return;
        }
        switch (step.getAxisType()) {
            case CHILD -> {
                if (!node.isElement()) {
                    return;
                }
                ElementNode elementNode = (ElementNode) node;
                for (int i = 0; i < elementNode.getChildrenNumber(); ++i) {
                    Node child = elementNode.getChild(i);
                    result.add(child);
                }
            }
            case PARENT -> {
                if (node.getParent() != null) {
                    result.add(node.getParent());
                }
            }
            case CURRENT -> {
                result.add(node);
            }
            case CHILD_ELEMENT -> {
                if (!node.isElement()) {
                    return;
                }
                ElementNode elementNode = (ElementNode) node;
                for (int i = 0; i < elementNode.getChildrenNumber(); ++i) {
                    Node child = elementNode.getChild(i);
                    if (child.isElement()) {
                        result.add(child);
                    }
                }
            }
            case CHILD_VALUE -> {
                if (!node.isElement()) {
                    return;
                }
                ElementNode elementNode = (ElementNode) node;
                for (int i = 0; i < elementNode.getChildrenNumber(); ++i) {
                    Node child = elementNode.getChild(i);
                    if (child.isValue()) {
                        result.add(child);
                    }
                }
            }
        }
    }

    private static void addSubtreeOfNodes(Node root, Set<Node> result) {
        if (result.contains(root)) {
            return;
        }
        result.add(root);
        if (root.isElement()) {
            ElementNode node = (ElementNode) root;
            for (int i = 0; i < node.getChildrenNumber(); ++i) {
                addSubtreeOfNodes(node.getChild(i), result);
            }
        }
    }

    private static class DocumentNode extends ElementNode {
        public DocumentNode(String name) {
            super(name);
        }

        public ElementNode addChildNode(Node node) {
            if (node == null) {
                throw new NullPointerException("parameter node must not be null");
            }
            if (node.getParent() != null) {
                throw new IllegalArgumentException("node must not have parent before it is added");
            }
            childNodes.add(node);
            return this;
        }
    }

    private static Node findRootOfData(Node node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        return node;
    }
}
