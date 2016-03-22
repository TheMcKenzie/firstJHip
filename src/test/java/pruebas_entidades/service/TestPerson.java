package pruebas_entidades.service;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pruebas_entidades.Application;
import pruebas_entidades.domain.Address;
import pruebas_entidades.domain.Person;
import pruebas_entidades.domain.Phone;
import pruebas_entidades.repository.PersistentTokenRepository;
import pruebas_entidades.repository.PersonRepository;
import pruebas_entidades.repository.UserRepository;
import pruebas_entidades.web.rest.PersonResource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional

public class TestPerson {
	
	@Inject
    private PersistentTokenRepository persistentTokenRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;
    
    @Inject
    private PersonResource personResource;
    
    @Inject
    private PersonRepository personRepository;
    
    private Person person_id;

	@SuppressWarnings("unused")
	@Test
	public void test() throws URISyntaxException {
		String name="John Doe";
		
		Person person = new Person();
		person.setName(name);
		
		Address ad1 = new Address();
		ad1.setName("82 Charles Street");
		person.getId_adresss().add(ad1);
		Address ad2 = new Address();
		ad2.setName("34 Charles Street");
		person.getId_adresss().add(ad2);
		
		Phone ph1 = new Phone();
		ph1.setNumber("020202020202020");
		person.getId_phones().add(ph1);
		Phone ph2 = new Phone();
		ph2.setNumber("090909090909090");
		person.getId_phones().add(ph2);
		
		Person id=personRepository.save(person);
		
		Long id2 = id.getId();
		
		id=personRepository.getOne(id2);
		
		System.out.println("El nombre "+id.getName());
		
		assertTrue(id.getName().equals(name));
		
		/*Set <Address> setAd = id.getId_adresss();
		Set <Phone> setPh = id.getId_phones();
		
		System.out.println("The persons id is: "+id2+ " his name is: "+id.getName());
		
		System.out.println("The persons addresses are: "+setAd.toString());
		
		System.out.println("The persons phone numbers are: "+setPh.toString());*/
		
	}

}
