package org.dsa.core.sharedstarter.messaging.topics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaTopics {
  public static final String ACCOUNT_CREATED = "account-created";
  public static final String ACCOUNT_DELETED = "account-deleted";
  public static final String ACCOUNT_PASSWORD_CHANGED = "account-password-changed";
  public static final String ACCOUNT_UPDATED = "account-updated";
  public static final String USER_LOGGED_IN = "user-logged-in";
  public static final String USER_LOGGED_OUT = "user-logged-out";
}
