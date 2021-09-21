package com.userproject.service;

import com.userproject.model.User;
import com.userproject.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;


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
    @Cacheable("User_Response")
    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable("userResponse")
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Cacheable("userCache")
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Caching(put = {@CachePut(value = "User_Response")}, evict = {@CacheEvict(value = "userCache", allEntries = true)})
    public User Add(User user) {

        userRepository.save(user);
        log.info("Kullanıcı kayıt oldu -> "+user.getEmail());
        return user;

    }

    @Override
    @Caching(put = {@CachePut(value = "User_Response")}, evict = {@CacheEvict(value = "userCache", allEntries = true)})
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
    @Caching(put = {@CachePut(value = "User_Response")}, evict = {@CacheEvict(value = "userCache", allEntries = true)})
    public void Delete(int id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
        log.info("Kullanıcı silindi ->"+user.getEmail());
    }


}
