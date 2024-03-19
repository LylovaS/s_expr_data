package ru.nsu.fit.lylova.path;

public class Step {
    private StepTransition transition;
    private AxisType axisType;
    private String elementName;
    private Predicate predicate = null;


    public Step setTransition(StepTransition transition) {
        this.transition = transition;
        return this;
    }

    public StepTransition getTransition() {
        return transition;
    }

    public Step(StepTransition transition, AxisType axisType) {
        this.transition = transition;
        this.axisType = axisType;
        elementName = null;
    }

    public Step(StepTransition transition, String elementName) {
        this.elementName = elementName;
        this.axisType = null;
        this.elementName = elementName;
    }

    public Step setPredicate(String attributeName, String attributeValue, PredicateType type) {
        predicate = new Predicate(attributeName, attributeValue, type);
        return this;
    }

    public Predicate getPredicate() {
        return predicate;
    }


    public Step removePredicate() {
        predicate = null;
        return this;
    }
}

