package org.cxyxh.hotspot.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.annotation.Resource;
import org.cxyxh.hotspot.common.async.AsyncFactory;
import org.cxyxh.hotspot.common.async.AsyncManager;
import org.cxyxh.hotspot.common.constant.Constants;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.mapper.UserMapper;
import org.cxyxh.hotspot.common.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
	public String login(String username, String password, String code, String uuid) throws ServiceException {
		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
		authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, Constants.USER_PASSWORD_NOT_MATCH));
				//throw new UserPasswordNotMatchException();
				log.info("Bad credentials");
				throw new ServiceException(e.getMessage());
			} else {
				//	AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
				throw new ServiceException(e.getMessage());
			}
		}
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		//LoginUser loginUser = new LoginUser();

		return loginUser.toString();
	}

	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//	QueryWrapper<LoginUser> qw = new QueryWrapper<>();
	//	qw.eq("username", username);
	//	LoginUser loginUser = userMapper.selectOne(qw);
	//	if (Objects.isNull(loginUser)) {
	//		throw new UsernameNotFoundException("用户不存在");
	//	}
	//	return loginUser;
	//}


}
