package rlatnfla.securitytest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlatnfla.securitytest.dto.SignupRequest;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private Member(SignupRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
    }

    public static Member of(SignupRequest request) {
        return new Member(request);
    }
}
