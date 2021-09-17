package com.userproject.controller;

import com.userproject.Service.UserService;
import com.userproject.dto.UserDto;
import com.userproject.mapper.UserMapper;
import com.userproject.model.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usercontroller")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("/getalldto")
    public ResponseEntity<List<UserDto>> getAllDto(){
        List<User> userList = userService.getAll();
        List<UserDto> userDtos = userMapper.userDtos(userList);
        return ResponseEntity.ok(userDtos);
    }
    @GetMapping("/getbyemail")
    public User getByEmail(@RequestParam String email){
        return userService.getByEmail(email);
    }

    @GetMapping("/getbyiddto")
    public ResponseEntity<UserDto> findById(@RequestParam int id)
    {
        User user = userService.getById(id);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/getbyid")
    public User getById(@RequestParam int id){

        return userService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> Add(@Valid @RequestBody User user){
        var links = new Link[]{
                linkTo(methodOn(UserController.class).getAll()).withRel("User").withType("GET"),
        linkTo(methodOn(UserController.class).getById(user.getId())).withRel("User").withType("GET")
        };
        user.add(links);
        return ResponseEntity.ok(userService.Add(user));
    }

    @DeleteMapping("/delete")
    public void Delete(@RequestParam int id){
        userService.Delete(id);
    }

    @PutMapping("update")
    public void Update(@RequestParam int id,@RequestBody User user){
        var links = new Link[]{
                linkTo(methodOn(UserController.class).getAll()).withRel("User").withType("GET"),
                linkTo(methodOn(UserController.class).getById(user.getId())).withRel("User").withType("GET")
        };
        user.add(links);
        userService.Update(id,user);
    }
}
