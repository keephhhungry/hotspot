package org.cxyxh.hotspot.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cxyxh.hotspot.common.entity.LoginInfo;
import org.cxyxh.hotspot.common.mapper.LoginInfoMapper;
import org.cxyxh.hotspot.common.service.LoginInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:12
 * @describetion :
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LoginInfoMapper loginInfoMapper;

	@Override
	public int addLoginInfo(LoginInfo loginInfo) {
		return loginInfoMapper.insert(loginInfo);
	}
}
