package media.web.mediaweb.service;


import media.web.mediaweb.model.Role;
import media.web.mediaweb.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    public Role findByName(String name) {
        return iRoleRepository.findByName(name);
    }
    public Role findById(Long id) {
        return iRoleRepository.findById(id).orElse(null);
    }

}
