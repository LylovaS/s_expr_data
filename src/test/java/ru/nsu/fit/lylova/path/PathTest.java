package ru.nsu.fit.lylova.path;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.path.step.AxisType;
import ru.nsu.fit.lylova.path.step.Step;
import ru.nsu.fit.lylova.path.step.StepTransition;
import ru.nsu.fit.lylova.path.step.requirement.PredicateType;
import ru.nsu.fit.lylova.path.step.requirement.RequirementPartType;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void evaluate() throws IOException {
        Node node = DataReader.parseDataFromReader(new FileReader("src/test/resources/data1.txt"));
        Context context = new Context(node);
        // path="element"
        Path path = new Path(PathType.ABSOLUTE).addStep(new Step(StepTransition.JUST_CURRENT, "element"));
        Collection<Node> res = path.evaluate(context);
        assertEquals(1, res.size());
        for (Node i : res) {
            assertTrue(i.isElement());
            assertEquals("1", ((ElementNode) i).getAttributeByName("id").getValue());
        }

        path = path.setType(PathType.RELATIVE);
        res = path.evaluate(context);
        List<String> correctResult = new LinkedList<>(Arrays.asList("2", "5"));
        assertEquals(2, res.size());
        for (Node i : res) {
            assertTrue(i.isElement());
            String id = ((ElementNode) i).getAttributeByName("id").getValue();
            assertTrue(correctResult.contains(id));
            correctResult.remove(id);
        }
        assertTrue(correctResult.isEmpty());
    }

    @Test
    void compile() throws Exception {
        Path path = Path.compile("kek");
        assertEquals(PathType.RELATIVE, path.getType());

        path = Path.compile("/kek");
        assertEquals(PathType.ABSOLUTE, path.getType());

        path = Path.compile("//kek");
        assertEquals(PathType.ABSOLUTE, path.getType());
        assertEquals(2, path.getSteps().size());
        assertEquals(StepTransition.JUST_CURRENT, path.getSteps().get(0).getTransition());
        assertEquals(AxisType.CURRENT, path.getSteps().get(0).getAxisType());
        assertEquals(StepTransition.ANY_INNER, path.getSteps().get(1).getTransition());

        path = Path.compile("el/kek//do");
        assertNotNull(path);
        assertEquals(3, path.getSteps().size());

        path = Path.compile(".");
        assertEquals(1, path.getSteps().size());
        Step step = path.getSteps().get(0);
        assertEquals(AxisType.CURRENT, step.getAxisType());
        assertNull(step.getRequirement());
        assertEquals(StepTransition.JUST_CURRENT, step.getTransition());

        path = Path.compile("kek/kek2");
        assertEquals(2, path.getSteps().size());
        assertEquals(StepTransition.JUST_CURRENT, path.getSteps().get(1).getTransition());

        path = Path.compile("kek//kek2");
        assertEquals(2, path.getSteps().size());
        assertEquals(StepTransition.ANY_INNER, path.getSteps().get(1).getTransition());

        path = Path.compile("*");
        assertEquals(1, path.getSteps().size());
        assertEquals(AxisType.CHILD, path.getSteps().get(0).getAxisType());

        path = Path.compile("@value");
        assertEquals(1, path.getSteps().size());
        assertEquals(AxisType.CHILD_VALUE, path.getSteps().get(0).getAxisType());

        path = Path.compile("@element");
        assertEquals(1, path.getSteps().size());
        assertEquals(AxisType.CHILD_ELEMENT, path.getSteps().get(0).getAxisType());

        path = Path.compile(".");
        assertEquals(1, path.getSteps().size());
        assertEquals(AxisType.CURRENT, path.getSteps().get(0).getAxisType());

        path = Path.compile("..");
        assertEquals(1, path.getSteps().size());
        assertEquals(AxisType.PARENT, path.getSteps().get(0).getAxisType());

        path = Path.compile("*[pred(\"kek\", @name)]");
        assertEquals(1, path.getSteps().size());
        step = path.getSteps().get(0);
        assertNotNull(step.getRequirement());
        assertEquals("name", step.getRequirement().getRightPart());
        assertEquals(RequirementPartType.ATTR_NAME, step.getRequirement().getRightPartType());
        assertEquals("kek", step.getRequirement().getLeftPart());
        assertEquals(RequirementPartType.VALUE, step.getRequirement().getLeftPartType());
        assertEquals("pred", step.getRequirement().getPredicateName());

        path = Path.compile("*[@a1 != @a2]");
        assertEquals(1, path.getSteps().size());
        step = path.getSteps().get(0);
        assertNotNull(step.getRequirement());
        assertEquals("a2", step.getRequirement().getRightPart());
        assertEquals(RequirementPartType.ATTR_NAME, step.getRequirement().getRightPartType());
        assertEquals("a1", step.getRequirement().getLeftPart());
        assertEquals(RequirementPartType.ATTR_NAME, step.getRequirement().getLeftPartType());
        assertEquals(PredicateType.INEQUALITY, step.getRequirement().getPredicateType());

        path = Path.compile("*[@* = @*]");
        assertEquals(1, path.getSteps().size());
        step = path.getSteps().get(0);
        assertNotNull(step.getRequirement());
        assertEquals("*", step.getRequirement().getRightPart());
        assertEquals(RequirementPartType.ATTR_NAME, step.getRequirement().getRightPartType());
        assertEquals("*", step.getRequirement().getLeftPart());
        assertEquals(RequirementPartType.ATTR_NAME, step.getRequirement().getLeftPartType());
        assertEquals(PredicateType.EQUALITY, step.getRequirement().getPredicateType());
    }
}