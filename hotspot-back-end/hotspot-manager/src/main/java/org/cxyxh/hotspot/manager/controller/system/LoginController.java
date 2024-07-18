package org.cxyxh.hotspot.manager.controller.system;

import org.cxyxh.hotspot.common.entity.model.LoginBody;
import org.cxyxh.hotspot.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 16:57
 * @describetion :
 */
@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(@RequestBody LoginBody loginBody) throws Exception {
		String login = userService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
				loginBody.getUuid());
		return login;
	}
}
