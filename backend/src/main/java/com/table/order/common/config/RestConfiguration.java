package com.table.order.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.table.order.common.model.ReservationRequest;
import com.table.order.common.security.model.User;
import com.table.order.restaurateur.model.ActivatedRestaurant;
import com.table.order.restaurateur.model.Restaurant;

@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
        config.setBasePath("/api");
        
        config.exposeIdsFor(
    		User.class,
    		ActivatedRestaurant.class,
    		Restaurant.class,
    		ReservationRequest.class
        );
    }
}
