package com.example.bedms.model.dto;

import com.example.bedms.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String username;
    private ERole role;
    private String message;
}
