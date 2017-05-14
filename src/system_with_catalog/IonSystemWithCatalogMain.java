package system_with_catalog;

import csv_to_ion.Utils;
import software.amazon.ion.IonSystem;
import software.amazon.ion.SymbolTable;
import software.amazon.ion.system.IonSystemBuilder;
import software.amazon.ion.system.SimpleCatalog;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by gnavin on 5/14/17.
 */
public class IonSystemWithCatalogMain {

    public static void main(String[] args) {

    }

    //Because the value stream written using the shared symbol table does not contain the symbol mappings,
    //a reader of the stream needs to access the shared symbol table using a catalog.

    //ion-java includes an implementation of IonCatalog called SimpleCatalog,
    //which stores shared symbol tables in memory and will be used here for illustration purposes.

    //The resulting IonSystem can be used to instantiate IonReaders capable of interpreting the shared symbols
    //encountered in the value stream written

    IonSystem getSystemWithCatalog() {
        SimpleCatalog catalog = new SimpleCatalog();
        catalog.putTable(getSharedSymbolTable());
        return IonSystemBuilder.standard().withCatalog(catalog).build();
    }

    SymbolTable getSharedSymbolTable() {
        Iterator<String> symbols = Arrays.asList("id", "type", "state").iterator();
        return Utils.SYSTEM.newSharedSymbolTable("test.csv.columns", 1, symbols);
    }
}
