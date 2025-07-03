package config;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class WebProvider {
    @Getter
    private static final WebConfig webConfig =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );
}
