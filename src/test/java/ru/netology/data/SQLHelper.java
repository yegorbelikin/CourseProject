package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static Object searchApprovedInPayment() {
        var codeSQL = "SELECT transaction_id FROM payment_entity WHERE status = 'APPROVED' LIMIT 1";
        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, codeSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static long searchApprovedInOrder() {
        var transactionIdInOrder = "SELECT true FROM order_entity WHERE payment_id = (?) LIMIT 1";
        try (var conn = getConn()) {
        return QUERY_RUNNER.query(conn, transactionIdInOrder, searchApprovedInPayment(), new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static Object searchDeclinedInPayment() {
        var codeSQL = "SELECT transaction_id FROM payment_entity WHERE status = 'DECLINED' LIMIT 1";
        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, codeSQL, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static long searchDeclinedInOrder() {
        var transactionIdInOrder = "SELECT true FROM order_entity WHERE payment_id = (?) LIMIT 1";
        try (var conn = getConn()) {
            return QUERY_RUNNER.query(conn, transactionIdInOrder, searchDeclinedInPayment(), new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        try (var conn = SQLHelper.getConn()) {
            SQLHelper.QUERY_RUNNER.execute(conn, "DELETE FROM  db.payment_entity");
            SQLHelper.QUERY_RUNNER.execute(conn, "DELETE FROM  db.order_entity");
        }
    }
}


