package org.rastalion.beerservice.repositories;

import org.rastalion.beerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/*
Shift + Right Click On PagingAndSortingRepository - download sources

Also click on the CrudRepository in the PagingAndSortingRepository
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
