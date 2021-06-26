package com.users.repository;

import org.springframework.data.repository.CrudRepository;

import com.users.entity.User;


public interface UserRepository extends CrudRepository<User, String> {

}
