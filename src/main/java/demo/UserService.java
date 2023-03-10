package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userRepository = new ArrayList<>();

    public UserService() {
    }

    public List<User> getAllUsers() {
        return userRepository;
    }


    public List<User> getUsersWithAgeOlderThan(int age) {
        return userRepository.stream().filter(user -> user.getAge()>age).toList();
    }

    public User getOldestUser() {
        User oldest = null;
        if (userRepository.size()>0) {
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

        if (alreadyExists == false){ 
            userRepository.add(user);
            return true;}
           
        else
            return false;

            
    }

    public User getUserWithEmail(String email){
        for(User iUser : userRepository){
            if (iUser.getEmail() == email)
            return iUser;
        }
        return null;
    }

    public User removeUser(String email){
        for(User iUser : userRepository){
            if (iUser.getEmail() == email){
                userRepository.remove(iUser);
                return iUser;
            }   
        }
        return null;
    }

    public List<User> getListOfMembersIn(int year){
        List<User> usersWithMembershipIn = new ArrayList<>();
        for(User iUser : userRepository){
            for (Integer iYear : iUser.getMembershipList()){
                if (year == iYear){
                    usersWithMembershipIn.add(iUser);
                }
            }
        }
        return usersWithMembershipIn;
    }

}
