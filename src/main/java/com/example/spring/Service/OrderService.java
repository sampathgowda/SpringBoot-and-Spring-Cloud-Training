package com.example.spring.Service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.Order.dto.RestaurantOrderDto;
import com.example.spring.entity.orderRequest;
import com.example.spring.repo.restaurantOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class OrderService {
	@Autowired
	restaurantOrderRepository resorderRep;

	public RestaurantOrderDto getOrderDetail(String consumerId) {
		RestaurantOrderDto resorderdto = new RestaurantOrderDto();
		Optional<orderRequest> resOrderPresent = resorderRep.findByCustomerId(consumerId);

		if (resOrderPresent.isPresent()) {
			BeanUtils.copyProperties(resOrderPresent.get(), resorderdto);
			resorderdto.setMessage("The order#" + consumerId + " is found: ");

		} else {

			resorderdto.setMessage("The order#" + consumerId + " is not found: ");
		}

		return resorderdto;
	}

	public RestaurantOrderDto InsertOrder(RestaurantOrderDto Newresorder) {

		orderRequest orderrequest = new orderRequest();
		BeanUtils.copyProperties(Newresorder, orderrequest);
		RestaurantOrderDto resorderdto = new RestaurantOrderDto();

		log.info("######### Request received from controller");
		Optional<orderRequest> resOrderPresent = resorderRep.findByCustomerId(Newresorder.getCustomerId());

		System.out.println("The order present in DB " + resorderRep.findByCustomerId(Newresorder.getCustomerId()));
		System.out.println("The order came in request " + Newresorder.getCustomerId());
		try {
			if (resOrderPresent.isPresent()) {
				log.error("###### The Customer ID is present in database, Please try with different id");
				BeanUtils.copyProperties(orderrequest, resorderdto);
				resorderdto.setMessage("The Customer ID is present in database, Please try with different id");
				resorderdto.setStatus(Boolean.FALSE);

			} else {

				resorderRep.save(orderrequest);
				BeanUtils.copyProperties(orderrequest, resorderdto);
				resorderdto.setMessage("Order saved sucessfully");
			}

		} catch (Exception e) {

			log.error("####### Unable to save the error {} ", e.getMessage());
			resorderdto.setMessage(e.getMessage());
			resorderdto.setStatus(Boolean.FALSE);
		}

		return resorderdto;
	}

	public RestaurantOrderDto UpdateOrder(RestaurantOrderDto newrestaurantorderdto) {

		orderRequest orderrequest = new orderRequest();
		RestaurantOrderDto resorderdto = new RestaurantOrderDto();
		log.info("######## Request is been received to update from controler");
		BeanUtils.copyProperties(newrestaurantorderdto, orderrequest);
		Optional<orderRequest> existsorders = resorderRep.findByCustomerId(newrestaurantorderdto.getCustomerId());
		if (existsorders.isPresent()) {
			log.info("#### I am inside the if ");
			// orderrequest.setOrderDetails(newrestaurantorderdto.getOrderDetails());
			System.out.println(newrestaurantorderdto.getOrderDetails());
			resorderRep.updateOrderlist(newrestaurantorderdto.getOrderDetails(), newrestaurantorderdto.getCustomerId());
			resorderdto.setMessage("Order is been updated sucessfully");
			BeanUtils.copyProperties(orderrequest, resorderdto);
		}

		else {
			log.info("No Mathcing Records found to update");
			BeanUtils.copyProperties(newrestaurantorderdto, resorderdto);
			resorderdto.setMessage("No matching  Records found to update the databse");
			resorderdto.setStatus(Boolean.FALSE);

		}

		return resorderdto;
	}

	public RestaurantOrderDto deleteOrder(String consumerId) {

		RestaurantOrderDto resorderdto = new RestaurantOrderDto();
		Optional<orderRequest> resOrderPresent = resorderRep.findByCustomerId(consumerId);

		log.info("######### Request received from controller");
		if (resOrderPresent.isPresent()) {
			orderRequest orderrequest = new orderRequest();
			BeanUtils.copyProperties(resOrderPresent.get(), resorderdto);
			resorderRep.deleteRecord(consumerId);
			resorderdto.setMessage("Given customer ID is been deleted");
			BeanUtils.copyProperties(resOrderPresent, resorderdto);

		} else {
			resorderdto.setMessage("customer ID reqeusted to purge is not present");
			resorderdto.setStatus(Boolean.FALSE);
		}

		return resorderdto;
	}

}
