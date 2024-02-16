package com.bibernate.example.entity;

import com.breskul.bibernate.annotation.Entity;
import com.breskul.bibernate.annotation.Id;
import com.breskul.bibernate.annotation.ManyToOne;
import com.breskul.bibernate.annotation.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "notes")
public class Notes {

  @Id
  private Long id;

  private String note;

  @ManyToOne
  private Person persons;
}
