package be.ucll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import be.ucll.model.User;
import be.ucll.service.ServiceException;
import be.ucll.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "http://127.0.0.1:8080", "http://localhost:8080", "http://127.0.0.1:5500",
        "http://localhost:5500" })
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public Map<String, String> handleServiceExceptions(ServiceException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return errors;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/oldest")
    public User getOldestUser() throws ServiceException {
        return userService.getOldestUser();
    }

    @GetMapping("/search/olderthan")
    public List<User> searchUsersWithAgeOlderThan(@RequestParam("age") int age) throws ServiceException {
        return userService.getUsersWithAgeOlderThan(age);
    }

    @GetMapping("/search/{name}")
    public User searchUserWithName(@PathVariable("name") String name) {
        return userService.getUserWithName(name);
    }

    @GetMapping("/adults")
    public List<User> searchAdultUsers() throws ServiceException {
        return userService.getUsersWithAgeOlderThan(17);
    }

    @GetMapping("/search/email/{email}")
    public User searchWithEmail(@PathVariable("email") String email) throws ServiceException {
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

    @PostMapping
    public User addUser(@Valid @RequestBody User user) throws ServiceException {
        return userService.addUser(user);
    }

    @DeleteMapping("/{email}")
    public User deleteUser(@PathVariable("email") String email) throws ServiceException {
        return userService.removeUser(email);
    }
}
