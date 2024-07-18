package org.cxyxh.hotspot.common.async;

import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.cxyxh.hotspot.common.utils.spring.SpringUtils;
import org.cxyxh.hotspot.common.utils.thread.Threads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 */
//@Component
public class AsyncManager {
	/**
	 * 操作延迟10毫秒
	 */
	private final int OPERATE_DELAY_TIME = 10;

	/**
	 * 异步操作任务调度线程池
	 */
	//@Resource(name = "scheduledExecutorService")
	//private ScheduledExecutorService executor;
	private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

	/**
	 * 单例模式
	 */
	private AsyncManager() {
	}

	private static AsyncManager asyncManager = new AsyncManager();

	// 下面的方法有更好的命名方式吗?

	public static AsyncManager getInstance() {
		return asyncManager;
	}

	/**
	 * 执行任务
	 *
	 * @param task 任务
	 */
	public void execute(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}

	/**
	 * 停止任务线程池
	 */
	public void shutdown() {
		Threads.shutdownAndAwaitTermination(executor);
	}
}
