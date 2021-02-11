package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(Role role) {
        entityManager.remove(role);
    }
}
