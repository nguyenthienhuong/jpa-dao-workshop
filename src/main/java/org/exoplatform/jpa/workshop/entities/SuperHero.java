package org.exoplatform.jpa.workshop.entities;

import javax.persistence.*;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "SUPERHERO")
public class SuperHero {

  @Id
  @GeneratedValue
  @Column(name = "SUPERHERO_ID")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CIVIL_NAME")
  private String civilName;

  @Column(name = "PICTURE")
  private byte[] picture;

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "SUPERHERO_ID")
  private List<Ennemy> ennemies;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "SUPERHERO_POWER",
          joinColumns = {@JoinColumn(name = "SUPERHERO_ID")},
          inverseJoinColumns = {@JoinColumn(name = "POWER_ID")}
  )
  private List<Power> powers;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCivilName() {
    return civilName;
  }

  public void setCivilName(String civilName) {
    this.civilName = civilName;
  }

  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  public List<Ennemy> getEnnemies() {
    return ennemies;
  }

  public void setEnnemies(List<Ennemy> ennemies) {
    this.ennemies = ennemies;
  }

  public List<Power> getPowers() {
    return powers;
  }

  public void setPowers(List<Power> powers) {
    this.powers = powers;
  }
}
