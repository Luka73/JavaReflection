package principal;

import service.ReadCsv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) {
        ReadCsv readCsv = new ReadCsv();

        try {
            readCsv.getRecordScanner();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            readCsv.getRecordsBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
