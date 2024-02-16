package com.bibernate.example.entity;

import com.breskul.bibernate.annotation.Column;
import com.breskul.bibernate.annotation.Entity;
import com.breskul.bibernate.annotation.Id;
import com.breskul.bibernate.annotation.ManyToOne;
import com.breskul.bibernate.annotation.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

  @Id
  @Column(columnDefinition = "BIGSERIAL")
  private Long id;

  private String note;

  @ManyToOne
  @ToString.Exclude
  private Person persons;

  public Note(String note, Person persons) {
    this.note = note;
    this.persons = persons;
  }
}
