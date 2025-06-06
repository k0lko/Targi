//package org.targi.service;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Service;
//import org.targi.model.Booth;
//import org.targi.repository.BoothRepository;
//
//import java.util.List;
//
//@Service
//public class BoothService {
//    private final BoothRepository boothRepository;
//
//    public BoothService(BoothRepository boothRepository) {
//        this.boothRepository = boothRepository;
//    }
//
//    @PostConstruct
//    public void init() {
//        if (boothRepository.count() == 0) {
//            boothRepository.saveAll(List.of(
//                    new Booth("A1", 100, 150, false, null),
//                    new Booth("A2", 200, 150, false, null),
//                    new Booth("A3", 300, 150, false, null)
//            ));
//        }
//    }
//
//
//    public List<Booth> getAllBooths() {
//        return boothRepository.findAll();
//    }
//}
