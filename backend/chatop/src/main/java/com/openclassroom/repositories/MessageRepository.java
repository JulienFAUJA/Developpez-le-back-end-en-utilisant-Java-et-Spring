package com.openclassroom.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.models.MessageModel;

@Repository
public interface MessageRepository  extends CrudRepository<MessageModel, Integer> {
}
