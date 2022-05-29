package net.bookmarket.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@Getter
public class AppConfig {

    private String dataFilePath;
    private String logFilePath;

    @Autowired
    public void AppConfig(Environment environment) {
        String[] args = Objects.requireNonNull(environment.getProperty("nonOptionArgs", String[].class));

        this.dataFilePath = args[0];
        if (args.length > 1) {
            this.logFilePath = args[1];
        }
    }

}
