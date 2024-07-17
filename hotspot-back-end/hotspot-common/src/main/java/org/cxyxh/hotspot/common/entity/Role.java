package org.cxyxh.hotspot.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 16:52
 * @describetion :
 */
@Data
@TableName("role")
public class Role {

	//@TableId(value = "id", type = IdType.AUTO)
	private Long roleId;

	//@TableField("name")
	private String name;

	//@TableField("name_zh")
	private String nameZh;
}
