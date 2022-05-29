package net.bookmarket.loaders;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.bookmarket.config.AppConfig;
import net.bookmarket.entities.input.MarketData;
import net.bookmarket.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;

@Slf4j
@Component
public class DataLoader {

    private final AppConfig config;
    private final MarketRepository repository;

    @Autowired
    public DataLoader(AppConfig config, MarketRepository repository) {
        this.config = config;
        this.repository = repository;
    }

    @PostConstruct
    private void loadData() {
        File dataFile = new File(config.getDataFilePath());

        Gson gson = new Gson();
        MarketData data;
        try {
            data = gson.fromJson(new FileReader(dataFile), MarketData.class);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Exception loading data file", ex);
        }

        log.info("Data file loaded successfully");
        log.debug("Loaded data = " + data);

        repository.fillWithData(data);

    }

}
