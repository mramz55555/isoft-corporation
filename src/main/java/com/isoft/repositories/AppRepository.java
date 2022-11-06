package com.isoft.repositories;

import com.isoft.models.App;
import org.springframework.data.repository.CrudRepository;

public interface AppRepository extends CrudRepository<App, Integer> {
}
