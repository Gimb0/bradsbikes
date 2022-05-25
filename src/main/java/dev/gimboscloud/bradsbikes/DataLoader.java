package dev.gimboscloud.bradsbikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dev.gimboscloud.bradsbikes.models.Manufacturer;
import dev.gimboscloud.bradsbikes.models.Motorbike;
import dev.gimboscloud.bradsbikes.models.MotorbikeAccessory;
import dev.gimboscloud.bradsbikes.services.AccessoryService;
import dev.gimboscloud.bradsbikes.services.ManufacturerService;
import dev.gimboscloud.bradsbikes.services.MotorbikeService;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private MotorbikeService motorbikeService;
	private AccessoryService accessoryService;
	private ManufacturerService manufacturerService;
	
	@Autowired
	DataLoader(MotorbikeService motorbikeService, AccessoryService accessoryService, ManufacturerService manufacturerService) {
		this.motorbikeService = motorbikeService;
		this.accessoryService = accessoryService;
		this.manufacturerService = manufacturerService;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Manufacturer yamaha = new Manufacturer("Yamaha");
		Manufacturer honda = new Manufacturer("Honda");
		Manufacturer kawasaki = new Manufacturer("Kawasaki");
		Manufacturer ducati = new Manufacturer("Ducati");
		manufacturerService.save(yamaha);
		manufacturerService.save(honda);
		manufacturerService.save(kawasaki);
		manufacturerService.save(ducati);
		
		
		Motorbike bike1 = new Motorbike("Yamaha R1", "2020 Yamaha YZF-R1, 1000cc Superbike", 10, 20000.00, 190.00);
		bike1.setManufacturer(yamaha);
		motorbikeService.save(bike1);
		
		Motorbike bike2 = new Motorbike("Honda CBR1000RR", "2020 Honda Fireblade CBR1000RR-R, 1000cc Superbike", 5, 35000.00, 210.00);
		bike2.setManufacturer(honda);
		motorbikeService.save(bike2);
		
		Motorbike bike3 = new Motorbike("Kawazaki ZX10R", "2020 Kawazaki ZX10R, 1000cc Superbike", 15, 17500.00, 185.00);
		bike3.setManufacturer(kawasaki);
		motorbikeService.save(bike3);
		
		Motorbike bike4 = new Motorbike("Kawazaki H2R", "2020 Kawazaki H2R, 1000cc Supercharged Superbike", 5, 55000.00, 290.00);
		bike4.setManufacturer(kawasaki);
		motorbikeService.save(bike4);
		
		Motorbike bike5 = new Motorbike("Ducati V4R", "2020 Ducati V4R, 998cc V4 Superbike", 5, 38000.00, 230.00);
		bike5.setManufacturer(ducati);
		motorbikeService.save(bike5);
		
		Motorbike bike6 = new Motorbike("Yamaha R6", "2020 Yamaha YZF-R6, 599cc Supersport", 10, 14000.00, 125.00);
		bike6.setManufacturer(yamaha);
		motorbikeService.save(bike6);
		
		Motorbike bike7 = new Motorbike("Honda CBR600RR", "2020 Honda CBR600RR, 599cc Supersport", 12, 13000.00, 120.00);
		bike7.setManufacturer(honda);
		motorbikeService.save(bike7);

		Motorbike bike8 = new Motorbike("Kawasaki ZX6R", "2020 Kawasaki ZX6R, 636cc Supersport", 10, 14000.00, 130.00);
		bike8.setManufacturer(kawasaki);
		motorbikeService.save(bike8);
		
		MotorbikeAccessory accessory1 = new MotorbikeAccessory("Akroprovic Exhaust", "Akroprovic Full-System Exhaust for any brand Superbike", 15, 1500.00);
		accessory1.addMotorbike(bike1);
		accessory1.addMotorbike(bike2);
		accessory1.addMotorbike(bike3);
		accessory1.addMotorbike(bike4);
		accessory1.addMotorbike(bike5);
		accessory1.addMotorbike(bike6);
		accessory1.addMotorbike(bike7);
		accessory1.addMotorbike(bike8);
		accessoryService.save(accessory1);
		
		MotorbikeAccessory accessory2 = new MotorbikeAccessory("Evotech Levers", "Evotech Performance Folding Levers", 20, 199.99);
		accessory2.addMotorbike(bike1);
		accessory2.addMotorbike(bike2);
		accessory2.addMotorbike(bike3);
		accessory2.addMotorbike(bike4);
		accessory2.addMotorbike(bike5);
		accessory2.addMotorbike(bike6);
		accessory2.addMotorbike(bike7);
		accessory2.addMotorbike(bike8);
		accessoryService.save(accessory2);
		
		MotorbikeAccessory accessory3 = new MotorbikeAccessory("Ducati Rear Stand", "Ducati Single Sided Swingarm Paddock Stand", 5, 219.99);
		accessory3.addMotorbike(bike5);
		accessoryService.save(accessory3);
		
		bike1.addAccessory(accessory1);
		bike2.addAccessory(accessory1);
		bike3.addAccessory(accessory1);
		bike4.addAccessory(accessory1);
		bike5.addAccessory(accessory1);
		bike6.addAccessory(accessory1);
		bike7.addAccessory(accessory1);
		bike8.addAccessory(accessory1);
		
		bike1.addAccessory(accessory2);
		bike2.addAccessory(accessory2);
		bike3.addAccessory(accessory2);
		bike4.addAccessory(accessory2);
		bike5.addAccessory(accessory2);
		bike6.addAccessory(accessory2);
		bike7.addAccessory(accessory2);
		bike8.addAccessory(accessory2);
		
		bike5.addAccessory(accessory3);
		
		motorbikeService.save(bike1);
		motorbikeService.save(bike2);
		motorbikeService.save(bike3);
		motorbikeService.save(bike4);
		motorbikeService.save(bike5);
		motorbikeService.save(bike6);
		motorbikeService.save(bike7);
		motorbikeService.save(bike8);
	}
}
