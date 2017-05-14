package write_and_read;

import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonType;
import software.amazon.ion.IonWriter;
import software.amazon.ion.system.IonSystemBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gnavin on 5/14/17.
 */
public class WriteAndReadMain {

    static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    public static void main(String[] args) throws IOException {
        writeAndReadHelloWorld();
    }

    static void writeAndReadHelloWorld() throws IOException {

        //write
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (IonWriter writer = SYSTEM.newTextWriter(out)) {
            writer.stepIn(IonType.STRUCT);  // step into a struct
            writer.setFieldName("hello");   // set the field name for the next value to be written
            writer.writeString("world");    // write the next value
            writer.stepOut();                   // step out of the struct
        }

        final byte[] data = out.toByteArray();    // may contain either text or binary Ion data

        //read
        final InputStream in = new ByteArrayInputStream(data);
        final IonReader reader = SYSTEM.newReader(in);

        reader.next();                            // position the reader at the first value, a struct
        reader.stepIn();                          // step in to the struct
        reader.next();                            // position the reader at the first value in the struct
        String hello = reader.getFieldName();     // retrieve the current value's field name
        String world = reader.stringValue();      // retrieve the current value's String value
        reader.stepOut();                         // step out of the struct
        System.out.println(hello + " " + world);  // prints "hello world"
    }
}
