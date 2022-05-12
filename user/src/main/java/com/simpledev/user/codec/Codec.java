package com.simpledev.user.codec;

import com.simpledev.user.model.User;
import com.simpledev.user.protocols.UserResponse;
import org.springframework.beans.BeanUtils;

public class Codec {
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
