package web.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            entityManager.remove(user.get());
        } else {
            System.out.println("Пользователь с Id" + id + " не найден");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User c").getResultList();
    }

    @Override
    public Optional<User> findUserById(int id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findUserByName(String name) {
      return entityManager.createQuery("select c from User c where c.name = :name")
                .setParameter("name", name).getResultList()
                .stream().findAny();
    }

}
