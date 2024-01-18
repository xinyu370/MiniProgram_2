package com.mini.oauth.dao;

import com.mini.oauth.dao.entity.AuthoritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<AuthoritiesEntity,Long> {
    List<AuthoritiesEntity> findByUserId(Long id);
}
