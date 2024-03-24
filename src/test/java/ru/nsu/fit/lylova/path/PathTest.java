package ru.nsu.fit.lylova.path;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.node.ElementNode;
import ru.nsu.fit.lylova.data.node.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void evaluate() throws IOException {
        Node node = DataReader.parseDataFromReader(new FileReader("src/test/resources/data1.txt"));

        // path="element"
        Path path = new Path(PathType.ABSOLUTE).addStep(new Step(StepTransition.JUST_CURRENT, "element"));
        Collection<Node> res = path.evaluate(node);
        assertEquals(1, res.size());
        for (Node i : res) {
            assertTrue(i.isElement());
            assertEquals("\"1\"", ((ElementNode) i).getAttributeByName("id").getValue());
        }

        path = path.setType(PathType.RELATIVE);
        res = path.evaluate(node);
        List<String> correctResult = new LinkedList<>(Arrays.asList("\"2\"", "\"5\""));
        assertEquals(2, res.size());
        for (Node i : res) {
            assertTrue(i.isElement());
            String id = ((ElementNode) i).getAttributeByName("id").getValue();
            assertTrue(correctResult.contains(id));
            correctResult.remove(id);
        }
        assertTrue(correctResult.isEmpty());
    }
}