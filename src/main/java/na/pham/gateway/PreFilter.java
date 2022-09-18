package na.pham.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class PreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        System.out.println("Authorization = " + request.getHeaders().getFirst("Authorization"));
        System.out.println("Handle authentication/authorization");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("post filter here");
        }));
    }
}
