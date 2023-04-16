package demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://127.0.0.1:8080", "http://localhost:8080", "http://127.0.0.1:5500",
        "http://localhost:5500" })
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

    @GetMapping("/adults")
    public List<User> searchAdultUsers() {
        return userService.getUsersWithAgeOlderThan(17);
    }

    @GetMapping("/search/email/{email}")
    public User searchWithEmail(@PathVariable("email") String email) {
        return userService.getUserWithEmail(email);
    }

    @GetMapping("/search")
    public List<User> searchUsersWithEmailAndAge(@RequestParam("email") String email, @RequestParam("age") int age) {
        return userService.getUserWithEmailAndAge(email, age);
    }

    @GetMapping("/search/age/{min}/{max}")
    public List<User> searchUsersWithAgeBetween(@PathVariable("min") int min, @PathVariable("max") int max) {
        return userService.getUsersWithAgeBetween(min, max);
    }

    @GetMapping("/search/membersinyear")
    public List<User> searchUsersWithMembershipIn(@RequestParam("year") int year) {
        return userService.getListOfMembersIn(year);
    }
}
