package br.com.farmacia;

import br.com.farmacia.model.Pharmacy;
import br.com.farmacia.service.PharmacyService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.toIntExact;
import static java.util.Arrays.asList;

/**
 * @author vinicius.montouro
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PharmacyControllerTest {

    @LocalServerPort
    private int port;
    @MockBean
    private PharmacyService pharmacyService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Iterable<Pharmacy> pharmacies = asList(Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 1")
                .build(),
                Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 2")
                .build());
        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia";
        URI uri = new URI(baseUrl);
        Pageable pageable = PageRequest.of(0, 8);
        BDDMockito.when(pharmacyService.findPageable(pageable)).thenReturn(pharmacies);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findAllSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Iterable<Pharmacy> pharmacies = asList(Pharmacy.builder()
                        .tel("16992148350")
                        .address("Rua Padre Francisco Manoel Malaquias, 233")
                        .name("Test 1")
                        .build(),
                Pharmacy.builder()
                        .tel("16992148350")
                        .address("Rua Padre Francisco Manoel Malaquias, 233")
                        .name("Test 2")
                        .build());

        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia/all";
        URI uri = new URI(baseUrl);
        BDDMockito.when(pharmacyService.findAll())
                .thenReturn(pharmacies);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findByIdSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Pharmacy pharmacy = Pharmacy.builder()
                        .tel("16992148350")
                        .address("Rua Padre Francisco Manoel Malaquias, 233")
                        .name("Test 1")
                        .id(1L)
                        .build();

        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia/1";
        URI uri = new URI(baseUrl);
        BDDMockito.when(pharmacyService.findById(pharmacy.getId()))
                .thenReturn(pharmacy);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void createSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Pharmacy pharmacy = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 1")
                .id(10L)
                .build();


        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia";
        URI uri = new URI(baseUrl);
        BDDMockito.when(pharmacyService.create(pharmacy))
                .thenReturn(pharmacy);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, pharmacy,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Pharmacy pharmacy = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 1")
                .id(10L)
                .build();

        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia";
        URI uri = new URI(baseUrl);
        BDDMockito.when(pharmacyService.update(pharmacy))
                .thenReturn(pharmacy);
        restTemplate.put(uri,pharmacy);
    }

    @Test
    public void deleteSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Pharmacy pharmacy = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 1")
                .id(10L)
                .build();

        final String baseUrl = "http://localhost:" + port + "/apontador/v1/farmacia/10";
        URI uri = new URI(baseUrl);
        restTemplate.delete(baseUrl,pharmacy);
    }


}
