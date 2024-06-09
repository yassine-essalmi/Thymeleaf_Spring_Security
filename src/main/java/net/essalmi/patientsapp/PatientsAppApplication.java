package net.essalmi.patientsapp;

import lombok.RequiredArgsConstructor;
import net.essalmi.patientsapp.entities.Patient;
import net.essalmi.patientsapp.repository.PatientRepository;
import net.essalmi.patientsapp.security.Entities.AppRole;
import net.essalmi.patientsapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientsAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientsAppApplication.class, args);
    }

    //@Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            Patient patient1 = Patient.builder()
                    .nom("Hassan")
                    .prenom("Essalmi")
                    .dateNaissance(new Date())
                    .malade(false)
                    .score(115)
                    .build();
            Patient patient2 = Patient.builder()
                    .nom("Mohamed")
                    .prenom("Essalmi")
                    .dateNaissance(new Date())
                    .malade(true)
                    .score(125)
                    .build();
            Patient patient3 = Patient.builder()
                    .nom("Aliman")
                    .prenom("Essalmi")
                    .dateNaissance(new Date())
                    .malade(false)
                    .score(110)
                    .build();
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user1");
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user2");
            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("user3");
            UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin");
            if (u1 == null) jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build());
            if (u2 == null) jdbcUserDetailsManager.createUser(User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build());
            if (u3 == null) jdbcUserDetailsManager.createUser(User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER").build());
            if (admin == null) jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build());
        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");

            accountService.addNewUser("user1", "1234", "email@email.com","1234");
            accountService.addNewUser("user2", "1234", "email@email.com","1234");
            accountService.addNewUser("admin", "1234", "admin@email.com","1234");

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
        };
    }
}
