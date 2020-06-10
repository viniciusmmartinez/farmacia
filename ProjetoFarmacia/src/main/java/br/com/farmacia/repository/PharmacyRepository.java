package br.com.farmacia.repository;

import br.com.farmacia.model.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author vinicius.montouro
 */
@Repository
public interface PharmacyRepository extends PagingAndSortingRepository<Pharmacy, Long> {

    Pharmacy findByName(String name);

    Page<List<Pharmacy>> findByNameContaining(String name, Pageable pageable);

}
