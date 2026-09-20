package org.dsa.shared.plugin.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Repositories {
  BACKEND_CORE(
      "https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/"),
  COMMON_UTILS(
      "https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/"),
  BOOTIFY_PLUGIN(
      "https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/"),
  AWS_CODEARTIFACT_SHARED(
      "https://artifacts-314812911342.d.codeartifact.us-east-1.amazonaws.com/maven/shared/");

  private final String url;
}
