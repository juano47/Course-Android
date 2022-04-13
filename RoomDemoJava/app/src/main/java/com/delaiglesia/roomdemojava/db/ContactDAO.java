package com.delaiglesia.roomdemojava.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.delaiglesia.roomdemojava.db.entity.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    Long addContact(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Delete
    void deleteContact(int id);

    @Query("select * from contacts")
    List<Contact> getAllContacts();

    @Query("select * from contacts where contact_id == :contactId")
    Contact getContactById(int contactId);
}
