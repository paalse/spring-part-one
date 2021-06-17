package ru.paalse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paalse.persist.User;
import ru.paalse.persist.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRepr> findWithFilter(String usernameFilter) {
        return userRepository.findUserByUsernameLike(usernameFilter).stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Transactional // А нужна ли ?
    @Override
    public Optional<UserRepr> findById(long id) {
        return userRepository.findById(id).map(UserRepr::new);
    }

    @Transactional
    @Override
    public void save(UserRepr user) {
        userRepository.save(new User(user));
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
