package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.Service.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor  //kalau udah pakai AllArgsConstructor ngga perlu lagi pakai anotasi @AutoWired
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    private HttpServletResponse response;

    @GetMapping("/print/film")
    public void getProductFilmReport() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Film.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintFilm();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/print/user")
    public void getProductUserReport() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"User.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintUser();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/print/seat")
    public void getProductUReportSeat() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Seat.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintSeat();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/print/reservation")
    public void getProductUReportReservation() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Reservation.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintReservation();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/print/schedules")
    public void getProductUReportSchedules() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Schedules.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintSchedules();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
