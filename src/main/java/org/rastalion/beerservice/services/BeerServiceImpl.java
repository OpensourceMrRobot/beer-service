package org.rastalion.beerservice.services;

import lombok.RequiredArgsConstructor;
import org.rastalion.beerservice.domain.Beer;
import org.rastalion.beerservice.repositories.BeerRepository;
import org.rastalion.beerservice.web.controller.NotFoundException;
import org.rastalion.beerservice.web.mappers.BeerMapper;
import org.rastalion.beerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    /*
    The beerMapper gets used twice because our save method will return a beer,
    but we want to return a beerDto, hence the first use of our mapper.

    And the beerRepository wants to save a Beer entity not a BeerDto entity, hence the second use.
     */
    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(
                        beerMapper.beerDtoToBeer(beerDto)
                ));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setEan(beerDto.getEan());

        /*
        Look above for explanation if this confuses you, same logic...
         */
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
