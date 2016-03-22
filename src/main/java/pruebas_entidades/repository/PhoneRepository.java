package pruebas_entidades.repository;

import pruebas_entidades.domain.Phone;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Phone entity.
 */
public interface PhoneRepository extends JpaRepository<Phone,Long> {

}
