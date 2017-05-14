package csv_to_ion;

import com.opencsv.CSVReader;
import software.amazon.ion.IonWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by gnavin on 5/14/17.
 */
public class CsvToIonMain {

    public static void main(String[] args) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final CSVReader csvReader = new CSVReader(new FileReader(Utils.CSV_FILE));

        try (IonWriter textWriter = Utils.SYSTEM.newTextWriter(out)) {
            Utils.convertCsvToIon(textWriter, csvReader);
        }
        Utils.printIon(out);
    }
}
