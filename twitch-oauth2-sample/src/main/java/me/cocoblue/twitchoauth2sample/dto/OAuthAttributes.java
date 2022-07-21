package me.cocoblue.twitchoauth2sample.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.cocoblue.twitchoauth2sample.user.User;

import java.util.Map;

@Data
@NoArgsConstructor
@Log4j2
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String sub;
    private String picture;

    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String sub, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.sub = sub;
        this.picture = picture;
    }

    public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
        log.info("attributes: " + attributes.toString());
        return ofTwitch(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofTwitch(String userNameAttributeName, Map<String, Object> attributes) {
        return new OAuthAttributes(attributes,
                userNameAttributeName,
                (String) attributes.get("preferred_username"),
                (String) attributes.get("sub"),
                (String) attributes.get("picture"));
    }

    public User toEntity() {
        return new User(name, sub, picture);
    }
}