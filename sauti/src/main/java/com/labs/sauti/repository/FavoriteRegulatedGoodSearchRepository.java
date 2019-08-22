package com.labs.sauti.repository;

import com.labs.sauti.model.regulated_good.FavoriteRegulatedGoodSearch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface FavoriteRegulatedGoodSearchRepository extends CrudRepository<FavoriteRegulatedGoodSearch, Long> {

    @Query(value = "SELECT * FROM favorite_regulated_good_searches WHERE user_id=:userId", nativeQuery = true)
    ArrayList<FavoriteRegulatedGoodSearch> findAllByUserId(long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM favorite_regulated_good_searches WHERE favorite_regulated_good_search_id=:id AND user_id=:userId", nativeQuery = true)
    void deleteByIdAndUserId(long id, long userId);

}
