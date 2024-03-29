package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.Service.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@Tag(name = "8. Reports Controller")
@SecurityRequirement(name = "bearer-key")
@AllArgsConstructor  //kalau udah pakai AllArgsConstructor ngga perlu lagi pakai anotasi @AutoWired
@RequestMapping("/team3")
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

    @GetMapping("/print/film/{id}")
    public void getProductUReportFilmByid(Long filmId) throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"FilmById.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintFilmBy(filmId);
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

    @GetMapping("/print/reservations")
    public void getProductUReportReservations() throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Reservations.pdf\"");
        JasperPrint jasperPrint = reportService.generateJasperPrintReservations();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/print/reservations/byuserName")
    public void getProductUReportReservationByUsername(Principal principal) throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"ReservationsByName.pdf\"");
        String userName = principal.getName();
        JasperPrint jasperPrint = reportService.generateJasperPrintReservationBy(userName);
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
