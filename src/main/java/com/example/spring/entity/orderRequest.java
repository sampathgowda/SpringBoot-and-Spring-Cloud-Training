package com.example.spring.entity;

import javax.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "Order_Detail_TB")
public class orderRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "Customer_ID") // this line will create column name as provided or by default JPA will create variable name
	private String customerId;
	@Column(name = "Order_detail")
    private String orderDetails;

}
