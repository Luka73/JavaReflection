import model.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReadCsvTest {
    @Test
    public void givenObject_whenGetsFieldNamesAtRuntime_thenCorrect() {
        Object person = new Person();
        Field[] fields = person.getClass().getDeclaredFields();

        List<String> actualFieldNames = getFieldNames(fields);

        assertTrue(Arrays.asList("name", "age")
                .containsAll(actualFieldNames));
    }


    @Test
    public void whenSetIntegerFields_thenSuccess() throws Exception {
        Person person = new Person();

        Field ageField = person.getClass().getDeclaredField("age");
        ageField.setAccessible(true);

        Integer age = 26;
        ageField.setInt(person, age);
        assertEquals(age, person.getAge());

        Field nameFields = person.getClass().getDeclaredField("name");
        nameFields.setAccessible(true);

        String name = "Luana";
        nameFields.set(person, name);
        assertEquals(name, person.getName());
    }


    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }
}
