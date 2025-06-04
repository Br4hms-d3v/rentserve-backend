package be.brahms.rent_server.repositories;

import be.brahms.rent_server.models.entities.SecondResidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondResidenceRepository extends JpaRepository<SecondResidence, Long> {
}
