package org.targi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.targi.model.Booth;

public interface BoothRepository extends JpaRepository<Booth, Long> {
}

