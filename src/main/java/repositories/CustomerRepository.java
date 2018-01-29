package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findByLastName(String ln);
}
