package com.example.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring.entity.orderRequest;
import java.lang.String;
import java.util.Optional;
@Repository
public interface restaurantOrderRepository extends JpaRepository<orderRequest, Long> {
	//List<String> findByCustomerId(String customerid);
	Optional<orderRequest> findByCustomerId(String customerid);
	@Modifying
	@Query("UPDATE orderRequest A SET A.orderDetails = ?1 where A.customerId = ?2 ")
	void updateOrderlist(String orderDetails ,String customerId );
	@Modifying
	@Query("DELETE from orderRequest A where A.customerId = ?1 ")
	void deleteRecord(String customerId);
		
}
