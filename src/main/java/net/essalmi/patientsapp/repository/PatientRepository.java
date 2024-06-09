package net.essalmi.patientsapp.repository;

import net.essalmi.patientsapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String name, String prenom, PageRequest pageRequest);
}
