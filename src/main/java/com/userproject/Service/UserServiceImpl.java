package com.userproject.Service;

import com.userproject.model.User;
import com.userproject.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    public UserServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;

    }
    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User Add(User user) {

        userRepository.save(user);
        log.info("Kullanıcı kayıt oldu ->"+user.getEmail());
        return user;

    }

    @Override
    public void Update(int id,User user) {
        User oldUser = userRepository.getById(id);
        oldUser.setEmail(user.getEmail());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setDateOfBirth(user.getDateOfBirth());
        oldUser.setPhone(user.getPhone());
        oldUser.setNationalityId(user.getNationalityId());
        userRepository.save(oldUser);
        log.info("Kullanıcı güncellendi ->"+user.getEmail());
    }

    @Override
    public void Delete(int id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        log.info("Kullanıcı silindi ->"+user.getEmail());
    }


}
