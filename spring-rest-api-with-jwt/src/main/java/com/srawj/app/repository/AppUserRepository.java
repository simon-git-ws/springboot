package com.srawj.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srawj.app.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	Optional<AppUser> findOneByEmail(String email);
}
