package org.cxyxh.hotspot.common.entity.model;

import lombok.Data;
import org.cxyxh.hotspot.common.entity.LoginUser;

/**
 * @author ： cxyxh
 * @date : 2024/7/18 17:14
 * @describetion :
 */
@Data
public class LoginUserDto extends LoginUser {

	/**
	 * 用户唯一标识
	 */
	private String token;

	/**
	 * 登录时间
	 */
	private Long loginTime;

	/**
	 * 过期时间
	 */
	private Long expireTime;

	/**
	 * 登录IP地址
	 */
	private String ipaddr;

	/**
	 * 登录地点
	 */
	private String loginLocation;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 操作系统
	 */
	private String os;


}
