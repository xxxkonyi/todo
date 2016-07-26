package org.uoiu.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.StandardEnvironment;
import ratpack.server.RatpackServer;

@SpringBootApplication
//@RestController
public class Application {

  @Autowired
  StandardEnvironment environment;

//  @RequestMapping("/")
//  public String index(HttpServletResponse response) {
//    return MessageFormat.format("Server {0} {1} is live!",
//      environment.getSystemEnvironment().getOrDefault("HOSTNAME", "windows"),
//      environment.getSystemProperties().get("PID"));
//  }

//  @Bean
//  public Handler handler() {
//    return context -> context.render("Hello World");
//  }

  public static void main(String[] args) throws Exception {
//    SpringApplication.run(Application.class, args);

    RatpackServer.start(serverSpec -> serverSpec
//      .registry(Spring.spring(Application.class))
        .handlers(chain -> chain
          .get(ctx -> ctx.getResponse().send("Hello, World!"))
        )
    );
  }

}
