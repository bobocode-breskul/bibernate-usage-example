package com.bibernate.example;

import static com.bibernate.example.util.PersonUtil.printPersonList;

import com.bibernate.example.entity.Note;
import com.bibernate.example.entity.Person;
import com.bibernate.example.exception.SessionOpenException;
import com.breskul.bibernate.persistence.Persistence;
import com.breskul.bibernate.persistence.Session;
import com.breskul.bibernate.persistence.SessionFactory;
import com.breskul.bibernate.transaction.Transaction;
import java.util.List;

/**
 * The {@code Main} class demonstrates the basic usage of a Bibernate ORM framework. It includes
 * operations such as creating, reading, updating, and deleting (CRUD) Person entities.
 * <p>
 * To turn off SQL messaging, change the {@code bibernate.show_sql} property to {@code false} in the
 * application.properties file.
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

  /**
   * Performs database operations such as creating, finding, updating, and deleting Person entities.
   * It also demonstrates the addition of Notes to a Person.
   *
   * @param session the current session to perform operations within a transaction
   */
  private static void performDatabaseOperations(Session session) {
    createPersons(session);
    printPersons(session);

    Person person = findPersonById(session, 1);
    updatePersonAge(session, person, 40);
    deletePerson(session, person);
    printPersons(session);

    Person secondPerson = findPersonById(session, 2);
    addNotes(session, secondPerson);

    addPersonsByTransaction(session);

    printPersons(session);
  }

  /**
   * Creates two new Person entities and persists them in the database.
   *
   * @param session the current session to persist new Person entities
   */
  private static void createPersons(Session session) {
    Person person = new Person("Ivan", "Franko", 59);
    Person person2 = new Person("Taras", "Shevchenko", 47);

    session.persist(person);
    session.persist(person2);

    System.out.println("A new person has been created: " + person);
    System.out.println("A new person has been created: " + person2);
  }

  /**
   * Finds a Person entity by its ID.
   *
   * @param session the current session to perform the search
   * @param id      the unique identifier of the Person to find
   * @return the found Person entity, or null if not found
   */
  private static Person findPersonById(Session session, long id) {
    Person foundPerson = session.findById(Person.class, id);
    System.out.println("The user has been found by ID: " + foundPerson);
    return foundPerson;
  }

  /**
   * Updates the age of a Person entity.
   *
   * @param session the current session to perform the update
   * @param person  the Person entity to update
   * @param newAge  the new age value for the Person
   */
  private static void updatePersonAge(Session session, Person person, int newAge) {
    person.setAge(newAge);
    System.out.println("The user has been updated:     " + person);
  }

  /**
   * Deletes a Person entity from the database.
   *
   * @param session the current session to perform the deletion
   * @param person  the Person entity to delete
   */
  private static void deletePerson(Session session, Person person) {
    session.delete(person);
    session.flush();
    System.out.println("The user has been deleted:     " + person);
  }

  /**
   * Adds Note entities to a Person.
   *
   * @param session the current session to persist new Note entities
   * @param person  the Person entity to which Notes are added
   */
  private static void addNotes(Session session, Person person) {
    Note note1 = new Note("Svitaye, kray neba palaye", person);
    Note note2 = new Note("Meni trynadtsyatyy mynalo", person);

    session.persist(note1);
    session.persist(note2);

    System.out.println("Note has been added: " + note1);
    System.out.println("Note has been added: " + note2);
  }

  /**
   * Adds two predefined {@link Person} entities to the database within a single transaction. This
   * method begins a transaction, persists two new Person instances, and commits the transaction. In
   * case of an exception, the transaction is rolled back to ensure data consistency.
   *
   * @param session The Hibernate {@link Session} instance used for database operations. This
   *                session should be active and open before calling this method.
   */
  private static void addPersonsByTransaction(Session session) {
    Transaction transaction = session.getTransaction();
    try {
      transaction.begin();

      Person thirdPerson = new Person("Ivan", "Kotliarevsky", 69);
      Person fourthPerson = new Person("Larysa", "Kosach", 42);

      session.persist(thirdPerson);
      session.persist(fourthPerson);

      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
    }
  }

  /**
   * Prints the list of all Person entities present in the database. This method demonstrates the
   * execution of a BiQL query to fetch and print Person entities.
   *
   * @param session the current session used to execute the query
   */
  private static void printPersons(Session session) {
    String query = "from Person";
    List<Person> personList = session.executeBiQLQuery(query, Person.class);
    printPersonList(personList);
  }
}
