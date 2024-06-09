package net.essalmi.patientsapp.repository;

import net.essalmi.patientsapp.security.Entities.AppRole;
import net.essalmi.patientsapp.security.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
