package net.bookmarket;

import lombok.extern.slf4j.Slf4j;
import net.bookmarket.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@Slf4j
@SpringBootApplication
public class AppStartup {
    public static void main(String[] args) {

        if (args.length < 1) {
            log.info("Usage: java -jar application.jar data.json logfile.log");
            System.exit(1);
            return;
        }

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        ctx.register(AppConfig.class);
        ctx.refresh();

        SpringApplication.run(AppStartup.class, args);

    }

}
