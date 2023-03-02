package demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/oldest")
    public User getOldestUser() {
        return userService.getOldestUser();
    }

    @GetMapping("/search/olderthan")
    public List<User> searchUsersWithAgeOlderThan(@RequestParam("age") int age) {
        return userService.getUsersWithAgeOlderThan(age);
    }

    @GetMapping("/search/{name}")
    public User searchUserWithName(@PathVariable("name") String name) {
        return userService.getUserWithName(name);
    }

}
