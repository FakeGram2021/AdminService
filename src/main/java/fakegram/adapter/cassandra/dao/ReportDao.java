package fakegram.adapter.cassandra.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import fakegram.adapter.cassandra.model.Report;

@Dao
public interface ReportDao {

    @Query("SELECT * FROM ${qualifiedTableId} WHERE report_reason = :reportReason")
    PagingIterable<Report> findByReportReason(String reportReason);

    @Insert
    void upsert(Report report);

    @Delete
    void delete(Report report);


}
