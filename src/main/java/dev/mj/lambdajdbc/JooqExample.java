package dev.mj.lambdajdbc;

import static java.sql.DriverManager.deregisterDriver;
import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.registerDriver;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.jooq.impl.DSL;

public class JooqExample implements DriverAction {

    private static final String JDBC_URL = "jdbc:mysql://"
            + "localhost:3306/student"
            + "?autoReconnect=true&useSSL=false";

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Root@2018";

    public static void main(String[] args) throws Exception {

        registerDriver(new com.mysql.cj.jdbc.Driver(), new JooqExample());

        try (Connection con = getConnection(JDBC_URL, USER_NAME, PASSWORD)) {

            String sql = "select id, name, age from student.bio";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                Map<Long, Student> studentMap = DSL
                        .using(con)
                        .fetch(sql)

                        .map(rs -> Student.builder()
                                .id(rs.getValue("id", Long.class))
                                .name(rs.getValue("name", String.class))
                                .age(rs.getValue("age", Integer.class))
                                .build())

                        .parallelStream()

                        .collect(toUnmodifiableMap(Student::getId, identity()));

                System.out.println(studentMap);
            }
        }

    }

    @Override
    public void deregister() {
        try {
            deregisterDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
