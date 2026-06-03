package com.library.library_management.repository;

import com.library.library_management.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
        extends JpaRepository<Admin, String> {

    Admin findByIdAndPassword(
            String id,
            String password
    );
}