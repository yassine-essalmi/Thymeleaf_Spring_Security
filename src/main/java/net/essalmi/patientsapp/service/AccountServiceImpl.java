package net.essalmi.patientsapp.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.essalmi.patientsapp.repository.AppRoleRepository;
import net.essalmi.patientsapp.repository.AppUserRepository;
import net.essalmi.patientsapp.security.Entities.AppRole;
import net.essalmi.patientsapp.security.Entities.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String usernqme, String password, String email, String confirmedPassword) {
        AppUser user = appUserRepository.findByUsername(usernqme);
        if (user != null) throw new RuntimeException("User already exists");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser newUser = AppUser.builder().username(usernqme).password(passwordEncoder.encode(password)).email(email).build();
        return appUserRepository.save(newUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole != null) throw new RuntimeException("Role already exists");
        AppRole newRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(newRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.getRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException("Role not found");
        user.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
