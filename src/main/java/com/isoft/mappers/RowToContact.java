package com.isoft.mappers;

import com.isoft.models.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Deprecated
public class RowToContact implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("contact_id"));
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setStatus(rs.getString("status"));
        contact.setMessage(rs.getString("message"));
        contact.setMobileNum(rs.getString("mobile_number"));
        contact.setSubject(rs.getString("subject"));
        contact.setCreatedBy(rs.getString("created_by"));
        contact.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        contact.setUpdatedBy(rs.getString("updated_by"));

        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null)
            contact.setUpdatedAt(updatedAt.toLocalDateTime());

        return contact;
    }
}
