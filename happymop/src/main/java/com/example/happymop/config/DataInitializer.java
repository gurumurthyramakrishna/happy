package com.example.happymop.config;

import com.example.happymop.model.ServiceItem;
import com.example.happymop.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final ServiceRepository serviceRepository;
    
    public DataInitializer(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize services if none exist
        if (serviceRepository.count() == 0) {
            initializeServices();
        }
    }
    
    private void initializeServices() {
        ServiceItem[] services = {
            createService("home_cleaning", "Home Cleaning", 1200, "Complete home cleaning including dusting, mopping, and organizing"),
            createService("deep_cleaning", "Deep Cleaning", 2500, "Thorough deep cleaning including kitchen, bathrooms, and all rooms"),
            createService("office_cleaning", "Office Cleaning", 1800, "Professional office space cleaning and sanitization"),
            createService("carpet_cleaning", "Carpet Cleaning", 800, "Professional carpet and upholstery cleaning"),
            createService("window_cleaning", "Window Cleaning", 600, "Interior and exterior window cleaning"),
            createService("kitchen_clean", "Kitchen Deep Clean", 1500, "Complete kitchen cleaning including appliances and cabinets"),
            createService("bathroom_clean", "Bathroom Deep Clean", 1000, "Thorough bathroom cleaning and sanitization"),
            createService("construction_cleanup", "Post-Construction Cleanup", 3500, "Complete cleanup after construction or renovation work"),
            createService("move_cleaning", "Move-in/Move-out Cleaning", 2200, "Complete cleaning for moving in or out of property"),
            createService("laundry", "Laundry Service", 500, "Washing, drying, and folding of clothes")
        };
        
        for (ServiceItem service : services) {
            serviceRepository.save(service);
        }
        
        System.out.println("Initialized " + services.length + " services in the database");
    }
    
    private ServiceItem createService(String code, String title, int price, String description) {
        ServiceItem service = new ServiceItem();
        service.setCode(code);
        service.setTitle(title);
        service.setPrice(price);
        service.setDescription(description);
        return service;
    }
}
