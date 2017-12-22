package icoder.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = Client.TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

  static final String TABLE = "client";
  private static final String CLIENT_ID = "client_id";
  private static final String NAME = "name";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = CLIENT_ID)
  @JsonProperty(value = CLIENT_ID)
  private Integer idEntity;

  @Column(name = NAME)
  private String name;


  public Integer getIdEntity() {
    return idEntity;
  }

  public void setIdEntity(Integer idEntity) {
    this.idEntity = idEntity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
