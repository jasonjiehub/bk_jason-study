package com.sogou.study.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by denglinjie on 2016/10/9.
 */
public class MongoTest {
    public static void main(String[] args) {
        noAuthTest();
//        withAuthTest();
    }

    public static void noAuthTest() {
//        deleteDocument();
//        insertDocument();
        findAllCollection();
//        updateDocument();
//        findAllCollection();
//        createIndex();
//        mapReduce();
    }

    /**
     * 向一个集合中插入数据
     */
    public static void insertDocument() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            mongoDatabase.createCollection("testColl");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl");
            Document document1 = new Document("name", "linjie").append("age", 27).append("school", "hust");
            Document document2 = new Document("name", "tinting").append("age", 25).append("school", "whut");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document1);
            documents.add(document2);
            collection.insertMany(documents);
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void updateDocument() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl");
            FindIterable<Document> findIterable = collection.find(Filters.eq("name", "linjie"));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            Document oldDocument = new Document();
            oldDocument.put("address", "huake");
            collection.updateMany(Filters.eq("name", "linjie"), new Document("$set", oldDocument));
            System.out.println(new ObjectId().getTimestamp());
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public static void mapReduce() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl2");

            Document document = new Document().append("name", "Understanding JAVA").append("pages", 100);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding JAVA").append("pages", 100);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding PHTHON").append("pages", 200);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding XML").append("pages", 300);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding MONGODB").append("pages", 400);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding C++").append("pages", 500);
            collection.insertOne(document);

            document = new Document().append("name", "Understanding C#").append("pages", 500);
            collection.insertOne(document);

            String map = "function() { "+
                    "var category; " +
                    "if ( this.pages >= 250 ) "+
                    "category = 'Big Books'; " +
                    "else " +
                    "category = 'Small Books'; "+
                    "emit(category, {name: this.name});}";

            String reduce = "function(key, values) { " +
                    "var sum = 0; " +
                    "values.forEach(function(doc) { " +
                    "sum += 1; "+
                    "}); " +
                    "return {books: sum};} ";
            MapReduceIterable<Document> documents = collection.mapReduce(map, reduce);
            MongoCursor<Document> iterator = documents.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }

    /**
     * 删除一个集合
     */
    public static void deleteDocument() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl");
            collection.deleteMany(Filters.eq("name", "linjie"));
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * 遍历所有的集合
     */
    public static void findAllCollection() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl");
            FindIterable<Document> findIterable = collection.find(Filters.eq("name", new BasicDBObject("$regex", "linjie")));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * 索引测试
     */
    public static void createIndex() {
        try{
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            MongoDatabase mongoDatabase = mongoClient.getDatabase("jason");
            MongoCollection<Document> collection = mongoDatabase.getCollection("testColl");
            ListIndexesIterable<Document> documents = collection.listIndexes();
            MongoCursor<Document> mongoCursor = documents.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
            System.out.println("------------------");
            collection.createIndex(Filters.and(new BasicDBObject("name", 1), new BasicDBObject("school", 1)));
            documents = collection.listIndexes();
            mongoCursor = documents.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
            collection.dropIndex(new BasicDBObject("name", 1));
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    /**
     * 连接mongodb库需要账号密码验证
     */
    public static void withAuthTest() {
        try {
            ServerAddress serverAddress = new ServerAddress("10.10.10.10", 20728);
            List<ServerAddress> addressList = new ArrayList<ServerAddress>();
            addressList.add(serverAddress);

            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databasename", "password".toCharArray());
            List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
            mongoCredentialList.add(credential);

            MongoClient mongoClient = new MongoClient(addressList, mongoCredentialList);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("databasename");

            Calendar startCalendar = Calendar.getInstance();
            startCalendar.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            startCalendar.add(Calendar.DAY_OF_MONTH, -1);
            long startUpdateTime = startCalendar.getTime().getTime() / 1000;

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.set(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), endCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            endCalendar.add(Calendar.DAY_OF_MONTH, -0);
            long endUpdateTime = endCalendar.getTime().getTime() / 1000;

            MongoCollection<Document> collection = mongoDatabase.getCollection("collectionname1");
            System.out.println("collectionname1" + collection.count(Filters.and(Filters.gte("update_time", startUpdateTime), Filters.lt("update_time", endUpdateTime))));

            collection = mongoDatabase.getCollection("collectionname2");
            System.out.println("collectionname2: " + collection.count(Filters.and(Filters.gte("update_time", startUpdateTime), Filters.lt("update_time", endUpdateTime))));
        } catch (Exception e) {
            System.out.println("connect fail");
        }
    }
}
