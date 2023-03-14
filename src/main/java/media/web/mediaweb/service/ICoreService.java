package media.web.mediaweb.service;

import java.util.List;

public interface ICoreService<E> {
    List<E> findAll();

    E findById(Long id);

    void save();

    void deleteById(Long id);
}
