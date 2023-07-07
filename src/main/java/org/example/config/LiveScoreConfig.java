package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:./src/main/resources/liveScore.properties")
public interface LiveScoreConfig extends Config {
    @Config.Key("baseUrl")
    String baseUrl();
}
