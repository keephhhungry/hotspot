package org.cxyxh.hotspot.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.entity.Role;
import org.cxyxh.hotspot.common.enums.UserStatus;
import org.cxyxh.hotspot.common.exception.ServiceException;
import org.cxyxh.hotspot.common.mapper.UserMapper;
import org.cxyxh.hotspot.common.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ： cxyxh
 * @date : 2024/7/17 11:48
 * @describetion :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RoleService roleService;

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username){
		QueryWrapper<LoginUser> qw = new QueryWrapper<>();
		qw.eq("username", username);
		LoginUser loginUser = userMapper.selectOne(qw);
		if (Objects.isNull(loginUser)) {
			log.info("登录用户：{} 不存在.", username);
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		}else if (UserStatus.DELETED.getCode().equals(loginUser.getDelFlag())) {
			log.info("登录用户：{} 已被删除.", username);
			throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
		} else if (UserStatus.DISABLE.getCode().equals(loginUser.getStatus())) {
			log.info("登录用户：{} 已被停用.", username);
			throw new ServiceException("对不起，您的账号：" + username + " 已停用");
		}
		loginUser.setRoles(roleService.getRolesByUserId(loginUser.getUserId()));
		loginUser.setPassword("");
		return loginUser;
	}
}
