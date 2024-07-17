package org.cxyxh.hotspot.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.annotation.Resource;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.entity.Role;
import org.cxyxh.hotspot.common.mapper.RoleMapper;
import org.cxyxh.hotspot.common.mapper.UserMapper;
import org.cxyxh.hotspot.common.service.RoleService;
import org.cxyxh.hotspot.common.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:12
 * @describetion :
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<Role> getRolesByUserId(Long userId) {
		return userId == null ? new ArrayList<>() : roleMapper.getRolesByUserId(userId);
	}

}
