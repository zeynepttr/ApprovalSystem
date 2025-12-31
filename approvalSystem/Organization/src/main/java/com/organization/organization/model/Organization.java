package com.organization.organization.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "parent_id")
  private Long parentId;

  // --- GETTERS & SETTERS ---

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }
}
