package be.ucll.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import be.ucll.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findUsersByAgeAfter(int age);

    public List<User> findUsersByAgeAfterAndAgeBefore(int min, int max);

    public User findUserByEmail(String email);

    public User findUserByName(String name);

    public List<User> findAllByOrderByAgeDesc();

    public User deleteUserByEmail(String email);
}