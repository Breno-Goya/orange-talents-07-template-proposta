package br.com.zupacademy.msPropostas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(enableDefaultTransactions = false)
public class MsPropostasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPropostasApplication.class, args);
	}

}
