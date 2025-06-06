package org.targi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.targi.model.Booth;
import org.targi.repository.BoothRepository;

@Component
public class DataInitializer {

    private final BoothRepository boothRepository;

    public DataInitializer(BoothRepository boothRepository) {
        this.boothRepository = boothRepository;
    }

    @PostConstruct
    public void init() {
        if (boothRepository.count() == 0) {
            boothRepository.save(new Booth("A1", 100, 200));
            boothRepository.save(new Booth("A2", 150, 200));
            boothRepository.save(new Booth("B1", 100, 250));
            boothRepository.save(new Booth("B2", 150, 250));
        }
    }
}
