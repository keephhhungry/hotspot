package org.cxyxh.hotspot.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.cxyxh.hotspot.common.async.AsyncFactory;
import org.cxyxh.hotspot.common.async.AsyncManager;
import org.cxyxh.hotspot.common.constant.Constants;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.exception.ServiceException;
import org.cxyxh.hotspot.common.exception.user.UserPasswordNotMatchException;
import org.cxyxh.hotspot.common.mapper.UserMapper;
import org.cxyxh.hotspot.common.service.UserService;
import org.cxyxh.hotspot.common.utils.ServletUtils;
import org.cxyxh.hotspot.common.utils.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:12
 * @describetion :
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, LoginUser> implements UserService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	UserMapper userMapper;

	@Override
	public String login(String username, String password, String code, String uuid) {
		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				AsyncManager.getInstance().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, Constants.USER_PASSWORD_NOT_MATCH));
				throw new UserPasswordNotMatchException();
			} else {
				AsyncManager.getInstance().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, e.getMessage()));
				throw new ServiceException(e.getMessage());
			}
		}
		AsyncManager.getInstance().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_SUCCESS, Constants.USER_LOGIN_SUCCESS));
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		recordLoginInfo(loginUser.getUserId());

		return loginUser.toString();
	}

	/**
	 * 记录登录信息
	 *
	 * @param userId 用户ID
	 */
	public void recordLoginInfo(Long userId) {
		LoginUser loginUser = userMapper.selectById(userId);
		loginUser.setUserId(userId);
		//loginUser.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
		loginUser.setLoginDate(LocalDateTime.now());
		userMapper.updateById(loginUser);
	}


}
