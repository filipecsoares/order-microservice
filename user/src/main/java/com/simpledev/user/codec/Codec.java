package com.simpledev.user.codec;

import com.simpledev.user.model.UserEntity;
import com.simpledev.user.protocols.UserResponse;
import com.simpledev.user.protocols.UsersResponse;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Codec {
    public static UserResponse toResponse(UserEntity user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public static UsersResponse toResponse(List<UserEntity> users) {
        return new UsersResponse(users.stream().map(Codec::toResponse).collect(Collectors.toList()));
    }
}
