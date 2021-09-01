package fakegram.adapter.http.dto;

import fakegram.domain.model.Report;
import fakegram.domain.model.ReportReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReportDto {

    private String reportedAccountId;
    private String reporterAccountId;
    private String reportedPostId;
    private ReportReason reportReason;


    public Report toReport() {
        return Report
                .builder()
                .reportedUserId(UUID.fromString(this.reportedAccountId))
                .reporterId(UUID.fromString(this.getReporterAccountId()))
                .reportedPostId(UUID.fromString(this.getReportedPostId()))
                .reportReason(this.getReportReason())
                .build();
    }

    public static GetReportDto from(Report report) {
        return GetReportDto
                .builder()
                .reportedAccountId(report.getReportedUserId().toString())
                .reportedPostId(report.getReportedPostId().toString())
                .reporterAccountId(report.getReporterId().toString())
                .reportReason(report.getReportReason())
                .build();
    }
}
