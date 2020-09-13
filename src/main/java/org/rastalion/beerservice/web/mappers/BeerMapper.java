package org.rastalion.beerservice.web.mappers;

import org.mapstruct.Mapper;
import org.rastalion.beerservice.domain.Beer;
import org.rastalion.beerservice.web.model.BeerDto;

/*
Take a look at after compiling:

target/generated-sources/annotations/org/rastalion/beerservice/web/mappers/BeerMapperImpl.java
line: 38

 */

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);

}
