package own.api.sales_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import own.api.sales_tracker.dto.Sales;

@Repository
@EnableJpaRepositories
public interface SalesRepository extends JpaRepository<Sales, Integer>{

	boolean existsByPname(String pname);


	List<Sales> findByCategory(String category);

}
