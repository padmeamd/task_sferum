package net.bookmarket.loaders;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import net.bookmarket.config.AppConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class LoggerLoader {

    private final AppConfig config;

    @Autowired
    public LoggerLoader(AppConfig config) {
        this.config = config;
    }

    @PostConstruct
    private void loadLogger() {
        if (config.getLogFilePath() != null) {

            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            try {
                JoranConfigurator jc = new JoranConfigurator();
                jc.setContext(context);
                context.reset();

                context.putProperty("LOG_NAME", config.getLogFilePath());

                jc.doConfigure(new ClassPathResource("spring-logback.xml").getInputStream());
            } catch (Exception ignored) {

            }
            StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        }
    }

}
