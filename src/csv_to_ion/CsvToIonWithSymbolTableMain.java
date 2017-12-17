package csv_to_ion;

import com.opencsv.CSVReader;
import software.amazon.ion.IonWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by gnavin on 5/14/17.
 */
public class CsvToIonWithSymbolTableMain {

    public static void main(String[] args) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        //An application that wishes to convert this data into the Ion format can generate a symbol table containing the column names.
        //This reduces encoding size and improves read efficiency.
        final CSVReader csvReader = new CSVReader(new FileReader(Utils.CSV_FILE));

        try (IonWriter binaryWriter = Utils.SYSTEM.newBinaryWriter(out)) {
            Utils.convertCsvToIon(binaryWriter, csvReader);
        }
    
        try(OutputStream outputStream = new FileOutputStream("ionBinaryFile.ion")) {
            out.writeTo(outputStream);
        }
        
        Utils.printIon(out);
    }
}
