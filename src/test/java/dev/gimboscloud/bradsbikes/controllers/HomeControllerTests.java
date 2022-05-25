package dev.gimboscloud.bradsbikes.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HomeControllerTests {
	
	static private HomeController homeController;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		homeController = new HomeController();
	}

	@Test
	public void call_to_serverHomePage_returns_home() {
		String response = homeController.serveHomePage();
		
		assertEquals("home", response);
	}

}
