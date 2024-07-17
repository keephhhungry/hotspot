package org.cxyxh.hotspot.common.config.security;

import org.cxyxh.hotspot.common.service.impl.UserDetailsServiceImpl;
import org.cxyxh.hotspot.common.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author ： cxyxh
 * @date : 2024/7/16 22:06
 * @describetion :
 */
@Configuration
public class SecurityConfig{

	/**
	 * 自定义用户认证逻辑
	 */
	@Autowired
	UserDetailsServiceImpl	userDetailsService;

	//@Autowired
	//CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
	//
	//@Autowired
	//CustomUrlDecisionManager customUrlDecisionManager;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encode = encoder.encode("123");
		System.out.println(encode);
	}

	//@Bean
	//WebSecurityCustomizer webSecurityCustomizer() {
		//return new WebSecurityCustomizer() {
		//	@Override
		//	public void customize(WebSecurity web) {
		//		web.ignoring().antMatchers("/v2/api-docs",
		//				"/swagger-resources",
		//				"/swagger-resources/**",
		//				"/configuration/ui",
		//				"/configuration/security",
		//				"/swagger-ui.html/**",
		//				"/webjars/**",//前面是swagger
		//				"/",
		//				"/login",
		//				"/verifyCode",
		//				"/js/**",
		//				"/css/**",
		//				"/images/**",
		//				"/tinymce/**",
		//				"/fonts/**",
		//				"/index.html",
		//				"/v3/api-docs/**",
		//				"/favicon.ico");
		//	}
		//};
	//}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				// 基于token，所以不需要session
				.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// 过滤请求 对于登录login 注册register 验证码captchaImage 允许匿名访问
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login", "/register", "/captchaImage").anonymous())
				//.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.GET,"/", "/*.html", "/**/*.html","/**/*.css","/**/*.js","/profile/**").permitAll())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-ui.html").anonymous())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-resources/**").anonymous())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/webjars/**").anonymous())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api-docs").anonymous())
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/druid/**").anonymous())
				// 除上面外的所有请求全部需要鉴权认证
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
		return httpSecurity.build();
	}



}
