package com.te.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.jr.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
