package org.dsa.shared.plugin.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Versions {
  PATCH("patch"),
  MINOR("minor"),
  MAYOR("mayor");

  private final String version;
}
