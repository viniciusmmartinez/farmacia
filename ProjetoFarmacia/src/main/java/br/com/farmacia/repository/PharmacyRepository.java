package br.com.farmacia.repository;

import br.com.farmacia.model.Pharmacy;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vinicius.montouro
 */
@Repository
public interface PharmacyRepository extends PagingAndSortingRepository<Pharmacy, Long> {

    Pharmacy findByName(String username);

}
