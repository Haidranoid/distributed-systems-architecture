package org.dsa.shared.plugin.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Repository {
  AWS_CODEARTIFACT_SHARED(
      "https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/");

  private final String url;
}
