package com.bibernate.example.util;

import com.bibernate.example.entity.Person;
import java.util.List;

/**
 * Utility class for operations related to the Person entity. This class provides static methods to
 * perform common tasks involving Person objects, such as printing a list of Person entities to the
 * console.
 */
public class PersonUtil {

  /**
   * Prints the details of each person in a given list to the console. If the list is empty, it
   * displays a message indicating that there are no persons. Otherwise, it prints a header,
   * followed by the details of each person, and then a footer.
   *
   * @param personList the list of Person entities to be printed. If the list is empty, a message
   *                   indicating no persons are available will be printed.
   */
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
