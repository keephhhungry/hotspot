package org.cxyxh.hotspot.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.google.protobuf.ServiceException;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.entity.Role;
import org.cxyxh.hotspot.common.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:11
 * @describetion :
 */
public interface RoleService extends IService<Role> {

	List<Role> getRolesByUserId(Long userId);

}
