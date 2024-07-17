package org.cxyxh.hotspot.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cxyxh.hotspot.common.entity.LoginInfo;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:11
 * @describetion :
 */
public interface LoginInfoService extends IService<LoginInfo> {

	int addLoginInfo(LoginInfo loginInfo);
}
