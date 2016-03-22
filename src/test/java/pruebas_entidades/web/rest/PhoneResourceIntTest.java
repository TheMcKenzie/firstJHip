package pruebas_entidades.web.rest;

import pruebas_entidades.Application;
import pruebas_entidades.domain.Phone;
import pruebas_entidades.repository.PhoneRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PhoneResource REST controller.
 *
 * @see PhoneResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PhoneResourceIntTest {

    private static final String DEFAULT_NUMBER = "AAAAA";
    private static final String UPDATED_NUMBER = "BBBBB";

    @Inject
    private PhoneRepository phoneRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPhoneMockMvc;

    private Phone phone;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PhoneResource phoneResource = new PhoneResource();
        ReflectionTestUtils.setField(phoneResource, "phoneRepository", phoneRepository);
        this.restPhoneMockMvc = MockMvcBuilders.standaloneSetup(phoneResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        phone = new Phone();
        phone.setNumber(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createPhone() throws Exception {
        int databaseSizeBeforeCreate = phoneRepository.findAll().size();

        // Create the Phone

        restPhoneMockMvc.perform(post("/api/phones")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(phone)))
                .andExpect(status().isCreated());

        // Validate the Phone in the database
        List<Phone> phones = phoneRepository.findAll();
        assertThat(phones).hasSize(databaseSizeBeforeCreate + 1);
        Phone testPhone = phones.get(phones.size() - 1);
        assertThat(testPhone.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPhones() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        // Get all the phones
        restPhoneMockMvc.perform(get("/api/phones?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(phone.getId().intValue())))
                .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getPhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

        // Get the phone
        restPhoneMockMvc.perform(get("/api/phones/{id}", phone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(phone.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPhone() throws Exception {
        // Get the phone
        restPhoneMockMvc.perform(get("/api/phones/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

		int databaseSizeBeforeUpdate = phoneRepository.findAll().size();

        // Update the phone
        phone.setNumber(UPDATED_NUMBER);

        restPhoneMockMvc.perform(put("/api/phones")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(phone)))
                .andExpect(status().isOk());

        // Validate the Phone in the database
        List<Phone> phones = phoneRepository.findAll();
        assertThat(phones).hasSize(databaseSizeBeforeUpdate);
        Phone testPhone = phones.get(phones.size() - 1);
        assertThat(testPhone.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void deletePhone() throws Exception {
        // Initialize the database
        phoneRepository.saveAndFlush(phone);

		int databaseSizeBeforeDelete = phoneRepository.findAll().size();

        // Get the phone
        restPhoneMockMvc.perform(delete("/api/phones/{id}", phone.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Phone> phones = phoneRepository.findAll();
        assertThat(phones).hasSize(databaseSizeBeforeDelete - 1);
    }
}
