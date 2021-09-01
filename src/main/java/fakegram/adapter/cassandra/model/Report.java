package fakegram.adapter.cassandra.model;


import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import fakegram.domain.model.ReportReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @PartitionKey
    private String reportReason;

    @ClusteringColumn(1)
    private String reportedUserId;

    @ClusteringColumn(2)
    private String reportedPostId;

    @ClusteringColumn(3)
    private String reporterId;

    public static Report from(fakegram.domain.model.Report report) {
        return Report.builder()
                .reportReason(report.getReportReason().toString())
                .reportedUserId(report.getReportedUserId().toString())
                .reportedPostId(report.getReportedPostId().toString())
                .reporterId(report.getReporterId().toString())
                .build();
    }

    public static fakegram.domain.model.Report toReport(Report report) {
        return fakegram.domain.model.Report.builder()
                .reportReason(ReportReason.valueOf(report.getReportReason()))
                .reportedUserId(UUID.fromString(report.reportedUserId))
                .reportedPostId(UUID.fromString(report.getReportedPostId()))
                .reporterId(UUID.fromString(report.getReporterId()))
                .build();
    }


}
