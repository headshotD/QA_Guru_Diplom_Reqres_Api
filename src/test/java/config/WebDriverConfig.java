package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:key.properties"
})

public interface WebDriverConfig extends Config {

    @Key("email")
    String email();

    @Key("password")
    String password();

    @Key("invalidEmail")
    String invalidEmail();
}
