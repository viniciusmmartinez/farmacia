package br.com.farmacia;

import br.com.farmacia.model.Pharmacy;
import br.com.farmacia.repository.PharmacyRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author vinicius.montouro
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PharmacyRepositoryTest {

    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData(){
        Pharmacy pharmacy = Pharmacy.builder()
                                .tel("16992148350")
                                .address("Rua Padre Francisco Manoel Malaquias, 233")
                                .name("Test 1")
                                .build();
        this.pharmacyRepository.save(pharmacy);

        Assertions.assertThat(pharmacy.getId()).isNotNull();
        Assertions.assertThat(pharmacy.getName()).isEqualTo("Test 1");
        Assertions.assertThat(pharmacy.getAddress()).isEqualTo("Rua Padre Francisco Manoel Malaquias, 233");
        Assertions.assertThat(pharmacy.getTel()).isEqualTo("16992148350");
    }

    @Test
    public void deleteShouldPersistData(){
        Pharmacy pharmacy = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 2")
                .build();
        this.pharmacyRepository.save(pharmacy);
        this.pharmacyRepository.delete(pharmacy);
        Assertions.assertThat(pharmacyRepository.findById(pharmacy.getId())).isEmpty();
    }

    @Test
    public void updateSholdPersistData(){
        Pharmacy pharmacy = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 3")
                .build();
        this.pharmacyRepository.save(pharmacy);
        pharmacy.setTel("1122224444");
        pharmacy.setName("Test 4");
        this.pharmacyRepository.save(pharmacy);
        pharmacy = this.pharmacyRepository.findById(pharmacy.getId()).get();
        Assertions.assertThat(pharmacy.getName()).isEqualTo("Test 4");
        Assertions.assertThat(pharmacy.getTel()).isEqualTo("1122224444");
    }

    @Test
    public void searchByNameIgnoreCaseSholdReturnData(){
        Pharmacy pharmacy1 = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 3")
                .build();
        Pharmacy pharmacy2 = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 3")
                .build();
        this.pharmacyRepository.save(pharmacy1);
        this.pharmacyRepository.save(pharmacy2);
        Pageable pageable = PageRequest.of(0, 8);

        Page<List<Pharmacy>> byNameIgnoreCaseContaining =
                this.pharmacyRepository.findByNameIgnoreCaseContaining("Test 3", pageable);
        Assertions.assertThat(byNameIgnoreCaseContaining.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void searchByNameSholdReturnData(){
        Pharmacy pharmacy1 = Pharmacy.builder()
                .tel("16992148350")
                .address("Rua Padre Francisco Manoel Malaquias, 233")
                .name("Test 4")
                .build();
        this.pharmacyRepository.save(pharmacy1);
        pharmacy1 = this.pharmacyRepository.findByName("Test 4");
        Assertions.assertThat(pharmacy1.getName()).isEqualTo("Test 4");
    }

    @Test
    public void createWhenNameIsNullShouldThrowContraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field 'name' is mandatory");
        this.pharmacyRepository.save(new Pharmacy());
    }
    @Test
    public void createWhenPhoneIsNullShouldThrowContraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field 'tel' is mandatory");
        this.pharmacyRepository.save(new Pharmacy());
    }
    @Test
    public void createWhenAddressIsNullShouldThrowContraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field 'address' is mandatory");
        this.pharmacyRepository.save(new Pharmacy());
    }


}
