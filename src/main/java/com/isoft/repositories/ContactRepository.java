package com.isoft.repositories;

import com.isoft.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findAllByStatusEquals(String status);
//    private final JdbcTemplate template;
//
//    public ContactRepository(JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public int countOfMessages() {
//        return template.queryForObject("select count(*) from contact_message", Integer.class);
//    }
//
//    public int saveContactMessage(Contact contact) {
//        String sql = "insert into contact_message " +
//                "(name,mobile_number,email,subject,message,status,created_at,created_by)" +
//                " values (:name,:mobile_number,:email,:subject,:message,:status,:created_at,:created_by)";
//
//        return template.update(sql, contact.getName(), contact.getMobileNum(), contact.getEmail(),
//                contact.getSubject(), contact.getMessage(), contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
//    }
//
//    public List<Contact> getMessages(String status) {
//        String sql = "select * from contact_message where status=status";
//        return template.query(sql,(ps -> ps.setString(1,status)), new RowToContact());
//    }
//
//    public int closeMessage(int id,String updatedBy){
////        String sql="update contact_message set status='closed',updated_by=?,updated_at=? where contact_id=?";
//        String sql="update contact_message set update_by="+updatedBy+" where contact_id=?";
//
//        return template.update(sql,(ps)-> ps.setInt(1,id));

//        return template.update(sql,(ps)->{
//                ps.setInt(3,id);
//                ps.setString(1,updatedBy);
//                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
//            });
//    }
}
