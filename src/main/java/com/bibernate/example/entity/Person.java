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

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "persons")
@ToString
public class Person {

  @Id
  @Column(columnDefinition = "BIGSERIAL")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @OneToMany
  private List<Note> noteList;

  public Person(String firstName, String lastName, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }
}
