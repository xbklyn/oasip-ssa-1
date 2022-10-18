package sit.int221.oasip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import sit.int221.oasip.properties.FileProperties;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties({
		FileProperties.class
})
public class OasipApplication {

	public static void main(String[] args) {
		SpringApplication.run(OasipApplication.class, args);
	}

}
