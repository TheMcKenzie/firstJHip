package pruebas_entidades.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Phone.
 */
@Entity
@Table(name = "phone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private String number;
    
    @ManyToOne
    @JoinColumn(name = "id_person_id")
    private Person id_person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }

    public Person getId_person() {
        return id_person;
    }

    public void setId_person(Person person) {
        this.id_person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phone phone = (Phone) o;
        if(phone.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Phone{" +
            "id=" + id +
            ", number='" + number + "'" +
            '}';
    }
}
