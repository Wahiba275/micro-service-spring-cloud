package org.sid.billing_service;

import org.sid.billing_service.entities.Bill;
import org.sid.billing_service.entities.ProductItem;
import org.sid.billing_service.feign.CustomerRestClient;
import org.sid.billing_service.feign.ProductItemRestClient;
import org.sid.billing_service.model.Customer;
import org.sid.billing_service.model.Product;
import org.sid.billing_service.repositories.BillRepository;
import org.sid.billing_service.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository , ProductItemRepository productItemRepository , CustomerRestClient customerRestClient , ProductItemRestClient productItemRestClient){
      return args ->{
          Customer customer = customerRestClient.getCustomerById(1L);
          Bill bill1 = new Bill(null , new Date() , null , customer.getId() ,customer);
          billRepository.save(bill1);
          PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
          productPagedModel.forEach(p ->{
              ProductItem productItem = new ProductItem();
              productItem.setPrice(p.getPrice());
              productItem.setQuantity((double) new Random().nextInt(100));
              productItem.setProductID(p.getId());
              productItem.setBill(bill1);
              productItemRepository.save(productItem);
          });
      };
    }
}
