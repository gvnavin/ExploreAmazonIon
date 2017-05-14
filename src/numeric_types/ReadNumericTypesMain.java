package numeric_types;

import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonType;
import software.amazon.ion.system.IonSystemBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by gnavin on 5/14/17.
 */
public class ReadNumericTypesMain {

    static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    static final String NUMBER_LIST = "[ 1.23456, 123456, 1.2345e6, ]";

    public static void main(String[] args) throws IOException {
        readNumericTypes();
    }

    static void readNumericTypes() throws IOException {

        // expected values
        BigDecimal first = new BigDecimal("1.23456");
        BigInteger second = new BigInteger("123456");
        double third = 1.2345e6;

        IonReader reader = SYSTEM.newReader(NUMBER_LIST);
        reader.next();
        reader.stepIn();

        assertEquals(IonType.DECIMAL, reader.next());
        assertEquals(first, reader.bigDecimalValue());
        assertEquals(first.doubleValue(), reader.doubleValue(), 1e-9);

        assertEquals(IonType.INT, reader.next());
        assertEquals(second, reader.bigIntegerValue());
        assertEquals(second.longValue(), reader.longValue());
        assertEquals(second.intValue(), reader.intValue());

        assertEquals(IonType.FLOAT, reader.next());
        assertEquals(third, reader.doubleValue(), 1e-9);

        reader.stepOut();
    }

}
