package com.xrq.springbootwebflux.repository;

import com.xrq.springbootwebflux.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private ConcurrentMap<Integer,User> repository=new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator=new AtomicInteger();
    /**保存用户对象
     * @return  成功true
     */
    public boolean save(User user)
    {
        Integer id=idGenerator.incrementAndGet();
        user.setId(id);
        return  repository.put(id,user)==null;

    }

    public Collection<User> findAll() {
        return repository.values();
    }
}
