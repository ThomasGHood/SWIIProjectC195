//package main;
//
//import java.sql.SQLException;
//
//public class DebugMain {
//    private static int customerIndex;
//
//    public static void main(String[] args) throws SQLException {
//
//
////        LocalDate estDate = LocalDate.of(2021, 12, 31);
////        LocalTime estTime = LocalTime.of(8, 00);
////
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MMM dd | HH:mm");
////
////        ZoneId zoneTest = ZoneId.of("America/Vancouver");
////        ZoneId businessZone = ZoneId.of("America/New_York");
////
////        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
////
////        //Take it from the combobox stuff, so you get a date, and you get a time, and you get a
////
////        ZonedDateTime estTimeZone = ZonedDateTime.of(estDate, estTime, businessZone);
////        ZonedDateTime localSystemTimeZone = estTimeZone.withZoneSameInstant(localZoneId);
////        ZonedDateTime localSystemTimeZone3 = localSystemTimeZone.withZoneSameInstant(zoneTest);
////        ZonedDateTime localSystemTimeZone2 = localSystemTimeZone.withZoneSameInstant(localZoneId);
////
////        System.out.println(estTimeZone);
////        System.out.println(localSystemTimeZone);
////        System.out.println(localSystemTimeZone2);
////        System.out.println(localSystemTimeZone3.toLocalTime());
////
////        ZoneId mstTimeZone = ZoneId.of("America/Denver");
////
////        System.out.println(estTimeZone.getOffset());
//
//
//
////        System.out.println(localSystemTimeZone.getOffset());
////        System.out.println(estTimeZone.getOffset());
////
////        System.out.println(estTimeZone.withZoneSameInstant(mstTimeZone).getOffset());
//
//
//
//        //System.out.println(ZonedDateTime.now().toInstant());
//        //System.out.println(ZonedDateTime.now().format(dtf));
//
//
////        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("Europe")).forEach(System.out::println);
////
////        int startA = -3;
////        int startB = 0;
////        int endA = 1;
////        int endB = 3;
////
////        if((startA < startB) && ((endA >= startB))){
////            System.out.println("included");
////        } else{
////            System.out.println("not");
////        }
//
////        JDBC.openConnection();
////        //lambda expression
////        ContactsQuery.select();
////        ListManager.getAllContacts().stream().forEach(System.out::println);
////
////
////        JDBC.closeConnection();
//
//
//    }
//}
