package com.openclassroom.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassroom.models.MessageModel;

@Repository
public interface MessageRepository  extends CrudRepository<MessageModel, Integer> {
//	public Optional<MessageModel> findById(Integer id);
//	public List<MessageModel> findAll();

}
