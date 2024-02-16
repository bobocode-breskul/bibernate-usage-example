package com.bibernate.example.util;

import com.bibernate.example.entity.Person;
import java.util.List;

public class PersonUtil {

  public static void printPersonList(List<Person> personList) {
    if (personList.isEmpty()) {
      System.out.println("\nThere is no persons yet...\n");
    } else {
      System.out.println("\nAll persons:");
      System.out.println("--------------------------------------------------------------");
      for (Person person : personList) {
        System.out.println(person);
      }
      System.out.println("--------------------------------------------------------------\n");
    }
  }
}
