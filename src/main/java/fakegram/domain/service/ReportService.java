package fakegram.domain.service;

import fakegram.domain.model.Report;
import fakegram.domain.model.ReportReason;
import fakegram.domain.repository.ReportRepository;
import jakarta.inject.Inject;

import java.util.List;

public class ReportService {

    private final ReportRepository reportRepository;

    @Inject
    public ReportService(final ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> findReportByReason(ReportReason reportReason) {
        return reportRepository.findReportsByReportReason(reportReason);
    }

    public void createReport(Report report) {
        reportRepository.upsertReport(report);
    }

    public void ignoreReport(Report report) {
        reportRepository.deleteReport(report);
    }

    public void terminatePost(Report report) {
        //kafka
        reportRepository.deleteReport(report);
    }

    public void terminateAccount(Report report) {
        //kafka
        reportRepository.deleteReport(report);
    }

}
