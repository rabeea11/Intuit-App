CREATE table if not exists payments(
          paymentId SERIAL,
          amount bigint, currency VARCHAR(255),
          userId VARCHAR(255),
          payeeId VARCHAR(255),
          paymentMethodId VARCHAR(255),
          riskScore bigint,
          allowed BIT);