package org.cxyxh.hotspot.common.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 17:40
 * @describetion :
 */
@Data
public class BaseEntity implements Serializable {

	/**
	 * 创建者
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
	private Date createTime;

	/**
	 * 更新者
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
	private Date updateTime;

}
