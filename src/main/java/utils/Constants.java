package utils;

import dao.Dao;
import model.Toy;
import model.User;

public class Constants {
	
	public static final String URL = "jdbc:mysql://localhost:3306/shop";
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String JDBC = "com.mysql.jdbc.Driver";
    public static final String USERTABLE = "users";
    public static final String TOYTABLE = "toys";
    public static final String COMMENTTABLE = "orders";
    public static final String USERINSERT = "insert into "+USERTABLE+" values(?,?,?,?,?,?);";
	public static final String COMMENTINSERT = "insert into "+COMMENTTABLE+" values(?,?,?);";
	public static final String COMMENTUPDATE = "update "+COMMENTTABLE+" set review=dsd where username= and prod=   ;";
    public static Toy toy;
    public static User user;
   
    
    /*
     * 
     * create database shop;
     * use shop;
     * 
     * User Table:
     *	create table users(fname varchar(30),username varchar(20),pass varchar(20),address varchar(50),gender varchar(20),mob bigint(11));
     *   insert into users values("Virat kohli","virat","virat","35th floor of the Omkar 1973 Worli, Mumbai","male",9876543210);
     *   insert into users values("Rohit Sharma","rohit","rohit","A 4-BHK apartment in Ahuja Towers, Worli, Mumbai","",9876543210);
     *   insert into users values("sania mirza","sania","sania","Murthuzaguda, Hyderabad, Andhra Pradesh, India","female",9876543210);
     *   
     *  
     *  Toys table:
     *  create table toys(name varchar(60),price varchar(20),seller varchar(20),typeof varchar(20),image varchar(60));
     *  
     *  boys entries:
     *  insert into toys values("Monster Buggies Toy","1559","kirpal","boy","MonsterBuggiesToy.jpg");
     *  insert into toys values("Skill Matics STEM Biuilding Toy","1009","skillmatics","boy","SkillmaticsSTEMBiuildingToy.jpg");
     *  insert into toys values("Smartivity Hydraulic Crane STEM Educational","350","samty","boy","SmartivityHydraulicCraneSTEMEducational.jpg");
     *  insert into toys values("Soft Bullet Gun Toy","700","serta","boy","SoftBulletGunToy.jpg");
     *  insert into toys values("World Globe","1200","sema","boy","WorldGlobe.jpg");
     *  
     *  girls entries:
     *  insert into toys values("Cable World Beauty Kit Toy Set","1219","cb world","girl","CableWorldBeautyKitToySet.jpg");
     *  insert into toys values("Tahera Girls Kids Gift Cartoon Pretend Play Toy","220","Tahera world","girl","TaheraGirlsKidsGiftCartoonPretendPlayToy.jpg");
     *  insert into toys values("Toyzone Plastic Barbie My Little Kitchen Play Set for Girls","1000","toyzone world","girl","ToyzonePlasticBarbieMyLittleKitchenPlaySetforGirls.jpg");
     *  insert into toys values("Unicorn Teddy Bear Plush Soft Toy","400","unicorn world","girl","UnicornTeddyBearPlushSoftToy.jpg");
     *  insert into toys values("Webby Unicorn Kids Play Tent House","899","wendys","girl","WebbyUnicornKidsPlayTentHouse.jpg");
     *  insert into toys values("zest 4 toyz 360 degree rotating musical dancing girl doll","600","zeetoys","girl","zest4toyz360degreerotatingmusicaldancinggirldoll.jpg");
     *  
     *  Educational entries:
     *  insert into toys values("Alphabet Fun Type","500","funty","edu","AlphabetFunType.jpg");
     *  insert into toys values("Dinosaur Figure Toys","800","dino","edu","DinosaurFigureToys.jpg");
     *  insert into toys values("Einstein Box Science Experiment Kit","499","sema","edu","EinsteinBoxScienceExperimentKit.jpg");
     *  insert into toys values("Laptop Toy","500","tt shop","edu","laptoptoy.jpg");
     *  insert into toys values("Pop Fidget Toy","300","seta","edu","PopFidgetToy.jpg");
     *  
     *  
     *  create table orders(username varchar(20),prod varchar(60),review varchar(250));
     *  insert into orders values("virat","Monster Buggies Toy","the battrey life of the truck is low but overall good product");
     *  
     * 
     * */

}
