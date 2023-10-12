package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserWrongPasswordException;
import imt.framework.back.imtframeworkback.data.models.UserModel;
import imt.framework.back.imtframeworkback.data.repositories.UserRepository;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return User.fromData(userRepository.save(UserModel.fromDomain(user)));
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return userRepository.findByMail(mail).map(User::fromData);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByMail(username).map(User::fromData);
        if (user.isEmpty()) throw new UserNotFoundException(username);
        User existing = user.get();
        if (!passwordEncoder.matches("test", existing.getPassword())) {
            throw new UserWrongPasswordException(username);
        }
        return existing;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id).map(User::fromData);
    }
}
