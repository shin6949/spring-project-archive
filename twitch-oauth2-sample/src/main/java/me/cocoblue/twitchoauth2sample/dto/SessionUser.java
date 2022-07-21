package me.cocoblue.twitchoauth2sample.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.cocoblue.twitchoauth2sample.user.User;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SessionUser implements Serializable {
    private Long internalId;
    private String name;
    private String picture;

    public SessionUser(User user) {
        this.internalId = user.getInternalId();
        this.name = user.getName();
        this.picture = user.getPicture();
    }
}
