package com.example.exercise.service;

import com.example.exercise.model.User;

public interface UserService {

    /****
     *
     * @param id of the user that is retrieved
     * @return the user with the given id
     */
    User getUserById(Long id);

    /***
     *
     * @param user the user with the details
     * @return a new user
     */
    User createUser(User user);

    /***
     *
     * @param id the id of the user that is delted
     */
    void deleteUser(Long id);
}
