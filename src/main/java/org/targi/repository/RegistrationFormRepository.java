package org.targi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.targi.model.RegistrationForm;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {
}
