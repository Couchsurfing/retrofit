package retrofit.http.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.http.CachePolicy;
import retrofit.http.Header;
import retrofit.http.mime.TypedOutput;

/** Encapsulates all of the information necessary to make an HTTP request. */
public final class Request {
  private final String method;
  private final String url;
  private final List<Header> headers;
  private final TypedOutput body;
  private final CachePolicy cachePolicy;

  public Request(String method, String url, List<Header> headers,
                 TypedOutput body, CachePolicy cachePolicy) {
    if (method == null) {
      throw new NullPointerException("Method must not be null.");
    }
    if (url == null) {
      throw new NullPointerException("URL must not be null.");
    }
    this.method = method;
    this.url = url;

    if (headers == null
            && (cachePolicy == null || cachePolicy.getPolicy() == CachePolicy.Policy.DEFAULT)) {
      this.headers = Collections.emptyList();
    } else {
      ArrayList<Header> headersCopy =  new ArrayList<Header>();
      if (cachePolicy != null) {
        switch (cachePolicy.getPolicy()) {
          case CACHE_ONLY:
              headersCopy.add(new Header("Cache-Control",
                      "max-stale=" + cachePolicy.getMaxStale()));
              break;
          case NETWORK_ONLY:
              headersCopy.add(new Header("Cache-Control", "max-age=0"));
              break;
          case DEFAULT:
              break;
          default:
              break;
        }
      }
      if (headers != null) {
          headersCopy.addAll(headers);
      }
      this.headers = Collections.unmodifiableList(headersCopy);
    }

    this.body = body;

    if (cachePolicy == null) {
      this.cachePolicy = CachePolicy.createDefaultPolicy();
    } else {
      this.cachePolicy = cachePolicy;
    }
  }

  /** HTTP method verb. */
  public String getMethod() {
    return method;
  }

  /** Target URL. */
  public String getUrl() {
    return url;
  }

  /** Returns an unmodifiable list of headers.empty, never {@code null}. */
  public List<Header> getHeaders() {
    return headers;
  }

  /** Returns the request body or {@code null}. */
  public TypedOutput getBody() {
    return body;
  }

    /** HTTP method verb. */
    public CachePolicy getCachePolicy() {
        return cachePolicy;
    }
}
