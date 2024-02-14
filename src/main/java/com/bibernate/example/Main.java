package com.bibernate.example;

import com.breskul.bibernate.demo.entity.Person;
import com.breskul.bibernate.persistence.Persistence;
import com.breskul.bibernate.persistence.Session;
import com.breskul.bibernate.persistence.SessionFactory;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    SessionFactory sessionFactory = Persistence.createSessionFactory();
    try (Session session = sessionFactory.openSession()) {

      printPersons(session);

      // Create
      Person person = new Person("Ivan", "Franko", 59);
      session.persist(person);
      System.out.println("A new person has been created: " + person);

      // Find
      Person foundPerson = session.findById(Person.class, person.getId());
      System.out.println("The user has been found by ID: " + foundPerson);

      // Update
      foundPerson.setAge(40);
      System.out.println("The user has been updated: " + foundPerson);

      // Delete
      session.delete(foundPerson);
      System.out.println("The user has been deleted: " + foundPerson);

      printPersons(session);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void printPersons(Session session) {
    String query = "SELECT * FROM persons";
    List<Person> personList = session.executeNativeQuery(query, Person.class);
    if (personList.isEmpty()) {
      System.out.println("\nThere is no persons yet...\n");
    } else {
      System.out.println("\nAll persons:");
      System.out.println("-----------------------------------------------------------");
      for (Person person : personList) {
        System.out.println(person);
      }
      System.out.println("-----------------------------------------------------------\n");
    }
  }
}
