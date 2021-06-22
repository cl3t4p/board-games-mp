package  com.boardgame.mp.server.Config;




import com.boardgame.mp.server.Repository.GameRepo;
import com.boardgame.mp.server.Repository.SessionRepo;
import com.boardgame.mp.server.controller.WebSocketGameController;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;



@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfiguration implements WebSocketConfigurer {


    SessionRepo sessionRepo;
    GameRepo gameRepo;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/games/{token}").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketGameController(sessionRepo,gameRepo);
    }



}