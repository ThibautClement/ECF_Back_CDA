package fr.thcl.formation.ecf.backend.localib.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfiguration {

    @Bean
    public LocationService locationService(LocationRepository locationRepository){
        return new LocationServiceImpl(locationRepository);
    }
}
