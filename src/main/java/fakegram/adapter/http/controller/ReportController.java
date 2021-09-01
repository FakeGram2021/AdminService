package fakegram.adapter.http.controller;

import fakegram.adapter.http.dto.CreateReportDto;
import fakegram.adapter.http.dto.GetReportDto;
import fakegram.domain.model.Report;
import fakegram.domain.model.ReportReason;
import fakegram.domain.service.ReportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @Inject
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @Get("/{reason}")
    public HttpResponse<Collection<GetReportDto>> getReportsByReason(ReportReason reason) {
        return HttpResponse.ok(reportService.findReportByReason(reason)
                .stream()
                .map(GetReportDto::from)
                .collect(Collectors.toList()));
    }

    @Post(consumes = APPLICATION_JSON)
    public HttpResponse<GetReportDto> submitReport(@Body CreateReportDto reportDto) {
        Report report = reportDto.toReport(UUID.randomUUID());
        reportService.createReport(report);
        return HttpResponse.ok(GetReportDto.from(report));
    }

    @Put("/remove-post")
    public HttpResponse<Collection<CreateReportDto>> resolveReportAndRemovePost(@Body GetReportDto reportDto) {
        Report report = reportDto.toReport();
        reportService.terminatePost(report);
        return HttpResponse.noContent();
    }

    @Put("/remove-user")
    public HttpResponse<Collection<CreateReportDto>> resolveReportAndRemoveUser(@Body GetReportDto reportDto) {
        Report report = reportDto.toReport();
        reportService.terminateAccount(report);
        return HttpResponse.noContent();
    }

    @Put("/ignore")
    public HttpResponse<?> ignoreReport(@Body GetReportDto reportDto) {
        Report report = reportDto.toReport();
        reportService.ignoreReport(report);
        return HttpResponse.noContent();
    }



}
