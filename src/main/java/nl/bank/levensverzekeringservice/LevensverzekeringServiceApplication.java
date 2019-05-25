package nl.bank.levensverzekeringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class LevensverzekeringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevensverzekeringServiceApplication.class, args);
	}

	@Configuration
	public class RequestLoggingFilterConfig {

		@Bean
		public CommonsRequestLoggingFilter logFilter() {

			CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
			filter.setIncludeQueryString(true);
			filter.setIncludePayload(true);
			filter.setMaxPayloadLength(10000);
			filter.setIncludeHeaders(false);
			return filter;
		}
	}

}
