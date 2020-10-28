package io.shiva.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.shiva.entity.User;
import io.shiva.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println(userName);
		Optional<User> user = userRepo.findByUserName(userName);

//		if (!user.isPresent()) {
//			throw new UsernameNotFoundException("No UserName exist");
//		}

		user.orElseThrow(() -> new UsernameNotFoundException("Not Found" + userName));

		return user.map(MyUserDetails::new).get();
	}
}
