package com.manpower.backendProject.repositories;

import com.manpower.backendProject.mocks.CustomMocks;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@Transactional(rollbackOn = Throwable.class)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

//    @AfterEach
//    public void clearAll() {
//        repository.deleteAll();
//        entityManager.flush();
//    }

    @Test
    public void testPersist_and_FindUserByEmail() {
        User user = CustomMocks.mockUser();
        this.entityManager.persist(user);
        User userFromDB = this.repository.findByEmail(user.getEmail()).orElseThrow();
        assertThat(userFromDB.getFirstname()).isEqualTo(user.getFirstname());
        assertThat(userFromDB.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    public void givenSameEmail_shouldThrow() {
        User user = CustomMocks.mockUser();
        this.entityManager.persist(user);

        Assertions.assertThrows(PersistenceException.class, () -> {
                    User sameUser = CustomMocks.mockUser();
                    this.entityManager.persist(sameUser);
                    entityManager.flush();
                }
        );
    }
}
