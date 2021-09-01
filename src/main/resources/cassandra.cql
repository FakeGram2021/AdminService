CREATE KEYSPACE IF NOT EXISTS admin
  WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor': 1
};

USE admin;

CREATE TABLE report (
    report_reason TEXT,
    reported_user_id TEXT,
    reported_post_id TEXT,
    reporter_id TEXT,
    PRIMARY KEY(report_reason, reported_user_id, reported_post_id, reporter_id)
);