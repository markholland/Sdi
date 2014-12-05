package services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;

public class SensorsApp extends Application{
	
	private datamodel.Sampler sampler = null;

     @Override
        public Set<Class<?>> getClasses() {
            return new HashSet<Class<?>>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
                System.out.println("Getting classes from SensorsApp...");
                // Add resources.
                add(SensorResource.class);
                add(SensorsResource.class);
                // Add LoggingFilter.
                add(LoggingFilter.class);
                
                // Start additional active components
                if (sampler == null) {
                	sampler = new datamodel.Sampler();
                }
                if (!sampler.isAlive()) {
                	System.out.println("SensorsApp: Starting sampling thread.");
                	sampler.start();
                }
            }};
        }
}