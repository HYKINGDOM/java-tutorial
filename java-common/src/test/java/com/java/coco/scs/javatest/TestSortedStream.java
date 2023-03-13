package com.java.coco.scs.javatest;

import com.java.coco.domian.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.java.coco.utils.ListSortUtil.sortListByField;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 自定义排序
 *
 * @author yihur
 */
public class TestSortedStream {


    private List<Person> personList;


    @Before
    public void init_data() {
        personList = Arrays.asList(new Person(null, 18), new Person("Peter", 23), new Person("Anda", 23), new Person("David", 12));
    }


    @Test
    public void test_java_stream_sorted_01() {

        String sortName = "name";
        boolean desc = Boolean.FALSE;
        boolean descNull = Boolean.FALSE;

        List<Person> persons = sortListByField(personList, sortName, Person.class, desc, descNull);

        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getName()).isEqualTo("Peter");
        assertThat(persons.get(1).getName()).isEqualTo("David");
        assertThat(persons.get(2).getName()).isEqualTo("Anda");
        assertThat(persons.get(3).getName()).isNull();
    }


    @Test
    public void test_java_stream_sorted_02() {

        String sortName = "name";
        boolean desc = Boolean.TRUE;
        boolean descNull = Boolean.FALSE;

        List<Person> persons = sortListByField(personList, sortName, Person.class, desc, descNull);
        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getName()).isNull();
        assertThat(persons.get(1).getName()).isEqualTo("Anda");
        assertThat(persons.get(2).getName()).isEqualTo("David");
        assertThat(persons.get(3).getName()).isEqualTo("Peter");
    }


    @Test
    public void test_java_stream_sorted_03() {

        String sortName = "name";
        boolean desc = Boolean.FALSE;
        boolean descNull = Boolean.TRUE;

        List<Person> persons = sortListByField(personList, sortName, Person.class, desc, descNull);

        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getName()).isNull();
        assertThat(persons.get(1).getName()).isEqualTo("Peter");
        assertThat(persons.get(2).getName()).isEqualTo("David");
        assertThat(persons.get(3).getName()).isEqualTo("Anda");
    }


    @Test
    public void test_java_stream_sorted_04() {

        String sortName = "name";
        boolean desc = Boolean.TRUE;
        boolean descNull = Boolean.TRUE;

        List<Person> persons = sortListByField(personList, sortName, Person.class, desc, descNull);
        assertThat(persons.size()).isEqualTo(4);
        assertThat(persons.get(0).getName()).isEqualTo("Anda");
        assertThat(persons.get(1).getName()).isEqualTo("David");
        assertThat(persons.get(2).getName()).isEqualTo("Peter");
        assertThat(persons.get(3).getName()).isNull();
    }

}
