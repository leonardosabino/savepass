package br.com.savepass.savepass.controller;

import br.com.savepass.savepass.model.vo.UserVO;
import br.com.savepass.savepass.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "UserController", tags = "UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Find all users")
    public List<UserVO> findUser() {
        return userService.findUser();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Find user by ID")
    public UserVO findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    public UserVO saveUser(@Validated @RequestBody UserVO user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete a existent user")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete all existent users")
    public void deleteUser() {
        userService.deleteUser();
    }
}
