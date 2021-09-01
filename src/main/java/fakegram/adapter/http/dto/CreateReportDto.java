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
public class CreateReportDto {

    private String reportedAccountId;
    private String reportedPostId;
    private ReportReason reportReason;

    public Report toReport(UUID reporterId) {
        return Report
                .builder()
                .reporterId(reporterId)
                .reportedUserId(UUID.fromString(this.reportedAccountId))
                .reportedPostId(UUID.fromString(this.reportedPostId))
                .reportReason(this.reportReason)
                .build();
    }

    public static CreateReportDto from(Report report) {
        return CreateReportDto
                .builder()
                .reportedAccountId(report.getReportedUserId().toString())
                .reportedPostId(report.getReportedPostId().toString())
                .reportReason(report.getReportReason())
                .build();
    }
}
