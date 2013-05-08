package retrofit.http;

public class CachePolicy {
  public enum Policy {
      DEFAULT, NETWORK_ONLY, CACHE_ONLY;
  }
  private final Policy policy;
  private int maxStale;

  public static CachePolicy createDefaultPolicy() {
      return new CachePolicy(Policy.DEFAULT);
  }

  public static CachePolicy createNetworkOnlyPolicy() {
      return new CachePolicy(Policy.NETWORK_ONLY);
  }

  public static CachePolicy createCacheOnlyPolicy(int maxStale) {
      return new CachePolicy(maxStale);
  }

  private CachePolicy(Policy policy) {
    this.policy = policy;
  }

  private CachePolicy(int maxStale) {
      this.policy = Policy.CACHE_ONLY;
      this.maxStale = maxStale;
  }

  public Policy getPolicy() {
      return   policy;
  }

  public int getMaxStale() {
      return   maxStale;
  }
}
