package com.openclassroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassroom.models.DBUser;

public interface DBUserRepository  extends JpaRepository<DBUser, Integer> {
	public DBUser findByName(String name);
}
