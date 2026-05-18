package org.dsa.services.core.servicesstarter.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    MANAGER_DELETE("manager:delete"),

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    private final String permission;
}
