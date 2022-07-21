package com.TeamC.Chapter6.Service;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class ReportService {

    private DataSource dataSource;

    private Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public JasperPrint generateJasperPrintFilm() throws Exception{
        InputStream fileReport = new ClassPathResource("reports/Film.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        return jasperPrint;
    }

    public JasperPrint generateJasperPrintUser() throws Exception{
        InputStream fileReport = new ClassPathResource("reports/User.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        return jasperPrint;
    }

    public JasperPrint generateJasperPrintSeat() throws Exception{
        InputStream fileReport = new ClassPathResource("reports/Seat.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        return jasperPrint;
    }

    public JasperPrint generateJasperPrintReservation() throws Exception{
        InputStream fileReport = new ClassPathResource("reports/Reservation.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        return jasperPrint;
    }

    public JasperPrint generateJasperPrintSchedules() throws Exception{
        InputStream fileReport = new ClassPathResource("reports/Schedules.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());
        return jasperPrint;
    }

}
