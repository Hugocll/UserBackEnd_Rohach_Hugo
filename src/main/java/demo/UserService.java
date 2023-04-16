package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userRepository = new ArrayList<>();

    public UserService() {
        User elke = new User("Elke", 44, "elke@ucll.be", "elke");
        User miyo = new User("Miyo", 18, "miyo@ucll.be", "miyo");
        User eric = new User("Eric", 65, "eric@kuleuven.be", "eric");
        User yuki = new User("Yuki", 13, "yuki@ucll.be", "yuki");
        User stijn = new User("Stijn", 45, "stijn@ucll.be", "stijn");
        elke.addMembershipYear(2002);

        userRepository.add(elke);
        userRepository.add(miyo);
        userRepository.add(eric);
        userRepository.add(yuki);
        userRepository.add(stijn);

    }

    public List<User> getAllUsers() {
        return userRepository;
    }

    public List<User> getUsersWithAgeOlderThan(int age) {
        return userRepository.stream().filter(user -> user.getAge() > age).toList();
    }

    public User getOldestUser() {
        User oldest = null;
        if (userRepository.size() > 0) {
            oldest = userRepository.get(0);
            for (User user : userRepository) {
                if (user.getAge() > oldest.getAge())
                    oldest = user;
            }
        }
        return oldest;
    }

    public User getUserWithName(String name) {
        return userRepository.stream().filter(user -> user.getName().equals(name)).toList().get(0);
    }

    public boolean addUser(User user) {
        boolean alreadyExists = false;

        for (User loopUser : userRepository)
            if (loopUser.getEmail() == user.getEmail())
                alreadyExists = true;

        if (alreadyExists == false) {
            userRepository.add(user);
            return true;
        }

        else
            return false;

    }

    public User getUserWithEmail(String email) {
        for (User iUser : userRepository) {
            if (iUser.getEmail() == email)
                return iUser;
        }
        return null;
    }

    public User removeUser(String email) {
        for (User iUser : userRepository) {
            if (iUser.getEmail() == email) {
                userRepository.remove(iUser);
                return iUser;
            }
        }
        return null;
    }

    // This
    public List<User> getListOfMembersIn(int year) {
        List<User> usersWithMembershipIn = new ArrayList<>();
        for (User iUser : userRepository) {
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
        for (User iUser : userRepository) {
            if (iUser.getEmail() == email && iUser.getAge() == age) {
                correspondingUsers.add(iUser);
            }

        }
        return correspondingUsers;
    }

    public List<User> getUsersWithAgeBetween(int min, int max) {
        return userRepository.stream().filter(user -> user.getAge() > min && user.getAge() < max).toList();
    }
}
