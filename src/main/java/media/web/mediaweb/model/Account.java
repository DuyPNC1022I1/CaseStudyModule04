package media.web.mediaweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String username; //Cần validate unique
    @Size(min = 6, max = 8)
    private String password; //Cần validate 6-8 ký tự
    @Size(min = 10, max = 12)
    private String phone; //Cần validate 12 ky tu
    @Email
    private String email; //Cần validate @
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

//    public List<GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
}
