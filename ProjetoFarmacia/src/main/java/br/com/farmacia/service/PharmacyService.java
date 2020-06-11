package br.com.farmacia.service;

import br.com.farmacia.exception.ResourceNotFoundException;
import br.com.farmacia.model.Pharmacy;
import br.com.farmacia.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vinicius.montouro
 */
@Slf4j
@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    public Iterable<Pharmacy> findPageable(Pageable pageable) {
        return pharmacyRepository.findAll(pageable);
    }
    public Page<List<Pharmacy>> search(String searchTerm, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"name");
        return pharmacyRepository.findByNameIgnoreCaseContaining(searchTerm.toLowerCase(),pageRequest);
    }

    @Transactional
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

    @Transactional
    public Pharmacy update(Pharmacy pharmacy) {
        Pharmacy entity = pharmacyRepository.findById(pharmacy.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setName(pharmacy.getName());
        entity.setAddress(pharmacy.getAddress());
        entity.setTel(pharmacy.getTel());

        return pharmacyRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        Pharmacy entity = pharmacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        pharmacyRepository.delete(entity);
    }

}
