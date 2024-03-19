package ru.nsu.fit.lylova.path;

import java.util.ArrayList;
import java.util.List;

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
}
