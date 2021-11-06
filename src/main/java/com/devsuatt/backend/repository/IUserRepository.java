package com.devsuatt.backend.repository;

import com.devsuatt.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Bu class'ın bir repository olması gerektiğini belirtiyoruz.
@Repository
// JpaRepository'den extends aldık, JpaRepository bize temel DB işlemlerini
// CRUD, Pagination, Sorting vs. işlemleri sağlayan bir framework
public interface IUserRepository extends JpaRepository<User, Long> {
    // burada db işlemleri ile ilgili CRUD vs. metotları bulunur,
    // biz bu aşamada JpaRepository'den inherit aldığımız için ona ait olan
    // metotları, bu repository interface'ini implemente eden servis katmanındaki
    // class'larda kullanabileceğiz. (bkz: UserManager)
    boolean existsUserByUsername(String username);
}
