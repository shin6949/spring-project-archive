package me.cocoblue.twitchoauth2sample.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long internalId;

    @Column(nullable = false)
    private Long twitchId;

    @Column(nullable = false)
    private String name;

    private String picture;
    private final String role = "ROLE_USER";

    public User(String name, String twitchId, String picture) {
        this.name = name;
        this.twitchId = Long.parseLong(twitchId);
        this.picture = picture;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
}