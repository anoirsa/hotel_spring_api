package com.hotelmangementapi.demo.security.jwt;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity(
        name = "Token"
)
@Table(
        name = "tokens",
        uniqueConstraints = @UniqueConstraint(name = "token_unique_constraints",columnNames = {"refresh_token_id","token"})

)
@Getter
@Setter
@NoArgsConstructor
public class JwtToken {
    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "token_sequence"
    )
    @Id
    public Long id;
    @Column(
            name = "refresh_token_id"
    )
    public String refreshTokenId;
    @Column(
            length = 2000
    )
    public String token;
    public String username;
    @Column(
            name = "is_used"
    )
    public String isUsed;

    public JwtToken(String refreshTokenId, String token, String username, String isUsed) {
        this.refreshTokenId = refreshTokenId;
        this.token = token;
        this.username = username;
        this.isUsed = isUsed;
    }
}
