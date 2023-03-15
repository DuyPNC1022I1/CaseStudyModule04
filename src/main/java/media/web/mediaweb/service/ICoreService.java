package media.web.mediaweb.service;

import java.util.List;

public interface ICoreService<E> {
    List<E> findAll();

    E findById(Long id);

    void save(E e);

    void deleteById(Long id);
}
