package com.resotechs.dtos;

import com.resotechs.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@ToString
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;

    public static User getUserFromDto(UserDto userDto){
        User user = new User();
        if(StringUtils.isNotBlank(userDto.getName())){
            user.setName(userDto.getName());
        }else{
            throw new RuntimeException("Name field can't be empty");
        }

        if(StringUtils.isNotBlank(userDto.getEmail())){
            user.setEmail(userDto.getEmail());
        }else{
            throw new RuntimeException("Email field can't be empty");
        }

        if(StringUtils.isNotBlank(userDto.getPassword())){
            user.setPassword(userDto.getPassword());
        }else{
            throw new RuntimeException("Password field can't be empty");
        }

        if(StringUtils.isNotBlank(userDto.getPhone())){
            user.setPhone(userDto.getPhone());
        }else{
            throw new RuntimeException("Phone field can't be empty");
        }

        return user;
    }
}
