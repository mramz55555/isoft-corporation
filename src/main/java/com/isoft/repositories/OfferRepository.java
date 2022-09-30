package com.isoft.repositories;

import com.isoft.models.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
//    private JdbcTemplate template;
//
//    public OfferRepository(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public List<Offer> getOffers(){
//        String sql="select * from offer";
//        return template.query(sql, BeanPropertyRowMapper.newInstance(Offer.class));
//    }

}
