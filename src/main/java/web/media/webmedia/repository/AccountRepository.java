package web.media.webmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.media.webmedia.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
