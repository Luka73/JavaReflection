package principal;

import service.ReadCsv;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainClass {
    public static void main(String[] args) {
        ReadCsv readCsv = new ReadCsv();

        try {
         readCsv.getRecordsBuffer();
        } catch (IllegalAccessException
                | NoSuchFieldException
                | IOException
                | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
