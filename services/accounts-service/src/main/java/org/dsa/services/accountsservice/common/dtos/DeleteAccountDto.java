package org.dsa.services.accountsservice.common.dtos;

import lombok.Builder;

@Builder
public record DeleteAccountDto(
        Long id
) {
}
