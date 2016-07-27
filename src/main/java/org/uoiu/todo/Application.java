package org.uoiu.todo;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.env.StandardEnvironment;

import ratpack.handling.RequestLogger;
import ratpack.server.RatpackServer;

//@SpringBootApplication
//@RestController
public class Application {

//  @Autowired
//  StandardEnvironment environment;

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

    RatpackServer.start(server -> server
//      .registry(Spring.spring(Application.class))
        .handlers(chain -> chain
          .all(RequestLogger.ncsa())
          .get(ctx -> ctx.render("Hello World!"))
          .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
        )
    );
  }

}
