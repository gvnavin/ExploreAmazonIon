package read_from_string;

import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.system.IonSystemBuilder;

import java.io.IOException;

/**
 * Created by gnavin on 5/14/17.
 */
public class ReadFromStringMain {
    static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    public static void main(String[] args) throws IOException {
        readHelloWorldFromString();
    }

    static void readHelloWorldFromString() {

        final String helloWorld = "{ hello:\"world\" }";
        IonReader reader = SYSTEM.newReader(helloWorld);
        reader.next();                            // position the reader at the first value, a struct
        reader.stepIn();                          // step in to the struct
        reader.next();                            // position the reader at the first value in the struct
        String hello = reader.getFieldName();     // retrieve the current value's field name
        String world = reader.stringValue();      // retrieve the current value's String value
        reader.stepOut();                         // step out of the struct
        System.out.println(hello + " " + world);  // prints "hello world"


    }
}
