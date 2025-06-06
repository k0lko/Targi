// RegistrationFormService.java
package org.targi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.targi.model.RegistrationForm;
import org.targi.repository.RegistrationFormRepository;

@Service
@RequiredArgsConstructor
public class RegistrationFormService {
    private final RegistrationFormRepository repository;

    public RegistrationForm save(RegistrationForm form) {
        return repository.save(form);
    }
}