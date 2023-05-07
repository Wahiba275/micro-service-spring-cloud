package org.sid.inventoryservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start( ProductRepository productRepository , RepositoryRestConfiguration repositoryRestConfiguration){
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		return  args ->{
			productRepository.save(new Product(null , "laptop" , 12000.0 , 10.2));
			productRepository.save(new Product(null , "SAMSONG Phone" , 10000.0 , 8.0));
			productRepository.save(new Product(null , "Camera Canon" , 1500.23 , 4.0));
		    productRepository.findAll().forEach(p->{
				System.out.println(p.toString());
			});
		};
	}
}

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String name;
	private Double price ;
	private Double quantity;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
@RepositoryRestResource
interface  ProductRepository extends JpaRepository<Product , Long>{

}
