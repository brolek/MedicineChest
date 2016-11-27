package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.example.asus.mycar.db");
        schema.enableKeepSectionsByDefault();

        addMedicine(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void addTables(final Schema schema) {
//        Entity user = addUser(schema);
//        Entity repo = addRepo(schema);
//
//        Property userId = repo.addLongProperty("userId").notNull().getProperty();
//        user.addToMany(repo, userId, "userRepos");
//    }

    private static Entity addMedicine(final Schema schema) {
        Entity medicine = schema.addEntity("Medicine");
        medicine.addIdProperty().primaryKey().autoincrement();
        medicine.addStringProperty("name").notNull();
        medicine.addShortProperty("comment");
        medicine.addDateProperty("date");


        return medicine;
    }

//    private static Entity addRepo(final Schema schema) {
//        Entity repo = schema.addEntity("Repo");
//        repo.addIdProperty().primaryKey().autoincrement();
//        repo.addStringProperty("title").notNull();
//        repo.addStringProperty("language");
//        repo.addIntProperty("watchers_count");
//
//        return repo;
//    }

}
