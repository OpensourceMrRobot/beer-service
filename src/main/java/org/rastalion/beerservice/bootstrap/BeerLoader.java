package org.rastalion.beerservice.bootstrap;

import org.rastalion.beerservice.domain.Beer;
import org.rastalion.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_EAN = "5410188033779";
    public static final String BEER_2_EAN = "0649528786107";
    public static final String BEER_3_EAN = "8801643205355";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Duvel")
                    .beerStyle("Trappistje")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .ean(BEER_1_EAN)
                    .price(new BigDecimal("2.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Augustijn")
                    .beerStyle("Donkere Trappist")
                    .quantityToBrew(50)
                    .minOnHand(16)
                    .ean(BEER_2_EAN)
                    .price(new BigDecimal("3.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Blonde Augustijn")
                    .beerStyle("Bleke Trappist")
                    .quantityToBrew(8001)
                    .minOnHand(420)
                    .ean(BEER_3_EAN)
                    .price(new BigDecimal("3.75"))
                    .build());

//            System.out.println("Loaded Beers: " + beerRepository.count());
        }
    }
}
