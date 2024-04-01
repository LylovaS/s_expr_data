package ru.nsu.fit.lylova.data;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import ru.nsu.fit.lylova.DataLexer;
import ru.nsu.fit.lylova.DataParser;
import ru.nsu.fit.lylova.data.node.Node;
import ru.nsu.fit.lylova.schema.DataToSchemeTranslator;
import ru.nsu.fit.lylova.schema.SchemaValidator;
import ru.nsu.fit.lylova.schema.node.SchemaNode;

import java.io.IOException;
import java.io.Reader;

public class DataReader {
    public static Node parseData(Reader reader) throws IOException {
        DataLexer lexer = new DataLexer(CharStreams.fromReader(reader));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DataParser parser = new DataParser(tokens);
        ParseTree tree = parser.calc();
        ParseTreeWalker walker = new ParseTreeWalker();
        DataWalker dataWalker = new DataWalker();
        walker.walk(dataWalker, tree);
        return dataWalker.getDataNode();
    }

    public static Node parseDataWithSchema(Reader dataReader, Reader schemaReader) throws Exception {
        Node data = parseData(dataReader);

        Node schema_tmp = parseData(schemaReader);
        SchemaNode schema = DataToSchemeTranslator.translate(schema_tmp);

        if (!SchemaValidator.validate(data, schema)) {
            throw new Exception("data does not match the schema (((");
        }
        return data;
    }

    public static Node parseDataWithSchema(Reader dataReader, SchemaNode schema) throws Exception {
        Node data = parseData(dataReader);

        if (!SchemaValidator.validate(data, schema)) {
            throw new Exception("data does not match the schema (((");
        }
        return data;
    }
}
