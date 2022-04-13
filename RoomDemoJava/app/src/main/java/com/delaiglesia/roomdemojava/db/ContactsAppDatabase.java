package com.delaiglesia.roomdemojava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.delaiglesia.roomdemojava.db.entity.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();
}
