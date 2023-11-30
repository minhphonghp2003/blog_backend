package com.phong.blog.User.Repository;

import com.phong.blog.User.Model.User;
import com.phong.blog.User.Model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredential, Integer> {
    UserCredential findByUsername(String username);
    UserCredential findByUser(User user);

    UserCredential findByEmail(String email);

    @Modifying
    @Query("update UserCredential d set d.passRecvToken = :token where d.email = :email ")
    public void updateRecvToken(@Param(value = "email") String email, @Param(value = "token") String token);
    @Query(value = "select * from remove_recv_token(:email)",nativeQuery = true)
    public void removeRecvToken(@Param(value = "email") String email);
    @Modifying
    @Query("update UserCredential d set d.hashedPassword = :hashed where d.passRecvToken = :token ")
    public void updatePassword(@Param(value = "token") String token, @Param(value = "hashed") String hashed);

}
