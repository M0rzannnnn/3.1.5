package ru.vinogradov.kataBoot.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.kataBoot.model.User;
import ru.vinogradov.kataBoot.service.RoleService;
import ru.vinogradov.kataBoot.service.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/admin"})
public class RestControllerApp {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestControllerApp(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional> getUserById(@PathVariable("id") long id){
        Optional<User> user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public List<User> showAllUsers() {
        return  userService.getAllUsers();
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable("id") Long id) {

        this.userService.updateUser(id, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody User user) {
        this.userService.saveUser(user);
return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
