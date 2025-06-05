package be.brahms.rent_server.repositories;

import be.brahms.rent_server.models.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Bill entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
