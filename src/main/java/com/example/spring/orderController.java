package com.example.spring;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring.Order.dto.RestaurantOrderDto;
import com.example.spring.Service.OrderService;
import lombok.extern.slf4j.Slf4j;


//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/restaurantorder")
@Slf4j

public class orderController {
	@Autowired
	OrderService orderservice;
@GetMapping("/getOrder")
public ResponseEntity<RestaurantOrderDto> getOrder(@RequestParam(value = "consumerId") String consumerId ) {
	
	log.info("Calling Service to check the avilability of the order:{}",consumerId);
	
	RestaurantOrderDto resOrderDto = orderservice.getOrderDetail(consumerId);
	log.info("Resposne received sucesfully");
	
	ResponseEntity<RestaurantOrderDto> responseEntity =  new ResponseEntity<>(resOrderDto,HttpStatus.OK);  
	return  responseEntity;
}

@PostMapping("/CreateOrder")
public ResponseEntity<RestaurantOrderDto> CreateOrder(@RequestBody RestaurantOrderDto restaurantorderdto)
{
	
log.info("Request is been received");	
RestaurantOrderDto resOrderDto = orderservice.InsertOrder(restaurantorderdto);
log.info("The order is been sucessfully cretaed");

ResponseEntity<RestaurantOrderDto> responseEntity;

if (resOrderDto.getStatus()) {
	
	responseEntity = new ResponseEntity<>(resOrderDto,HttpStatus.OK);
	
} else {
	responseEntity = new ResponseEntity<>(resOrderDto,HttpStatus.BAD_REQUEST);
}
return 	responseEntity;

}

@PutMapping("/update")

	public ResponseEntity<RestaurantOrderDto> UpdateRecords(@RequestBody RestaurantOrderDto Newrestaurantorderdto)
	
	{	
	log.info("Request is been received");	
	RestaurantOrderDto resOrderDto = orderservice.UpdateOrder(Newrestaurantorderdto);
	log.info("The order is been sucessfully cretaed");

	ResponseEntity<RestaurantOrderDto> Updatedresponse= new ResponseEntity<>(resOrderDto,HttpStatus.OK);
	return Updatedresponse;
		
	}
@DeleteMapping("/deleteOrder")

public ResponseEntity<RestaurantOrderDto> deleteOrder(@RequestParam(value = "cosumerId")String consumerId) {
	
	RestaurantOrderDto resOrderDto = orderservice.deleteOrder(consumerId);
	ResponseEntity<RestaurantOrderDto> deleteRecord= new ResponseEntity<>(resOrderDto,HttpStatus.OK);
	
	return deleteRecord;
	
}

}
