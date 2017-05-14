package pretty_print;

import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonWriter;
import software.amazon.ion.system.IonSystemBuilder;
import software.amazon.ion.system.IonTextWriterBuilder;

import java.io.IOException;

/**
 * Created by gnavin on 5/14/17.
 */
public class PrettyPrintMain {

    static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    static final String UN_FORMATTED = "{ level1:{ level2:{ level3:\"foo\" }, x:2 }, y:[a,b,c] }";

    public static void main(String[] args) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        try (final IonWriter prettyWriter = IonTextWriterBuilder.pretty().build(stringBuilder)) {
            final IonReader reader = SYSTEM.newReader(UN_FORMATTED);
            prettyWriter.writeValues(reader);
        }
        System.out.println(stringBuilder.toString());
    }
}
