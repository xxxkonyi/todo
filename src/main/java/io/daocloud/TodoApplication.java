package io.daocloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

@SpringBootApplication
@Controller
public class TodoApplication {

  @Autowired
  StandardEnvironment environment;

  @RequestMapping("/")
  public void index(HttpServletResponse response) throws IOException {
    response.getWriter().println(MessageFormat.format("Server {0} {1} is live!",
      environment.getSystemEnvironment().getOrDefault("HOSTNAME", "windows"),
      environment.getSystemProperties().get("PID")));
  }

  public static void main(String[] args) {
    SpringApplication.run(TodoApplication.class, args);
  }

}
