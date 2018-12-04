package com.example.springdataexcercise;

import net.bytebuddy.TypeCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    List<User> findAllJPQL();

    @Query(value = "select*from USER ", nativeQuery = true)
    List<User> findAllSQL();

    List<User> findAllByFirstNameIsContainingAndLastNameIsContaining(String text, String text2);

    @Query("select u from User u where u.firstName like concat('%',:let,'%')")
    List<User> findAllThatHaveOJPQL(@Param("let") String letter);

    @Query(value = "SELECT*FROM User u WHERE u.firstName like :let%:let and u.lastName like :let%:let", nativeQuery = true)
    List<User> findAllThatHave(@Param("let") String letter);

    @Transactional
    void deleteAllByFirstNameStartingWithIgnoreCase(String letter);
@Transactional
@Modifying
@Query("delete from User u where u.firstName like concat(:let,'%')")
    void deleteAllByFirstNameStartingWithJPQL(@Param("let")String letter);

    @Transactional
    @Modifying
    @Query("delete from User u where u.firstName like :let%")
    void deleteAllByFirstNameStartingWithSQL(@Param("let")String letter);

    List<User> findAllByOrderByLastName();
}