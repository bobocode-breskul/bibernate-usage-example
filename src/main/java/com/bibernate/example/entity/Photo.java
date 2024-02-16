package com.bibernate.example.entity;

import com.breskul.bibernate.annotation.Column;
import com.breskul.bibernate.annotation.DynamicUpdate;
import com.breskul.bibernate.annotation.Entity;
import com.breskul.bibernate.annotation.Id;
import com.breskul.bibernate.annotation.OneToMany;
import com.breskul.bibernate.annotation.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Data
@DynamicUpdate
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "photo")
public class Photo {

  @Id
  @Column(columnDefinition = "BIGSERIAL")
  private Long id;

  @Column(name = "url")
  private String url;

  @Column(name = "description")
  private String description;


  @ToString.Exclude
  @Setter(AccessLevel.PRIVATE)
  @OneToMany
  private List<PhotoComment> comments = new ArrayList<>();

  public void addComment(PhotoComment comment) {
    this.comments.add(comment);
    comment.setPhoto(this);
  }

  public void removeComment(PhotoComment comment) {
    this.comments.remove(comment);
    comment.setPhoto(null);
  }
}
