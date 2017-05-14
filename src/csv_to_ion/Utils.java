package csv_to_ion;

import com.opencsv.CSVReader;
import software.amazon.ion.IonReader;
import software.amazon.ion.IonSystem;
import software.amazon.ion.IonType;
import software.amazon.ion.IonWriter;
import software.amazon.ion.system.IonSystemBuilder;
import software.amazon.ion.system.IonTextWriterBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gnavin on 5/14/17.
 */
public class Utils {

    public static final String CSV_FILE = "test.csv";
    public static final IonSystem SYSTEM = IonSystemBuilder.standard().build();

    public static void printIon(ByteArrayOutputStream out) throws IOException {
        final byte[] data = out.toByteArray();
        final InputStream in = new ByteArrayInputStream(data);
        final StringBuilder stringBuilder = new StringBuilder();
        try (final IonWriter prettyWriter = IonTextWriterBuilder.pretty().build(stringBuilder)) {
            final IonReader reader = SYSTEM.newReader(in);
            prettyWriter.writeValues(reader);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void convertCsvToIon(IonWriter writer, final CSVReader csvReader) throws IOException {
        try {

            String[] line;
            while ((line = csvReader.readNext()) != null) {

                writer.stepIn(IonType.STRUCT);

                writer.setFieldName("id");
                writer.writeInt(Integer.parseInt(line[0]));

                writer.setFieldName("type");
                writer.writeString(line[1]);

                writer.setFieldName("state");
                writer.writeBool(Boolean.valueOf(line[2]));

                writer.stepOut();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
