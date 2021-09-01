package fakegram.adapter.cassandra.repository;


import com.datastax.oss.driver.api.core.CqlSession;
import fakegram.adapter.cassandra.dao.ReportDao;
import fakegram.adapter.cassandra.mapper.ReportMapper;
import fakegram.adapter.cassandra.mapper.ReportMapperBuilder;
import fakegram.adapter.cassandra.model.Report;
import fakegram.domain.model.ReportReason;
import fakegram.domain.repository.ReportRepository;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static com.datastax.oss.driver.api.core.CqlIdentifier.fromCql;

public class CassandraReportRepository implements ReportRepository {

    private static final String KEYSPACE = "admin";
    private static final String REPORT_TABLE = "report";


    private final ReportDao reportDao;

    @Inject
    public CassandraReportRepository(final CqlSession session) {
        final ReportMapper reportMapper = new ReportMapperBuilder(session).build();
        reportDao = reportMapper.reportDao(
                fromCql(KEYSPACE),
                fromCql(REPORT_TABLE)
        );
    }

    public List<fakegram.domain.model.Report> findReportsByReportReason(ReportReason reportReason) {
        return reportDao
                .findByReportReason(reportReason.toString())
                .all()
                .stream()
                .map(Report::toReport)
                .collect(Collectors.toList());
    }

    public void upsertReport(fakegram.domain.model.Report report) {
        Report persistingReport = Report.from(report);
        reportDao.upsert(persistingReport);
    }

    public void deleteReport(fakegram.domain.model.Report report) {
        Report persistingReport = Report.from(report);
        reportDao.delete(persistingReport);
    }

}
