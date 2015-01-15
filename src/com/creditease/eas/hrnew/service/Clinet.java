package com.creditease.eas.hrnew.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//import com.creditease.core.ws.client.UserService;
import com.creditease.smp.manager.EASWebservice;
import com.creditease.smp.manager.dto.EASEmployeeDTO;

public class Clinet {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IHelloService helloService = (IHelloService) context.getBean("client1");
//		String response = helloService.sayHello("Peter");
//		System.out.println(response);
//		System.out.println("------------------------------");
//		
//		ListTestService listTestService = (ListTestService) context.getBean("client2");
//		Map<String, Pet> map = listTestService.getAllPet();
//		
//		for (Map.Entry<String,Pet> entry : map.entrySet()) {
//			System.out.println("name===" + entry.getKey() + "pet ====" + entry.getValue().toString());
//		}
//		System.out.println("------------------------------");
//		User user = new User();
//		user.setId("1");
//		List<Pet> petList = listTestService.getPetByName(user);
//		for (Pet pet : petList) {
//			System.out.println(pet.toString());
//		}
		EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		
		Integer temp = easWebservice.updateEASEmployees(new ArrayList<EASEmployeeDTO>());
		System.out.println("___"+temp);
		
//		UserService helloService = (UserService) context.getBean("client4");
//		HashMap<String,String> map = helloService.test();
//		for (Entry<String, String> entry: map.entrySet()) {
//			System.out.println("key===" + entry.getKey() +"--- value = " +entry.getValue());
//		}
		
//		HashMap<String,String> map = helloService.getCurrentGroupId("1");
//		for (Entry<String, String> entry: map.entrySet()) {
//			System.out.println("key===" + entry.getKey() +"--- value = " +entry.getValue());
//		}
	}
}
