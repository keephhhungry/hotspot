package org.cxyxh.hotspot.common.async;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.cxyxh.hotspot.common.constant.Constants;
import org.cxyxh.hotspot.common.entity.LoginInfo;
import org.cxyxh.hotspot.common.service.LoginInfoService;
import org.cxyxh.hotspot.common.utils.LogUtils;
import org.cxyxh.hotspot.common.utils.ServletUtils;
import org.cxyxh.hotspot.common.utils.ip.AddressUtils;
import org.cxyxh.hotspot.common.utils.ip.IpUtils;
import org.cxyxh.hotspot.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author tienchin
 */
@Component
public class AsyncFactory {

	private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

	private static LoginInfoService loginInfoService = SpringUtils.getBean(LoginInfoService.class);

	/**
	 * 记录登录信息
	 *
	 * @param username 用户名
	 * @param status   状态
	 * @param message  消息
	 * @param args     列表
	 * @return 任务task
	 */
	public static TimerTask recordLoginInfo(final String username, final String status, final String message, final Object... args) {

		final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());

		return new TimerTask() {
			@Override
			public void run() {
				String address = AddressUtils.getRealAddressByIP(ip);
				StringBuilder s = new StringBuilder();
				s.append(LogUtils.getBlock(ip));
				s.append(address);
				s.append(LogUtils.getBlock(username));
				s.append(LogUtils.getBlock(status));
				s.append(LogUtils.getBlock(message));
				// 打印信息到日志
				sys_user_logger.info(s.toString(), args);

				// 封装对象
				LoginInfo loginInfo = new LoginInfo();

				loginInfo.setUsername(username);
				loginInfo.setIpaddr(ip);
				loginInfo.setLoginLocation(address);
				// 获取客户端浏览器
				loginInfo.setBrowser(userAgent.getBrowser().getName());
				// 获取客户端操作系统
				loginInfo.setOs(userAgent.getOperatingSystem().getName());
				loginInfo.setMsg(message);
				// 日志状态
				if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
					loginInfo.setStatus(Constants.SUCCESS);
				} else if (Constants.LOGIN_FAIL.equals(status)) {
					loginInfo.setStatus(Constants.FAIL);
				}
				loginInfo.setLoginTime(LocalDateTime.now());
				// 插入数据
				loginInfoService.addLoginInfo(loginInfo);
			}
		};
	}

	/**
	 * 操作日志记录
	 *
	 * @param operLog 操作日志信息
	 * @return 任务task
	 */
	//public static TimerTask recordOper(final SysOperLog operLog) {
	//	return new TimerTask() {
	//		@Override
	//		public void run() {
	//			// 远程查询操作地点
	//			operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
	//			SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
	//		}
	//	};
	//}
}
