package be.ucll.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.User;
import be.ucll.repo.UserRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithAgeOlderThan(int age) throws ServiceException {
        List<User> returnList = new ArrayList<>();
        returnList = userRepository.findUsersByAgeAfter(age);
        if (returnList.size() == 0) {
            throw new ServiceException("users", String.format("no users with age %d found", age));
        }
        return returnList;
    }

    public User getOldestUser() throws ServiceException {
        if (userRepository.findAllByOrderByAgeDesc().size() == 0) {
            throw new ServiceException("users", "no oldest user found");
        }
        return userRepository.findAllByOrderByAgeDesc().get(0);
    }

    public User getUserWithName(String name) {
        return userRepository.findUserByName(name);
    }

    public User addUser(User user) throws ServiceException {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ServiceException("email", "email already taken");
        }
        userRepository.save(user);
        return user;

    }

    public User getUserWithEmail(String email) throws ServiceException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("user", String.format("No user found with email: %s", email));
        }
        return user;

    }

    public User removeUser(String email) throws ServiceException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("user", "user with this email does not exist");
        }
        userRepository.delete(user);
        return user;
    }

    public List<User> getListOfMembersIn(int year) {
        List<User> usersWithMembershipIn = new ArrayList<>();
        for (User iUser : userRepository.findAll()) {
            for (Integer iYear : iUser.getMembershipList()) {
                if (year == iYear) {
                    usersWithMembershipIn.add(iUser);
                }
            }
        }
        return usersWithMembershipIn;
    }

    public List<User> getUserWithEmailAndAge(String email, int age) {
        List<User> correspondingUsers = new ArrayList<>();
        for (User iUser : userRepository.findAll()) {
            if (iUser.getEmail() == email && iUser.getAge() == age) {
                correspondingUsers.add(iUser);
            }

        }
        return correspondingUsers;
    }

    public List<User> getUsersWithAgeBetween(int min, int max) {
        return userRepository.findUsersByAgeAfterAndAgeBefore(min, max);
    }
}
