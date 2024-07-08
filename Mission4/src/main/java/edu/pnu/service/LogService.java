package edu.pnu.service;

import java.sql.SQLException;
import edu.pnu.dao.LogDao;

public class LogService {
    private LogDao logDao;
    public LogService() throws SQLException {
        logDao = new LogDao();
    }

    public void doLog() {
        try { logDao.addLog("GET", "SELECT * FROM table", true);
        } catch (SQLException e) {
            System.err.println("작업 중 오류 발생: " + e.getMessage());
        }
    }
}