package org.sid.billing_service.web;

import org.sid.billing_service.entities.Bill;
import org.sid.billing_service.feign.CustomerRestClient;
import org.sid.billing_service.feign.ProductItemRestClient;
import org.sid.billing_service.model.Customer;
import org.sid.billing_service.model.Product;
import org.sid.billing_service.repositories.BillRepository;
import org.sid.billing_service.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class billingRestController {
    public billingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    private BillRepository billRepository ;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient ;

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
         Bill bill = billRepository.findById(id).get();
         Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
         bill.setCustomer(customer);
         bill.getProductItems().forEach(pi ->{
             Product product = productItemRestClient.getProductById(pi.getProductID());
             //pi.setProduct(product);
             pi.setProductName(product.getName());
         });
         return  bill ;
    }
}
