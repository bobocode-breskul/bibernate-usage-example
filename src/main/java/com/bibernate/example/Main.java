package com.bibernate.example;

import com.breskul.bibernate.demo.entity.Person;
import com.breskul.bibernate.persistence.Persistence;
import com.breskul.bibernate.persistence.Session;
import com.breskul.bibernate.persistence.SessionFactory;

public class Main {

  public static void main(String[] args) {
    SessionFactory sessionFactory = Persistence.createSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      // Create
      Person person = new Person("Taras", "TEST", 20);
      session.persist(person);

      // Find
      Person foundPerson = session.findById(Person.class, person.getId());
      System.out.println(foundPerson);

      // Update
      foundPerson.setAge(40);
      System.out.println(foundPerson);

      // Delete
      session.delete(foundPerson);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}