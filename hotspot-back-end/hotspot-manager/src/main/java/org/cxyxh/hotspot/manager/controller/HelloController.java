package org.cxyxh.hotspot.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:18
 * @describetion :
 */
@RestController
@RequestMapping ("/hello")
public class HelloController {


	@GetMapping("/")
	public String sayHello() {
		return "Hello World!";
	}
}
