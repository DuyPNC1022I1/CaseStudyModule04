package media.web.mediaweb.service;

import media.web.mediaweb.model.Account;
import media.web.mediaweb.model.Role;
import media.web.mediaweb.model.UserPrinciple;
import media.web.mediaweb.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private RoleService roleService;

    public Optional<Account> findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> userOptional = iAccountRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }
    public boolean existsByUsername(String username) {
        return iAccountRepository.existsByUsername(username);
    }
    public void save(Account user) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findById(1L));
        user.setRoles(roleSet);
        iAccountRepository.save(user);
    }

}
