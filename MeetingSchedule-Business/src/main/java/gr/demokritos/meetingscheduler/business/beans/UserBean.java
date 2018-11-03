package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.business.mappers.UserMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.User;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.UserRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class UserBean {
    private UserMapper userMapper = new UserMapper();

    @Inject
    @JpaRepo
    private UserRepository userRepository;

    public UserBean() {

    }

    public void addUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        userRepository.add(user);
    }

    public void updateUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        userRepository.update(user);
    }

    public void removeUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        userRepository.remove(user);
    }

    public boolean usernameExists(String username) {
        return getUserByUsername(username) != null;
    }
    public boolean emailExists(String email) {
        return getUserByEmail(email) != null;
    }

    public UserDto isValidLogin(String email, String password) {
        if(emailExists(email)) {
            UserDto userDto = getUserByEmail(email);
            if(userDto.getPassword().equals(password)) {
                return userDto;
            }
        }
        return null;
    }

    public List<UserDto> getAllUsers() {
        return getUserDtos(userRepository.findAllUsers());
    }

    public List<UserDto> getAllUsers(String sortString) {
        return getUserDtos(userRepository.findAllUsers(sortString));
    }

    public UserDto getUserById(Long id) {
        return userMapper.convertUserToUserDto(userRepository.findMemberById(id));
    }

    public UserDto getUserByUsername(String username) {
        return userMapper.convertUserToUserDto(userRepository.findMemberByUsername(username));
    }

    public UserDto getUserByEmail(String email) {
        return userMapper.convertUserToUserDto(userRepository.findMemberByEmail(email));
    }

    public List<UserDto> getUsersByName(String name) {
        return getUserDtos(userRepository.findUserByName(name));
    }

    public List<UserDto> getUsersByLastName(String lastName) {
        return getUserDtos(userRepository.findUserByLastName(lastName));
    }

    private List<UserDto> getUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(user -> {
                UserDto userDto = userMapper.convertUserToUserDto(user);
                userDtos.add(userDto);
            });
        }
        return userDtos;
    }
}
