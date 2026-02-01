package pl.jakub.core.wait;

import java.time.Duration;

public enum WaitTimeout {
  ULTRA_SHORT(1), VERY_SHORT(2), SHORT(5), MEDIUM(10), DEFAULT(30), LONG(60);

  private final Duration duration;

  WaitTimeout(int seconds) {
    this.duration = Duration.ofSeconds(seconds);
  }

  public Duration duration() {
    return duration;
  }

  public long seconds() {
    return duration.getSeconds();
  }
}
