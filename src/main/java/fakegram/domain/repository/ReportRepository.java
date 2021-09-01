package fakegram.domain.repository;

import fakegram.domain.model.Report;
import fakegram.domain.model.ReportReason;

import java.util.List;

public interface ReportRepository {

    List<Report> findReportsByReportReason(ReportReason reportReason);

    void upsertReport(fakegram.domain.model.Report report);

    void deleteReport(fakegram.domain.model.Report report);

}
