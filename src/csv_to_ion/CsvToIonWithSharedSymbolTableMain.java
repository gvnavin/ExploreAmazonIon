package csv_to_ion;

import com.opencsv.CSVReader;
import software.amazon.ion.IonWriter;
import software.amazon.ion.SymbolTable;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;


/**
 * Created by gnavin on 5/14/17.
 */
public class CsvToIonWithSharedSymbolTableMain {

    public static void main(String[] args) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        //An application that wishes to convert this data into the Ion format can generate a symbol table containing the column names.
        //This reduces encoding size and improves read efficiency.
        final CSVReader csvReader = new CSVReader(new FileReader(Utils.CSV_FILE));

        final SymbolTable sharedSymbolTable = getSharedSymbolTable();
        try (IonWriter binaryWriter = Utils.SYSTEM.newBinaryWriter(out, sharedSymbolTable)) {
            Utils.convertCsvToIon(binaryWriter, csvReader);
        }
        Utils.printIon(out);
    }


    //Using local symbol tables requires the local symbol table (including all of its symbols) to be written
    //at the beginning of the value stream. Consider an Ion stream that represents CSV data with many columns.
    //Although local symbol tables will optimize writing and reading each value,
    //including the entire symbol table itself in the value stream adds overhead that increases with the number of columns.
    //If it is feasible for the writers and readers of the stream to agree on a pre-defined shared symbol table,
    //this overhead can be reduced.

    static SymbolTable getSharedSymbolTable() {
        final Iterator<String> symbols = Arrays.asList("id", "type", "state").iterator();
        return Utils.SYSTEM.newSharedSymbolTable("test.csv.columns", 1, symbols);
    }
}
