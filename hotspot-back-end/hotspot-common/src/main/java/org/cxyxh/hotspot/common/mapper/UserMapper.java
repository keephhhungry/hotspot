package org.cxyxh.hotspot.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cxyxh.hotspot.common.entity.LoginUser;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 17:52
 * @describetion :
 */
public interface UserMapper extends BaseMapper<LoginUser> {

	//LoginUser selectUserByUserName(String userName);
}
