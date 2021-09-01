package fakegram.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private UUID reporterId;
    private UUID reportedUserId;
    private UUID reportedPostId;
    private ReportReason reportReason;
}
