package com.xrq.springbootwebflux.config;

import com.xrq.springbootwebflux.domain.User;
import com.xrq.springbootwebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Configuration
public class RouteFunctionConfiguration {
    /**
     * Servlet
     * 请求接口  ServletRequest或者 HttpServletRequest
     * 响应借口  ServletResponse或者 HttpServletResponse
     * <p>
     * Spring 5.0 重新定义了 服务请求和 响应接口：
     * <p>
     * 请求借口 ServerRequest
     * 相应借口 ServerResponse
     * 即可支持servelt 规范 也可以支持自定义 碧土  netty
     * <p>
     * Flux 是0-n个对象集合
     * Mono是 0-1个对象集合
     * 都是异步处理是（非阻塞）
     * <p>
     * FLUX或者mono都是publisher
     */

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> presonFinalAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/person/findAll"),
                request -> {
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }

}
