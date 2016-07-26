package org.uoiu.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  StandardEnvironment environment;

  @RequestMapping("/")
  public String index(HttpServletResponse response) {
    return MessageFormat.format("Server {0} {1} is live!",
      environment.getSystemEnvironment().getOrDefault("HOSTNAME", "windows"),
      environment.getSystemProperties().get("PID"));
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
