package api.config;

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

    @Key("x-api-key")
    String key();

    @Key("data.last_name")
    String dataLastName();

    @Key("data.first_name")
    String dataFirstName();

    @Key("support.text")
    String supportText();

}