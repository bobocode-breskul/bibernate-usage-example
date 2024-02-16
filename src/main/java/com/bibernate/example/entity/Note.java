package com.bibernate.example.entity;

import com.breskul.bibernate.annotation.Column;
import com.breskul.bibernate.annotation.Entity;
import com.breskul.bibernate.annotation.Id;
import com.breskul.bibernate.annotation.ManyToOne;
import com.breskul.bibernate.annotation.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a note entity that stores textual information. This entity is linked to a
 * {@link Person} to indicate ownership or association.
 */
@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

  /**
   * The unique identifier for each note. It is automatically generated as a big serial value by the
   * database.
   */
  @Id
  @Column(columnDefinition = "BIGSERIAL")
  private Long id;

  /**
   * The textual content of the note.
   */
  private String note;

  /**
   * The person associated with this note. This relationship is managed via a many-to-one
   * association, indicating that multiple notes can be associated with a single person. Marked as
   * {@link ToString.Exclude} to exclude from output.
   */
  @ManyToOne
  @ToString.Exclude
  private Person persons;

  public Note(String note, Person persons) {
    this.note = note;
    this.persons = persons;
  }
}
