package org.dsa.services.accountsservice.request;

import lombok.Builder;

@Builder
public record DeleteAccountRequest(Long id) {}
