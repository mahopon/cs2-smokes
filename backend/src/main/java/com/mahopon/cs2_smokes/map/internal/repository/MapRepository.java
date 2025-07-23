package com.mahopon.cs2_smokes.map.internal.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahopon.cs2_smokes.map.api.model.Map;

import java.util.List;

public interface MapRepository extends CrudRepository<Map, Integer> {
}
