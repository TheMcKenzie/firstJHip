package pruebas_entidades.web.rest;

import com.codahale.metrics.annotation.Timed;
import pruebas_entidades.domain.Person;
import pruebas_entidades.repository.PersonRepository;
import pruebas_entidades.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Person.
 */
@RestController
@RequestMapping("/api")

public class FormPhoneAddress {

	private final Logger log = LoggerFactory.getLogger(PersonResource.class);
	
	@RequestMapping(value = "/form1",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    @Timed
	    public void createPerson() {
			System.out.println("estamos aqui");
	        /*log.debug("REST request to save Person : {}", person);
	        if (person.getId() != null) {
	            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("person", "idexists", "A new person cannot already have an ID")).body(null);
	        }
	        Person result = personRepository.save(person);
	        return ResponseEntity.created(new URI("/api/persons/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert("person", result.getId().toString()))
	            .body(result);*/
	    }
	
}
