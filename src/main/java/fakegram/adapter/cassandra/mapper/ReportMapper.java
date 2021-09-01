package fakegram.adapter.cassandra.mapper;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import fakegram.adapter.cassandra.dao.ReportDao;

@Mapper
public interface ReportMapper {

    @DaoFactory
    ReportDao reportDao(@DaoKeyspace CqlIdentifier keyspace, @DaoTable CqlIdentifier table);

}
