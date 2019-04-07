package com.javaapi.test.test.javafeature.version8.streams.streamcollect;

import com.javaapi.test.test.javafeature.version8.streams.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by user on 2019/4/7
 * refer
 * https://blog.csdn.net/Leolu007/article/details/78487102
 */
public class ClientCollectToMap {
    private List<Person> phpProgrammers;
    private List<Person> javaProgrammers;

    @Before
    public void setUp() {
        javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
    }

    public Map<String, Person> getNameMap(List<Person> accounts) {
        return accounts.stream().collect(Collectors.toMap(Person::getFirstName, Function.identity(), (key1, key2) -> key2));
    }


    public Map<String, Person> getNameLinkedMap(List<Person> accounts) {
        Collector<Person, ?, LinkedHashMap<String, Person>> collector;
        collector = Collectors.toMap(Person::getFirstName, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new);
        return accounts.stream().collect(collector);
    }

    @Test
    public void testToMap(){
        Map<String, Person> nameAccountMap = getNameMap(javaProgrammers);
        System.out.println("nameAccountMap = " + nameAccountMap);
    }


    @Test
    public void testToMap2(){
        Map<String, Person> nameAccountMap = getNameLinkedMap(javaProgrammers);
        System.out.println("nameAccountMap = " + nameAccountMap);
    }
}
