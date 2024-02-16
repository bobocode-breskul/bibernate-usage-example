package com.bibernate.example.entity;

import com.breskul.bibernate.annotation.Column;
import com.breskul.bibernate.annotation.DynamicUpdate;
import com.breskul.bibernate.annotation.Entity;
import com.breskul.bibernate.annotation.Id;
import com.breskul.bibernate.annotation.OneToMany;
import com.breskul.bibernate.annotation.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a Person entity with basic personal information and a list of notes associated with
 * the person. This class is annotated for use with the Bibernate ORM framework, indicating it's a
 * managed entity corresponding to the "persons" table in the database.
 */
@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "persons")
@ToString
public class Person {

  /**
   * Unique identifier for the Person. It's automatically managed by the database (BIGSERIAL).
   */
  @Id
  @Column(columnDefinition = "BIGSERIAL")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "age")
  private Integer age;

  /**
   * List of Note entities associated with this person. It represents a one-to-many relationship
   * between Person and Note entities.
   */
  @OneToMany
  private List<Note> noteList;

  public Person(String firstName, String lastName, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }
}
