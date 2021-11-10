package service;

import model.Person;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCsv {

    private static final String COMMA_DELIMITER = ",";
    private static final String FILE_PATH = "C:\\Projects\\java-reflection\\src\\resources\\person-list.csv";


    public List<Person> getRecordsBuffer() throws IOException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<String> columnNames;
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            columnNames = Arrays.asList(br.readLine().split(COMMA_DELIMITER));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }

        List<Person> personList = new ArrayList<>();

        for(List<String> row : records) {
            Person person = new Person();
            for(int i = 0; i < columnNames.size(); i++)  {
                String columnName = columnNames.get(i);
                //String cleanValue = columnName.replaceAll("\\P{Print}", "");

                Field field = person.getClass().getDeclaredField(columnName);
                field.setAccessible(true);

                if(field.getType().equals(Integer.class)) {
                    field.set(person, Integer.parseInt(row.get(i)));
                } else {
                    field.set(person, row.get(i));
                }
            }

            personList.add(person);
        }

        return personList;
    }


    private List<String> getRecordFromLine(String line) {
        return Arrays.asList(line.split(COMMA_DELIMITER));
    }


}
