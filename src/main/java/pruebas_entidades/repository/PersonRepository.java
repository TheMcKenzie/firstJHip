package pruebas_entidades.repository;

import pruebas_entidades.domain.Address;
import pruebas_entidades.domain.Person;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Person entity.
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
	
	 @Query("select distinct address from Address address left join fetch address.id_persons")
	    List<Address> findAllWithEagerRelationships();

}
