package gr.demokritos.meetingscheduler.business.mappers;

import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.User;

public class UserMapper {

    public UserDto convertUserToUserDto(User user) {
        if(user == null) return null;
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        if(userDto == null) return null;
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
