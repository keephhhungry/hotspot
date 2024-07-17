package org.cxyxh.hotspot.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.cxyxh.hotspot.common.entity.LoginUser;
import org.cxyxh.hotspot.common.entity.Role;

import java.util.List;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 17:52
 * @describetion :
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

	List<Role> getRolesByUserId(Long userId);
}
