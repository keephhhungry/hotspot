//package org.cxyxh.hotspot.common.config.security;
//
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * @ProjectName: blog
// * @Package: org.cxyxh.blog.config
// * @ClassName: CustomUrlDecisionManager
// * @Author: Administrator
// * @Description: ${description}
// * @Date: 2020/5/28 11:05
// * @Version: 1.0
// * 查看当前登录用户的角色
// */
//
//@Component
//public class CustomUrlDecisionManager implements AccessDecisionManager {
//
//    /**
//     * @param authentication   你拥有的角色
//     * @param object
//     * @param configAttributes 路径需要的角色
//     * @throws AccessDeniedException
//     * @throws InsufficientAuthenticationException
//     */
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        for (ConfigAttribute configAttribute : configAttributes) {
//            String needRole = configAttribute.getAttribute();
//            //路径全部没匹配上，默认为登录就可以访问的路径，只需要判断登录情况
//            if ("ROLE_LOGIN".equals(needRole)) {
//                if (authentication instanceof AnonymousAuthenticationToken) {
//                    throw new AccessDeniedException("尚未登录，请登录!");
//                } else {
//                    return;
//                }
//            }
//            //拥有的角色和需要的角色做对比
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                if (authority.getAuthority().equals(needRole)) {
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("权限不足，请联系管理员!");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//}