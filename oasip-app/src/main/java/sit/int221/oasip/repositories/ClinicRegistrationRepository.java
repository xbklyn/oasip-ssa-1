package sit.int221.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasip.entities.ClinicsRegistration;

public interface ClinicRegistrationRepository extends JpaRepository<ClinicsRegistration, Integer> {
}
