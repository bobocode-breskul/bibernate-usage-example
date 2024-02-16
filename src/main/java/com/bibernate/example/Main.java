package com.bibernate.example;

import static com.bibernate.example.util.PersonUtil.printPersonList;

import com.bibernate.example.entity.Person;
import com.bibernate.example.exception.SessionOpenException;
import com.breskul.bibernate.persistence.Persistence;
import com.breskul.bibernate.persistence.Session;
import com.breskul.bibernate.persistence.SessionFactory;
import java.util.List;

/**
 * The {@code Main} class demonstrates the basic usage of a session in a Hibernate-like ORM framework.
 * It includes operations such as creating, reading, updating, and deleting (CRUD) Person entities.
 * <p>
 * To turn off SQL messaging change {@code bibernate.show_sql} property to {@code false} in application.properties file.
 * </p>
 */
public class Main {

  private static final SessionFactory sessionFactory = Persistence.createSessionFactory();

  public static void main(String[] args) {
    try (Session session = sessionFactory.openSession()) {
      printPersons(session);
      performDatabaseOperations(session);
    } catch (Exception e) {
      throw new SessionOpenException("Failed to open session", e);
    }
  }

  private static void performDatabaseOperations(Session session) {
    createPersons(session);
    printPersons(session);

    Person person = findPersonById(session, 1);
    updatePersonAge(session, person, 40);
    deletePerson(session, person);

    printPersons(session);
  }

  private static void createPersons(Session session) {
    Person person = new Person("Ivan", "Franko", 59);
    Person person2 = new Person("Taras", "Shevchenko", 47);
    session.persist(person);
    session.persist(person2);
    System.out.println("A new person has been created: " + person);
    System.out.println("A new person has been created: " + person2);
  }

  private static Person findPersonById(Session session, long id) {
    Person foundPerson = session.findById(Person.class, id);
    System.out.println("The user has been found by ID: " + foundPerson);
    return foundPerson;
  }

  private static void updatePersonAge(Session session, Person person, int newAge) {
    person.setAge(newAge);
    System.out.println("The user has been updated:     " + person);
  }

  private static void deletePerson(Session session, Person person) {
    session.delete(person);
    session.flush();
    System.out.println("The user has been deleted:     " + person);
  }

  private static void printPersons(Session session) {
    String query = "SELECT * FROM persons";
    List<Person> personList = session.executeNativeQuery(query, Person.class);
    printPersonList(personList);
  }
}
