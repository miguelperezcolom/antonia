package io.mateu.travel.backoffice.shared.infra;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "io.mateu.travel")
@EnableJpaRepositories(basePackages = "io.mateu.travel")
public class SharedJPAConfig {
}
