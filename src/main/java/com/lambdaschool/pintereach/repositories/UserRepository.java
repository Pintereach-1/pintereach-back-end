package com.lambdaschool.pintereach.repositories;

import com.lambdaschool.pintereach.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
    extends CrudRepository<User, Long>
{
}
