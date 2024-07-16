package org.cxyxh.hotspot.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 15:18
 * @describetion :
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	//@InitBinder
	//public void initBinder(WebDataBinder binder) {
	//	// Date 类型转换
	//	binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
	//		@Override
	//		public void setAsText(String text) {
	//			setValue(DateUtils.parseDate(text));
	//		}
	//	});
	//}
}
