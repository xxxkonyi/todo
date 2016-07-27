package org.uoiu.todo;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.env.StandardEnvironment;

import ratpack.error.ServerErrorHandler;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import ratpack.handling.RequestLogger;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;
import rx.Observable;

import static ratpack.rx.RxRatpack.observe;

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
    RxRatpack.initialize(); // must be called once for the life of the JVM

    RatpackServer.start(server -> server
//      .registry(Spring.spring(Application.class))
        .handlers(chain -> chain
          .all(RequestLogger.ncsa())
          .register(registry ->
            registry.add(ServerErrorHandler.class, (context, throwable) ->
              context.render("caught by error handler: " + throwable.getMessage())
            )
          )
          .get(ctx -> {
            Promise<String> promise = Blocking.get(() -> "hello world");
            observe(promise).map(String::toUpperCase).subscribe(ctx::render);
          })
          .get("error", ctx -> Observable.<String>error(new Exception("!")).subscribe((s) -> {
          }))
          .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
        )
    );
  }

}
