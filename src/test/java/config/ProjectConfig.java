package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${deviceFarm}.properties",
        "classpath:config/local.properties",
        "classpath:config/browserstack.properties",
        "classpath:config/selenoid.properties",
        "classpath:config/emulation.properties"
})
public interface ProjectConfig extends Config {

    @Config.Key("timeout")
    @Config.DefaultValue("8000")
    long getTimeout();

    @Config.Key("startMaximized")
    @Config.DefaultValue("false")
    boolean getStartMaximized();

    @Config.Key("enableVNC")
    @Config.DefaultValue("true")
    Boolean getVNC();

    @Config.Key("enableVideo")
    @Config.DefaultValue("true")
    Boolean getVideo();
}
