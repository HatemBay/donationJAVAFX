package com.donation.IService;

import java.util.List;

public interface IService_1<T> {
    void insert(T t);
    void update(T t);
    void delete(int id);
    List<T> displayAll();
}
