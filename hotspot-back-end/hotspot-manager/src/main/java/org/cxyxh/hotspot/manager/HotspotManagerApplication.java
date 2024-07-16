package org.cxyxh.hotspot.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.cxyxh.hotspot.manager", "org.cxyxh.hotspot.common"})
public class HotspotManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotspotManagerApplication.class, args);
	}

}
