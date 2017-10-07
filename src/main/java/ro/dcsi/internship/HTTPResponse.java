package ro.dcsi.internship;

public class HTTPResponse {
  public final String response;
  public final int code;

  public HTTPResponse(String response, int code) {
    this.response = response;
    this.code = code;
  }

  @Override
  public String toString() {
    return "HTTPResponse [\n\tresponse=" + response + ",\n\tcode=" + code + "\n]";
  }
}
