package com.lambdaschool.pintereach.services;

import com.lambdaschool.pintereach.exception.ResourceNotFoundException;
import com.lambdaschool.pintereach.models.User;
import com.lambdaschool.pintereach.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl
        implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    /**
     * Connects this service to the Role table
     */


    public User findUserById(long id) throws
            ResourceNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserId() != 0)
        {
            userrepos.findById(user.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserId() + " not found!"));
            newUser.setUserId(user.getUserId());
        }

        newUser.setUsername(user.getUsername()
                .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());





        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
            User user,
            long id)
    {
        User currentUser = findUserById(id);

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                    .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }







        return userrepos.save(currentUser);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
