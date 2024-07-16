package org.cxyxh.hotspot.common.entity.model;

import org.cxyxh.hotspot.common.constant.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 17:24
 * @describetion :
 */
public class RespBean extends HashMap<String, Object> {

	/**
	 * 状态码
	 */
	public static final String CODE_TAG = "code";

	/**
	 * 返回内容
	 */
	public static final String MSG_TAG = "msg";

	/**
	 * 数据对象
	 */
	public static final String DATA_TAG = "data";

	public static final String OPERATION_SUCCESSFUL = "操作成功";

	public static final String OPERATION_FAILED = "操作失败";

	public RespBean() {}

	/**
	 * 初始化一个新创建的 RespBean 对象
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 */
	public RespBean(int code, String msg) {
		super.put(CODE_TAG, code);
		super.put(MSG_TAG, msg);
	}

	/**
	 * 初始化一个新创建的 RespBean 对象
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 * @param data 数据对象
	 */
	public RespBean(int code, String msg, Object data) {
		super.put(CODE_TAG, code);
		super.put(MSG_TAG, msg);
		if (Objects.nonNull(data)) {
			super.put(DATA_TAG, data);
		}
	}

	/**
	 * 返回成功消息
	 *
	 * @return 成功消息
	 */
	public static RespBean success() {
		return RespBean.success(OPERATION_SUCCESSFUL);
	}

	/**
	 * 返回成功数据
	 *
	 * @return 成功消息
	 */
	public static RespBean success(Object data) {
		return RespBean.success(OPERATION_SUCCESSFUL, data);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static RespBean success(String msg) {
		return RespBean.success(msg, null);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 成功消息
	 */
	public static RespBean success(String msg, Object data) {
		return new RespBean(HttpStatus.SUCCESS, msg, data);
	}

	/**
	 * 返回错误消息
	 *
	 * @return
	 */
	public static RespBean error() {
		return RespBean.error(OPERATION_FAILED);
	}

	/**
	 * 返回错误消息
	 *
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static RespBean error(String msg) {
		return RespBean.error(msg, null);
	}

	/**
	 * 返回错误消息
	 *
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static RespBean error(String msg, Object data) {
		return new RespBean(HttpStatus.ERROR, msg, data);
	}

	/**
	 * 返回错误消息
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 * @return 警告消息
	 */
	public static RespBean error(int code, String msg) {
		return new RespBean(code, msg, null);
	}

	/**
	 * 方便链式调用
	 *
	 * @param key   键
	 * @param value 值
	 * @return 数据对象
	 */
	@Override
	public RespBean put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
