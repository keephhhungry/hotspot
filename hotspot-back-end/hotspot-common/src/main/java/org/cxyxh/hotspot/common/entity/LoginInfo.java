package org.cxyxh.hotspot.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ： cxyxh
 * @date : 2024/7/17 17:33
 * @describetion :
 */
@Data
@TableName("login_info")
public class LoginInfo {

	@TableId("info_id")
	private Long infoId;

	@TableField("username")
	private String username;

	@TableField("ipaddr")
	private String ipaddr;

	@TableField("login_location")
	private String loginLocation;

	@TableField("browser")
	private String browser;

	@TableField("os")
	private String os;

	@TableField("status")
	private String status;

	@TableField("msg")
	private String msg;

	@TableField("login_time")
	private LocalDateTime loginTime;


}
