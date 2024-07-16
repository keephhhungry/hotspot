package org.cxyxh.hotspot.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.entity.model.LoginBody;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:11
 * @describetion :
 */
public interface UserService extends IService<LoginUser> {

	String login(String username, String password, String code, String uuid);
}
