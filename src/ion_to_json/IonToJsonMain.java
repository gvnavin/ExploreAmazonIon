package ion_to_json;

import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonWriter;
import software.amazon.ion.system.IonSystemBuilder;
import software.amazon.ion.system.IonTextWriterBuilder;

import java.io.IOException;

/**
 * Created by gnavin on 5/14/17.
 */
public class IonToJsonMain {

    static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    static final String TEXT_ION = "{ data:annot::{ foo:null.string, bar:(2 + 2) }, time:1969-07-20T20:18Z }";

    public static void main(String[] args) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        try (final IonWriter jsonWriter = IonTextWriterBuilder.json().withPrettyPrinting().build(stringBuilder)) {
            rewrite(TEXT_ION, jsonWriter);
        }
        System.out.println(stringBuilder.toString());
    }

    static void rewrite(String textIon, IonWriter writer) throws IOException {
        IonReader reader = SYSTEM.newReader(textIon);
        writer.writeValues(reader);
    }
}
