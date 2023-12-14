package com.example.jobportal.requestdto;


import org.springframework.stereotype.Component;

import com.example.jobportal.enums.UserRole;



@Component
public class UserRequestDto {


	private String username;

		private String email;

	private String password;

	private UserRole userrole;

	
}
