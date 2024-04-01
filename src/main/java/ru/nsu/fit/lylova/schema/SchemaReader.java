package ru.nsu.fit.lylova.schema;

import ru.nsu.fit.lylova.data.DataReader;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.schema.node.SchemaNode;

import java.io.Reader;

public class SchemaReader {
    public static SchemaNode parseSchema(Reader reader) throws Exception {
        Node node = DataReader.parseData(reader);
        return DataToSchemeTranslator.translate(node);
    }
}
