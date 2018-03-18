INSERT IGNORE INTO users (username, password, enabled) VALUES ('marten@ya2do.io', '$2a$10$5VWqjwoMYnFRTTmbWCRZT.iY3WW8ny27kQuUL9yPK1/WJcPcBLFWO', 1);
INSERT IGNORE INTO users (username, password, enabled) VALUES ('admin@ya2do.io', '$2a$10$E3mPTZb50e7sSW15fDx8Ne7hDZpfDjrmMPTTUp8wVjLTu.G5oPYCO', 1);
INSERT IGNORE INTO users (username, password, enabled) VALUES ('jdoe@does.net', '$2a$10$cFKh0.XCUOA9L.in5smIiO2QIOT8.6ufQSwIIC.AVz26WctxhSWC6', 0);

INSERT IGNORE INTO authorities (username, authority) VALUES('marten@ya2do.io', 'USER');
INSERT IGNORE INTO authorities (username, authority) VALUES('jdoe@does.net', 'USER');

INSERT IGNORE INTO authorities (username, authority) VALUES('admin@ya2do.io', 'USER');
INSERT IGNORE INTO authorities (username, authority) VALUES('admin@ya2do.io', 'ADMIN');

