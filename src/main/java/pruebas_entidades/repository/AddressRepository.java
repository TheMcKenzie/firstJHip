package pruebas_entidades.repository;

import pruebas_entidades.domain.Address;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Address entity.
 */
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query("select distinct address from Address address left join fetch address.id_persons")
    List<Address> findAllWithEagerRelationships();

    @Query("select address from Address address left join fetch address.id_persons where address.id =:id")
    Address findOneWithEagerRelationships(@Param("id") Long id);

}
