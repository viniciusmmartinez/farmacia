package br.com.farmacia.service;

import br.com.farmacia.exception.ResourceNotFoundException;
import br.com.farmacia.model.Pharmacy;
import br.com.farmacia.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author vinicius.montouro
 */
@Slf4j
@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    public Iterable<Pharmacy> findPageable(Pageable pageable) {
        log.info("Listing all pharmacys");
        return pharmacyRepository.findAll(pageable);
    }

    public Pharmacy create(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    public Iterable<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy findById(Long id) {
        return pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Pharmacy update(Pharmacy pharmacy) {
        Pharmacy entity = pharmacyRepository.findById(pharmacy.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setName(pharmacy.getName());
        entity.setAddress(pharmacy.getAddress());
        entity.setTel(pharmacy.getTel());

        return pharmacyRepository.save(entity);
    }

    public void delete(Long id) {
        Pharmacy entity = pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        pharmacyRepository.delete(entity);
    }

}
