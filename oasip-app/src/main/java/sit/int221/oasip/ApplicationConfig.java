package sit.int221.oasip;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.int221.oasip.utils.ListMapper;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); }

    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }
}