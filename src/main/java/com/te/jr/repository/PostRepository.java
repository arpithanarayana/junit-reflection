package com.te.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.jr.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
